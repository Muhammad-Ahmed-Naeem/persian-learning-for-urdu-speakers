package com.persianlearning.presentation.controller;

import com.persianlearning.business.bo.ChapterStructureBO;
import com.persianlearning.model.ComponentType;
import com.persianlearning.presentation.view.ChapterStructureView;

import java.util.List;

public class ChapterStructureController {
    private ChapterStructureBO structureBO;
    private ChapterStructureView view;

    public ChapterStructureController(ChapterStructureBO structureBO, ChapterStructureView view) {
        this.structureBO = structureBO;
        this.view = view;
        this.view.setController(this);
    }

    public void showSupportedComponents() {
        // TODO: show supported components
    }

    public void saveStructure(String id, List<ComponentType> types) {
        // TODO: save chapter structure
    }
}
