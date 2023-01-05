package com.bookmyshow.exception;

public class InvalidSeatException extends Exception{
    public InvalidSeatException(Long id) {
        super("Seat id: "+id+" is not valid");
    }
}
