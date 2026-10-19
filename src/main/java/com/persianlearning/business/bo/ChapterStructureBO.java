package com.persianlearning.business.bo;

import com.persianlearning.business.validation.StructureValidator;
import com.persianlearning.business.validation.ValidationResult;
import com.persianlearning.dal.facade.IDALFacade;
import com.persianlearning.model.ChapterComponent;
import com.persianlearning.model.ComponentType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ChapterStructureBO {
    private final IDALFacade dalFacade;
    private final StructureValidator validator;

    public ChapterStructureBO(IDALFacade dalFacade) {
        this.dalFacade = Objects.requireNonNull(dalFacade, "DAL facade is required.");
        this.validator = new StructureValidator();
    }

    public List<ComponentType> getSupportedComponents() {
        return Collections.unmodifiableList(Arrays.asList(ComponentType.values()));
    }

    public List<ChapterComponent> getStructure(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Chapter ID is required.");
        }
        return dalFacade.getComponentsByChapterId(id.trim());
    }

    public ValidationResult configureStructure(String id, List<ComponentType> types) {
        // TODO: implement for UC-02
        return ValidationResult.ok();
    }
}
