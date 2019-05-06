package com.zhhl.concern.common;

public interface RxResult {
    void onError(Throwable throwable);

    void onComplete();
}
