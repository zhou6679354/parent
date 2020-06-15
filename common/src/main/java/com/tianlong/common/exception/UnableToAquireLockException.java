package com.tianlong.common.exception;
/**
 * @author zhouwenheng
 * @version 1.0
 * @date 2020年06月15日 14:41
 */
public class UnableToAquireLockException extends RuntimeException{
    public UnableToAquireLockException() {     }

    public UnableToAquireLockException(String message) {         super(message);     }

    public UnableToAquireLockException(String message, Throwable cause) {         super(message, cause);     }

}
