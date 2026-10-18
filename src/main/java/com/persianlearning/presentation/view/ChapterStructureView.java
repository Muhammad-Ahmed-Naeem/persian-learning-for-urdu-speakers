package com.persianlearning.presentation.view;

import com.persianlearning.model.ChapterComponent;
import com.persianlearning.model.ComponentType;
import com.persianlearning.presentation.controller.ChapterStructureController;

import java.util.List;

public class ChapterStructureView {
    private ChapterStructureController controller;

    public void setController(ChapterStructureController controller) {
        this.controller = controller;
    }

    public void displaySupportedComponents(List<ComponentType> types) {
        // TODO: display supported component types
    }

    public void displayStructure(List<ChapterComponent> components) {
        // TODO: display current structure
    }

    public void displayMessage(String message) {
        // TODO: display message
    }
}
