package com.persianlearning.presentation.view;

import com.persianlearning.model.ChapterComponent;
import com.persianlearning.presentation.controller.ChapterContentController;

import java.util.List;

public class ChapterContentView {
    private ChapterContentController controller;

    public void setController(ChapterContentController controller) {
        this.controller = controller;
    }

    public void displayConfiguredComponents(List<ChapterComponent> components) {
        // TODO: display configured components
    }

    public void displayMessage(String message) {
        // TODO: display message
    }
}
