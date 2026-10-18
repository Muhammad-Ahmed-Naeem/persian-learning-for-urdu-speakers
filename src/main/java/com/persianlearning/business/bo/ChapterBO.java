package com.persianlearning.business.bo;

import com.persianlearning.business.validation.ChapterValidator;
import com.persianlearning.business.validation.ValidationResult;
import com.persianlearning.dal.facade.IDALFacade;
import com.persianlearning.model.Chapter;

import java.util.Collections;
import java.util.List;

public class ChapterBO {
    private IDALFacade dalFacade;
    private ChapterValidator validator;

    public ChapterBO(IDALFacade dalFacade) {
        this.dalFacade = dalFacade;
        this.validator = new ChapterValidator();
    }

    public ValidationResult createChapter(String title) {
        // TODO: implement for UC-01
        return ValidationResult.ok();
    }

    public List<Chapter> getAllChapters() {
        // TODO: implement for UC-01
        return Collections.emptyList();
    }

    public Chapter getChapter(String id) {
        // TODO: implement for UC-01
        return null;
    }

    public ValidationResult renameChapter(String id, String title) {
        // TODO: implement for UC-01
        return ValidationResult.ok();
    }

    public String getDeleteWarning(String id) {
        // TODO: implement for UC-01
        return "";
    }

    public ValidationResult deleteChapter(String id, boolean confirmed) {
        // TODO: implement for UC-01
        return ValidationResult.ok();
    }
}
