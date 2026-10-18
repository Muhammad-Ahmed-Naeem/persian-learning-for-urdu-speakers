package com.persianlearning.presentation.view;

import com.persianlearning.model.GlossaryEntry;
import com.persianlearning.presentation.controller.GlossaryController;

import java.util.List;

public class GlossaryView {
    private GlossaryController controller;

    public void setController(GlossaryController controller) {
        this.controller = controller;
    }

    public void displayGlossary(List<GlossaryEntry> entries) {
        // TODO: display glossary entries
    }

    public void displayMessage(String message) {
        // TODO: display message
    }
}
