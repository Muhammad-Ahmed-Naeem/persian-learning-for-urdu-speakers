package com.persianlearning.presentation.controller;

import com.persianlearning.business.bo.GlossaryBO;
import com.persianlearning.presentation.view.GlossaryView;

public class GlossaryController {
    private GlossaryBO glossaryBO;
    private GlossaryView view;

    public GlossaryController(GlossaryBO glossaryBO, GlossaryView view) {
        this.glossaryBO = glossaryBO;
        this.view = view;
        this.view.setController(this);
    }

    public void showGlossary(String chapterId) {
        // TODO: show glossary entries
    }

    public void addEntry(String chapterId, String persian, String urdu) {
        // TODO: add glossary entry
    }

    public void editEntry(String entryId, String persian, String urdu) {
        // TODO: edit glossary entry
    }

    public void deleteEntry(String entryId, boolean confirmed) {
        // TODO: delete glossary entry
    }
}
