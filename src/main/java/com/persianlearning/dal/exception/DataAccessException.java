package com.persianlearning.dal.exception;

/** Indicates a persistence failure rather than an empty or missing record. */
public class DataAccessException extends RuntimeException {

    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
