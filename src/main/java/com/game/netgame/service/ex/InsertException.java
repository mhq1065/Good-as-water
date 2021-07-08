package com.game.netgame.service.ex;

public class InsertException extends ServiceException {
    public InsertException() {
        super();
    }

    public InsertException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsertException(Throwable cause) {
        super(cause);
    }

    protected InsertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public InsertException(String 插入异常) {
        super();
    }
}
