package com.persianlearning.business.validation;

public class ValidationResult {
    private final boolean valid;
    private final String field;
    private final String message;

    public ValidationResult(boolean valid, String field, String message) {
        this.valid = valid;
        this.field = field;
        this.message = message;
    }

    public static ValidationResult ok() {
        return new ValidationResult(true, null, null);
    }

    public static ValidationResult error(String field, String message) {
        return new ValidationResult(false, field, message);
    }

    public boolean isValid() {
        return valid;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
