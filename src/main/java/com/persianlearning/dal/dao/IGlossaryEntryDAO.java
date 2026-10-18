package com.persianlearning.dal.dao;

import com.persianlearning.model.GlossaryEntry;
import java.util.List;

public interface IGlossaryEntryDAO {
    boolean addEntry(GlossaryEntry entry);
    List<GlossaryEntry> getEntriesByChapterId(String chapterId);
    GlossaryEntry getEntryById(String id);
    GlossaryEntry getEntryByPersianWord(String persian);
    boolean updateEntry(GlossaryEntry entry);
    boolean deleteEntry(String id);
    boolean deleteEntriesByChapterId(String chapterId);
}
