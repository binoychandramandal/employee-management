package com.bookmyshow.exception;

public class InvalidMovieException extends Exception{
    public InvalidMovieException(Long id) {
        super("Movie id: "+id+" is not valid");
    }
}
