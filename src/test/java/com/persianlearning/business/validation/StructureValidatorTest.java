package com.persianlearning.business.validation;

import com.persianlearning.model.ComponentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StructureValidatorTest {

    private StructureValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new StructureValidator();
    }

    @Test
    public void nullSelectionFails() {
        assertStructureError(validator.validateStructure(null));
    }

    @Test
    public void emptySelectionFails() {
        assertStructureError(validator.validateStructure(Collections.<ComponentType>emptyList()));
    }

    @Test
    public void nullComponentTypeFails() {
        assertStructureError(validator.validateStructure(Arrays.asList(ComponentType.GLOSSARY, null)));
    }

    @Test
    public void duplicateGlossaryFails() {
        assertStructureError(validator.validateStructure(Arrays.asList(
                ComponentType.GLOSSARY, ComponentType.GLOSSARY)));
    }

    @Test
    public void duplicateOptionalTypeFails() {
        assertStructureError(validator.validateStructure(Arrays.asList(
                ComponentType.EXAMPLES, ComponentType.GLOSSARY, ComponentType.EXAMPLES)));
    }

    @Test
    public void structureWithoutGlossaryFails() {
        assertStructureError(validator.validateStructure(Arrays.asList(
                ComponentType.LEARNING_OBJECTIVES, ComponentType.PERSIAN_DIALOGUE)));
    }

    @Test
    public void glossaryOnlyIsValid() {
        ValidationResult result = validator.validateStructure(Collections.singletonList(ComponentType.GLOSSARY));

        assertTrue(result.isValid());
        assertNull(result.getField());
        assertNull(result.getMessage());
    }

    @Test
    public void allSupportedTypesAreValid() {
        assertTrue(validator.validateStructure(Arrays.asList(ComponentType.values())).isValid());
    }

    @Test
    public void glossaryMayAppearAnywhereWithoutChangingInputOrder() {
        List<ComponentType> types = Collections.unmodifiableList(Arrays.asList(
                ComponentType.EXAMPLES, ComponentType.GLOSSARY, ComponentType.GRAMMAR_NOTE));

        assertTrue(validator.validateStructure(types).isValid());
        assertEquals(Arrays.asList(
                ComponentType.EXAMPLES, ComponentType.GLOSSARY, ComponentType.GRAMMAR_NOTE), types);
    }

    @Test
    public void separateValidationsDoNotShareSelectedTypes() {
        List<ComponentType> types = Arrays.asList(ComponentType.GLOSSARY, ComponentType.EXAMPLES);

        assertTrue(validator.validateStructure(types).isValid());
        assertStructureError(validator.validateStructure(Collections.singletonList(ComponentType.EXAMPLES)));
        assertTrue(validator.validateStructure(types).isValid());
    }

    private void assertStructureError(ValidationResult result) {
        assertFalse(result.isValid());
        assertEquals("structure", result.getField());
        assertNotNull(result.getMessage());
        assertFalse(result.getMessage().trim().isEmpty());
    }
}
