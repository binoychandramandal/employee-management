package com.bookmyshow.exception;

public class InvalidCityException extends Exception{
    public InvalidCityException(long id) {
        super("City id: "+id+" is not valid");
    }
}
