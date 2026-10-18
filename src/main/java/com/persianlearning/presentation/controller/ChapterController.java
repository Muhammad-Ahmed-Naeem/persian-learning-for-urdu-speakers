package com.persianlearning.presentation.controller;

import com.persianlearning.business.bo.ChapterBO;
import com.persianlearning.presentation.view.ChapterView;

public class ChapterController {
    private ChapterBO chapterBO;
    private ChapterView view;

    public ChapterController(ChapterBO chapterBO, ChapterView view) {
        this.chapterBO = chapterBO;
        this.view = view;
        this.view.setController(this);
    }

    public void showChapterList() {
        // TODO: handle show chapter list
    }

    public void createChapter(String title) {
        // TODO: handle create chapter
    }

    public void openChapter(String id) {
        // TODO: handle open chapter
    }

    public void renameChapter(String id, String title) {
        // TODO: handle rename chapter
    }

    public void requestDelete(String id) {
        // TODO: handle request delete (show warning)
    }

    public void confirmDelete(String id, boolean confirmed) {
        // TODO: handle confirm delete
    }
}
