package com.persianlearning.business.bo;

import com.persianlearning.business.validation.StructureValidator;
import com.persianlearning.business.validation.ValidationResult;
import com.persianlearning.dal.facade.IDALFacade;
import com.persianlearning.model.ChapterComponent;
import com.persianlearning.model.ComponentType;

import java.util.Collections;
import java.util.List;

public class ChapterStructureBO {
    private IDALFacade dalFacade;
    private StructureValidator validator;

    public ChapterStructureBO(IDALFacade dalFacade) {
        this.dalFacade = dalFacade;
        this.validator = new StructureValidator();
    }

    public List<ComponentType> getSupportedComponents() {
        // TODO: implement for UC-02
        return Collections.emptyList();
    }

    public List<ChapterComponent> getStructure(String id) {
        // TODO: implement for UC-02
        return Collections.emptyList();
    }

    public ValidationResult configureStructure(String id, List<ComponentType> types) {
        // TODO: implement for UC-02
        return ValidationResult.ok();
    }
}
