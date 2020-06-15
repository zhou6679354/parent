package com.tianlong.common.lockutil.component;

public interface AquiredLockWorker<T> {
    T invokeAfterLockAquire() throws Exception;
}
