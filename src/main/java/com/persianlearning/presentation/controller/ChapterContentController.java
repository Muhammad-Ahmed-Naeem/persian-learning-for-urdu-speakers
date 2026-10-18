package com.persianlearning.presentation.controller;

import com.persianlearning.business.bo.ChapterContentBO;
import com.persianlearning.presentation.view.ChapterContentView;

public class ChapterContentController {
    private ChapterContentBO contentBO;
    private ChapterContentView view;

    public ChapterContentController(ChapterContentBO contentBO, ChapterContentView view) {
        this.contentBO = contentBO;
        this.view = view;
        this.view.setController(this);
    }

    public void showConfiguredComponents(String id) {
        // TODO: show components for chapter
    }

    public void saveContent(String componentId, String content) {
        // TODO: save component content
    }

    public void removeContent(String componentId) {
        // TODO: remove component content
    }
}
