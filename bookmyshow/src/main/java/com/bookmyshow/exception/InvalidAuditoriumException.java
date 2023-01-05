package com.bookmyshow.exception;

public class InvalidAuditoriumException extends Exception{
    public InvalidAuditoriumException(Long id) {
        super("Auditorium id: "+id+" is not valid");
    }
}
