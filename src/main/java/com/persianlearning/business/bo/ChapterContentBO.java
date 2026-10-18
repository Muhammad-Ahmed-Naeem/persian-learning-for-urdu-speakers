package com.persianlearning.business.bo;

import com.persianlearning.business.validation.ContentValidator;
import com.persianlearning.business.validation.ValidationResult;
import com.persianlearning.dal.facade.IDALFacade;
import com.persianlearning.model.ChapterComponent;

import java.util.Collections;
import java.util.List;

public class ChapterContentBO {
    private IDALFacade dalFacade;
    private ContentValidator validator;

    public ChapterContentBO(IDALFacade dalFacade) {
        this.dalFacade = dalFacade;
        this.validator = new ContentValidator();
    }

    public List<ChapterComponent> getConfiguredComponents(String id) {
        // TODO: implement for UC-03
        return Collections.emptyList();
    }

    public ValidationResult saveContent(String componentId, String content) {
        // TODO: implement for UC-03
        return ValidationResult.ok();
    }

    public ValidationResult removeContent(String componentId) {
        // TODO: implement for UC-03
        return ValidationResult.ok();
    }

    public boolean isChapterComplete(String id) {
        // TODO: implement for UC-03
        return false;
    }
}
