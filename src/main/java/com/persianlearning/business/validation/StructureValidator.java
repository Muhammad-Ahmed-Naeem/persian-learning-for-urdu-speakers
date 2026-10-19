package com.persianlearning.business.validation;

import com.persianlearning.model.ComponentType;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class StructureValidator {

    public ValidationResult validateStructure(List<ComponentType> types) {
        if (types == null || types.isEmpty()) {
            return ValidationResult.error(
                    "structure", "Select a chapter structure that includes GLOSSARY.");
        }

        Set<ComponentType> selectedTypes = EnumSet.noneOf(ComponentType.class);
        for (ComponentType type : types) {
            if (type == null) {
                return ValidationResult.error(
                        "structure", "The structure contains an invalid component type.");
            }
            if (!selectedTypes.add(type)) {
                return ValidationResult.error(
                        "structure", "Each component type can appear only once: " + type);
            }
        }

        if (!selectedTypes.contains(ComponentType.GLOSSARY)) {
            return ValidationResult.error(
                    "structure", "Every chapter structure must include GLOSSARY.");
        }

        return ValidationResult.ok();
    }
}
