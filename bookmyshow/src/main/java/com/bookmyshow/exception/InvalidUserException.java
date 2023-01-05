package com.bookmyshow.exception;

public class InvalidUserException extends Exception{
    public InvalidUserException(long id) {
        super("User id: "+id+" is not valid");
    }
}
