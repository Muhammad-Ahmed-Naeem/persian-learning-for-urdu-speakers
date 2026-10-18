package com.persianlearning.business.bo;

import com.persianlearning.business.validation.GlossaryValidator;
import com.persianlearning.business.validation.ValidationResult;
import com.persianlearning.dal.facade.IDALFacade;
import com.persianlearning.model.GlossaryEntry;

import java.util.Collections;
import java.util.List;

public class GlossaryBO {
    private IDALFacade dalFacade;
    private GlossaryValidator validator;

    public GlossaryBO(IDALFacade dalFacade) {
        this.dalFacade = dalFacade;
        this.validator = new GlossaryValidator();
    }

    public ValidationResult addEntry(String chapterId, String persian, String urdu) {
        // TODO: implement for UC-04
        return ValidationResult.ok();
    }

    public List<GlossaryEntry> getEntries(String chapterId) {
        // TODO: implement for UC-04
        return Collections.emptyList();
    }

    public ValidationResult updateEntry(String entryId, String persian, String urdu) {
        // TODO: implement for UC-04
        return ValidationResult.ok();
    }

    public ValidationResult deleteEntry(String entryId, boolean confirmed) {
        // TODO: implement for UC-04
        return ValidationResult.ok();
    }

    private boolean isPersianWordTaken(String persian, String excludeEntryId) {
        // TODO: implement for UC-04 (BR-08)
        return false;
    }
}
