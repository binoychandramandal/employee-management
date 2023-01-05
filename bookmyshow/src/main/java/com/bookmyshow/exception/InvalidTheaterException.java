package com.bookmyshow.exception;

public class InvalidTheaterException extends Exception{
    public InvalidTheaterException(Long id) {
        super("Theater id: "+id+" is not valid");
    }
}
