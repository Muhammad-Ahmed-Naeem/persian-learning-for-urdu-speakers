package com.persianlearning.presentation.view;

import com.persianlearning.model.Chapter;
import com.persianlearning.presentation.controller.ChapterController;

import java.util.List;

public class ChapterView {
    private ChapterController controller;

    public void setController(ChapterController controller) {
        this.controller = controller;
    }

    public void displayChapterList(List<Chapter> chapters) {
        // TODO: display chapters in UI
    }

    public void displayChapter(Chapter chapter) {
        // TODO: display chapter details
    }

    public void displayDeleteWarning(String message) {
        // TODO: show deletion confirmation dialog
    }

    public void displayMessage(String message) {
        // TODO: show notification message
    }
}
