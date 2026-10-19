package com.persianlearning.presentation.controller;

import com.persianlearning.business.bo.ChapterBO;
import com.persianlearning.business.validation.ValidationResult;
import com.persianlearning.model.Chapter;
import com.persianlearning.presentation.view.ChapterView;

import java.util.List;

public class ChapterController {
    private ChapterBO chapterBO;
    private ChapterView view;

    public ChapterController(ChapterBO chapterBO, ChapterView view) {
        this.chapterBO = chapterBO;
        this.view = view;
        this.view.setController(this);
    }

    public void showChapterList() {
        List<Chapter> chapters = chapterBO.getAllChapters();
        view.displayChapterList(chapters);
    }

    public void createChapter(String title) {
        ValidationResult result = chapterBO.createChapter(title);
        if (result.isValid()) {
            view.displayMessage("Chapter created successfully.");
            showChapterList();
        } else {
            view.displayMessage(result.getMessage());
        }
    }

    public void openChapter(String id) {
        Chapter chapter = chapterBO.getChapter(id);
        if (chapter != null) {
            view.displayChapter(chapter);
        } else {
            view.displayMessage("Chapter not found.");
        }
    }

    public void renameChapter(String id, String title) {
        ValidationResult result = chapterBO.renameChapter(id, title);
        if (result.isValid()) {
            view.displayMessage("Chapter renamed successfully.");
            showChapterList();
        } else {
            view.displayMessage(result.getMessage());
        }
    }

    public void requestDelete(String id) {
        if (id == null || id.trim().isEmpty()) {
            view.displayMessage("Please select a chapter to delete.");
            return;
        }
        String warning = chapterBO.getDeleteWarning(id);
        view.displayDeleteWarning(warning);
    }

    public void confirmDelete(String id, boolean confirmed) {
        ValidationResult result = chapterBO.deleteChapter(id, confirmed);
        if (result.isValid()) {
            view.displayMessage("Chapter deleted successfully.");
            showChapterList();
        } else {
            view.displayMessage(result.getMessage());
        }
    }
}
