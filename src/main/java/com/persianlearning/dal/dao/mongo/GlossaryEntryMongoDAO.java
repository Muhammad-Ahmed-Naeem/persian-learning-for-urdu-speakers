package com.persianlearning.dal.dao.mongo;

import com.persianlearning.dal.dao.IGlossaryEntryDAO;
import com.persianlearning.model.GlossaryEntry;

import java.util.Collections;
import java.util.List;

public class GlossaryEntryMongoDAO implements IGlossaryEntryDAO {

    @Override
    public boolean addEntry(GlossaryEntry entry) {
        // TODO: MongoDB add entry
        return false;
    }

    @Override
    public List<GlossaryEntry> getEntriesByChapterId(String chapterId) {
        // TODO: MongoDB get entries for chapter
        return Collections.emptyList();
    }

    @Override
    public GlossaryEntry getEntryById(String id) {
        // TODO: MongoDB get entry by id
        return null;
    }

    @Override
    public GlossaryEntry getEntryByPersianWord(String persian) {
        // TODO: MongoDB get entry by persian word
        return null;
    }

    @Override
    public boolean updateEntry(GlossaryEntry entry) {
        // TODO: MongoDB update entry
        return false;
    }

    @Override
    public boolean deleteEntry(String id) {
        // TODO: MongoDB delete entry
        return false;
    }

    @Override
    public boolean deleteEntriesByChapterId(String chapterId) {
        // TODO: MongoDB delete entries by chapter
        return false;
    }
}
