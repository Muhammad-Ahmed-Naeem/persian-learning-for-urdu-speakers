package com.persianlearning.dal.dao.mysql;

import com.persianlearning.dal.dao.IGlossaryEntryDAO;
import com.persianlearning.model.GlossaryEntry;

import java.util.Collections;
import java.util.List;

public class GlossaryEntryMySQLDAO implements IGlossaryEntryDAO {

    @Override
    public boolean addEntry(GlossaryEntry entry) {
        // TODO: MySQL add glossary entry
        return false;
    }

    @Override
    public List<GlossaryEntry> getEntriesByChapterId(String chapterId) {
        // TODO: MySQL get glossary entries for chapter
        return Collections.emptyList();
    }

    @Override
    public GlossaryEntry getEntryById(String id) {
        // TODO: MySQL get glossary entry by id
        return null;
    }

    @Override
    public GlossaryEntry getEntryByPersianWord(String persian) {
        // TODO: MySQL find entry by persian word
        return null;
    }

    @Override
    public boolean updateEntry(GlossaryEntry entry) {
        // TODO: MySQL update glossary entry
        return false;
    }

    @Override
    public boolean deleteEntry(String id) {
        // TODO: MySQL delete glossary entry
        return false;
    }

    @Override
    public boolean deleteEntriesByChapterId(String chapterId) {
        return true;
    }
}
