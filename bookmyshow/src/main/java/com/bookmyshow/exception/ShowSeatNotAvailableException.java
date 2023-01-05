package com.bookmyshow.exception;

public class ShowSeatNotAvailableException extends Exception{
    public ShowSeatNotAvailableException(long id) {
        super("Seat id: "+id+" is not available for booking");
    }
}
