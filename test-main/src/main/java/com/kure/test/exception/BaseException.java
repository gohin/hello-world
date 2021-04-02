package com.kure.test.exception;

import java.text.MessageFormat;
import java.util.Locale;

public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String localMessage;
    private Object[] args = null;

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(String message, Object... arguments) {
        super(MessageFormat.format(message, arguments));
    }


    public BaseException(String message, Throwable cause, Object... args) {
        super(message, cause);
        this.args = args;
    }

    protected String getLocalMessage() {
        return this.getLocalMessage(Locale.getDefault());
    }

    public String getLocalMessage(Locale locale) {
        return this.localMessage;
    }

    public String getMessage() {
        return this.getLocalMessage();
    }

    public String getLocalizedMessage() {
        return this.getLocalMessage();
    }
}
