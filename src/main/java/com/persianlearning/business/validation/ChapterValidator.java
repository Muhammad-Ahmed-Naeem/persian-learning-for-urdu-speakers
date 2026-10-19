package com.persianlearning.business.validation;

public class ChapterValidator {

    public ValidationResult validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            return ValidationResult.error("title", "Chapter title cannot be empty");
        }
        if (title.length() > 255) {
            return ValidationResult.error("title", "Chapter title cannot exceed 255 characters");
        }
        return ValidationResult.ok();
    }
}
