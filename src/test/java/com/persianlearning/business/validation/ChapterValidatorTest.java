package com.persianlearning.business.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChapterValidatorTest {

    private ChapterValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new ChapterValidator();
    }

    @Test
    public void testValidTitle() {
        ValidationResult result = validator.validateTitle("Basics of Persian");
        assertTrue(result.isValid());
        assertNull(result.getField());
    }

    @Test
    public void testNullTitleFails() {
        ValidationResult result = validator.validateTitle(null);
        assertFalse(result.isValid());
        assertEquals("title", result.getField());
    }

    @Test
    public void testEmptyTitleFails() {
        ValidationResult result = validator.validateTitle("");
        assertFalse(result.isValid());
        assertEquals("title", result.getField());
    }

    @Test
    public void testWhitespaceOnlyTitleFails() {
        ValidationResult result = validator.validateTitle("   ");
        assertFalse(result.isValid());
        assertEquals("title", result.getField());
    }

    @Test
    public void testTitleExceeding255CharsFails() {
        StringBuilder longTitle = new StringBuilder();
        for (int i = 0; i < 256; i++) {
            longTitle.append("a");
        }
        ValidationResult result = validator.validateTitle(longTitle.toString());
        assertFalse(result.isValid());
        assertEquals("title", result.getField());
    }

    @Test
    public void testPersianTitlePasses() {
        ValidationResult result = validator.validateTitle("فصل اول: آشنایی");
        assertTrue(result.isValid());
    }
}
