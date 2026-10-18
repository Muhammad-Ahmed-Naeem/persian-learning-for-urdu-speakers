package com.persianlearning.business.bo;

import com.persianlearning.business.validation.ChapterValidator;
import com.persianlearning.business.validation.ValidationResult;
import com.persianlearning.dal.facade.IDALFacade;
import com.persianlearning.model.Chapter;

import java.util.List;
import java.util.UUID;

public class ChapterBO {
    private IDALFacade dalFacade;
    private ChapterValidator validator;

    public ChapterBO(IDALFacade dalFacade) {
        this.dalFacade = dalFacade;
        this.validator = new ChapterValidator();
    }

    public ValidationResult createChapter(String title) {
        ValidationResult validation = validator.validateTitle(title);
        if (!validation.isValid()) {
            return validation;
        }

        String chapterId = UUID.randomUUID().toString();
        Chapter chapter = new Chapter(chapterId, title.trim());

        boolean saved = dalFacade.addChapter(chapter);
        if (!saved) {
            return ValidationResult.error("general", "Could not save chapter to database");
        }

        return ValidationResult.ok();
    }

    public List<Chapter> getAllChapters() {
        return dalFacade.getAllChapters();
    }

    public Chapter getChapter(String id) {
        if (id == null || id.trim().isEmpty()) {
            return null;
        }
        return dalFacade.getChapterById(id.trim());
    }

    public ValidationResult renameChapter(String id, String title) {
        if (id == null || id.trim().isEmpty()) {
            return ValidationResult.error("id", "Chapter ID is required");
        }

        Chapter chapter = dalFacade.getChapterById(id.trim());
        if (chapter == null) {
            return ValidationResult.error("id", "Chapter not found");
        }

        ValidationResult validation = validator.validateTitle(title);
        if (!validation.isValid()) {
            return validation;
        }

        chapter.setTitle(title.trim());
        boolean updated = dalFacade.updateChapter(chapter);
        if (!updated) {
            return ValidationResult.error("general", "Could not update chapter");
        }

        return ValidationResult.ok();
    }

    public String getDeleteWarning(String id) {
        return "Deleting this chapter will permanently delete all of its components and glossary entries. Do you wish to continue?";
    }

    public ValidationResult deleteChapter(String id, boolean confirmed) {
        if (!confirmed) {
            return ValidationResult.error("confirmation", "Deletion was not confirmed");
        }

        if (id == null || id.trim().isEmpty()) {
            return ValidationResult.error("id", "Chapter ID is required");
        }

        Chapter chapter = dalFacade.getChapterById(id.trim());
        if (chapter == null) {
            return ValidationResult.error("id", "Chapter not found");
        }

        boolean deleted = dalFacade.deleteChapterCascade(id.trim());
        if (!deleted) {
            return ValidationResult.error("general", "Could not delete chapter");
        }

        return ValidationResult.ok();
    }
}
