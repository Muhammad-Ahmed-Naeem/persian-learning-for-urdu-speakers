package com.persianlearning.model;

import java.util.ArrayList;
import java.util.List;

public class Chapter {
    private String chapterId;
    private String title;
    private List<ChapterComponent> components;
    private List<GlossaryEntry> glossaryEntries;

    public Chapter(String chapterId, String title) {
        this.chapterId = chapterId;
        this.title = title;
        this.components = new ArrayList<>();
        this.glossaryEntries = new ArrayList<>();
    }

    public String getChapterId() {
        return chapterId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ChapterComponent> getComponents() {
        return components;
    }

    public List<GlossaryEntry> getGlossaryEntries() {
        return glossaryEntries;
    }
}
