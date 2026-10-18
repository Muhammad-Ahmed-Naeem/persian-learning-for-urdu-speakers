package com.persianlearning.model;

public class GlossaryEntry {
    private String entryId;
    private String chapterId;
    private String persianWord;
    private String urduMeaning;

    public GlossaryEntry(String entryId, String chapterId, String persianWord, String urduMeaning) {
        this.entryId = entryId;
        this.chapterId = chapterId;
        this.persianWord = persianWord;
        this.urduMeaning = urduMeaning;
    }

    public String getEntryId() {
        return entryId;
    }

    public String getChapterId() {
        return chapterId;
    }

    public String getPersianWord() {
        return persianWord;
    }

    public String getUrduMeaning() {
        return urduMeaning;
    }
}
