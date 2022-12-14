package com.tripworld.exceptions;


public class NoRecordFoundException extends RuntimeException {

    private static final String errorMessage = "No record found";
    private static final String forRes = "for resource";

    public NoRecordFoundException(Throwable e) {
        super(errorMessage, e);
    }

    public NoRecordFoundException(String resource, Long id) {
        super(errorMessage + " " + forRes + " " + resource + " : " + id);
    }

    public NoRecordFoundException() {
        super(errorMessage + " " + forRes);
    }
}
