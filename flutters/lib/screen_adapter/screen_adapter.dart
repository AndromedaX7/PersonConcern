import 'dart:async';
import 'dart:collection';
import 'dart:ui' as ui show window, PointerDataPacket;

import 'package:flutter/foundation.dart';
import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';

export 'dart:ui' show AppLifecycleState, Locale;

class InnerWidgetsFlutterBinding extends WidgetsFlutterBinding {
  static WidgetsBinding ensureInitialized() {
    if (WidgetsBinding.instance == null) InnerWidgetsFlutterBinding();
    return WidgetsBinding.instance;
  }

  @override
  ViewConfiguration createViewConfiguration() {
    return ViewConfiguration(
      size: getScreenAdapterSize(),
      devicePixelRatio: getAdapterRatio(),
    );
  }

  @override
  void initInstances() {
    // TODO: implement initInstances
    super.initInstances();
    ui.window.onPointerDataPacket = _handlePointerDataPacket;
  }

  final Queue<PointerEvent> _pendingPointerEvents = Queue<PointerEvent>();

  @override
  void unlocked() {
    // TODO: implement unlocked
    super.unlocked();
    _flushPointerEventQueue();
  }

  void _handlePointerDataPacket(ui.PointerDataPacket packet) {
    _pendingPointerEvents
        .addAll(PointerEventConverter.expand(packet.data, getAdapterRatio()));
    if (!locked) _flushPointerEventQueue();
  }

  @override
  void cancelPointer(int pointer) {
    super.cancelPointer(pointer);
    if (_pendingPointerEvents.isEmpty && !locked) {
      scheduleMicrotask(_flushPointerEventQueue);
    }
    _pendingPointerEvents.addFirst(PointerCancelEvent(pointer: pointer));
  }

  void _flushPointerEventQueue() {
    assert(!locked);
    while (_pendingPointerEvents.isNotEmpty) {
      _handlePointerEvent(_pendingPointerEvents.removeFirst());
    }
  }

  final Map<int, HitTestResult> _hitTests = <int, HitTestResult>{};

  void _handlePointerEvent(PointerEvent event) {
    assert(!locked);
    HitTestResult result;
    if (event is PointerDownEvent) {
      assert(!_hitTests.containsKey(event.pointer));
      result = HitTestResult();
      hitTest(result, event.position);
      _hitTests[event.pointer] = result;
      assert(() {
        if (debugPrintHitTestResults) debugPrint('$event:$result');
        return true;
      }());
    } else if (event is PointerUpEvent || event is PointerCancelEvent) {
      result = _hitTests.remove(event.pointer);
    } else if (event.down) {
      result = _hitTests[event.pointer];
    } else {
      return;
    }
    if (result != null) dispatchEvent(event, result);
  }
}

double getAdapterRatio() {
  return ui.window.physicalSize.width / SCREEN_WIDTH;
}

double getAdapterPixelRatio() {
  return getAdapterRatio() / ui.window.devicePixelRatio;
}

Size getScreenAdapterSize() {
  return Size(SCREEN_WIDTH, ui.window.physicalSize.height / getAdapterRatio());
}

const double SCREEN_WIDTH = 400;
