package com.edmunds.exception;

public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException(String errorMessage) {
        super(errorMessage);
    }
    public PersonNotFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
