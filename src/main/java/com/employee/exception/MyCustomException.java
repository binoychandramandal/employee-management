package com.employee.exception;

public class MyCustomException extends RuntimeException {

    public MyCustomException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public MyCustomException(String msg) {
        super(msg);
    }

    public MyCustomException() {
        super();
    }

}
