package com.persianlearning.stubs;

import com.persianlearning.dal.facade.IDALFacade;
import com.persianlearning.model.Chapter;
import com.persianlearning.model.ChapterComponent;
import com.persianlearning.model.GlossaryEntry;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * In-memory test stub for IDALFacade.
 * Uses HashMaps to simulate database operations without needing MySQL or Mockito.
 */
public class InMemoryDALFacade implements IDALFacade {

    private final Map<String, Chapter> chapters = new LinkedHashMap<>();
    private final Map<String, ChapterComponent> components = new LinkedHashMap<>();
    private final Map<String, GlossaryEntry> glossaryEntries = new LinkedHashMap<>();

    // Flag to simulate database failure during tests
    private boolean failNextWrite = false;

    public void setFailNextWrite(boolean failNextWrite) {
        this.failNextWrite = failNextWrite;
    }

    public void reset() {
        chapters.clear();
        components.clear();
        glossaryEntries.clear();
        failNextWrite = false;
    }

    // --- Chapter Operations ---

    @Override
    public boolean addChapter(Chapter chapter) {
        if (failNextWrite) {
            failNextWrite = false;
            return false;
        }
        chapters.put(chapter.getChapterId(), chapter);
        return true;
    }

    @Override
    public Chapter getChapterById(String id) {
        return chapters.get(id);
    }

    @Override
    public List<Chapter> getAllChapters() {
        return new ArrayList<>(chapters.values());
    }

    @Override
    public boolean updateChapter(Chapter chapter) {
        if (failNextWrite) {
            failNextWrite = false;
            return false;
        }
        if (chapters.containsKey(chapter.getChapterId())) {
            chapters.put(chapter.getChapterId(), chapter);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteChapter(String id) {
        return chapters.remove(id) != null;
    }

    // --- Component Operations ---

    @Override
    public boolean saveStructure(String chapterId, List<ChapterComponent> list) {
        if (failNextWrite) {
            failNextWrite = false;
            return false;
        }
        deleteComponentsByChapterId(chapterId);
        for (ChapterComponent cc : list) {
            components.put(cc.getComponentId(), cc);
        }
        return true;
    }

    @Override
    public List<ChapterComponent> getComponentsByChapterId(String chapterId) {
        List<ChapterComponent> result = new ArrayList<>();
        for (ChapterComponent cc : components.values()) {
            if (chapterId.equals(cc.getChapterId())) {
                result.add(cc);
            }
        }
        return result;
    }

    @Override
    public ChapterComponent getComponentById(String id) {
        return components.get(id);
    }

    @Override
    public boolean updateComponentContent(String id, String content) {
        ChapterComponent cc = components.get(id);
        if (cc != null) {
            cc.setContent(content);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteComponent(String id) {
        return components.remove(id) != null;
    }

    @Override
    public boolean deleteComponentsByChapterId(String chapterId) {
        List<String> toRemove = new ArrayList<>();
        for (ChapterComponent cc : components.values()) {
            if (chapterId.equals(cc.getChapterId())) {
                toRemove.add(cc.getComponentId());
            }
        }
        for (String id : toRemove) {
            components.remove(id);
        }
        return true;
    }

    // --- Glossary Operations ---

    @Override
    public boolean addEntry(GlossaryEntry entry) {
        if (failNextWrite) {
            failNextWrite = false;
            return false;
        }
        glossaryEntries.put(entry.getEntryId(), entry);
        return true;
    }

    @Override
    public List<GlossaryEntry> getEntriesByChapterId(String chapterId) {
        List<GlossaryEntry> result = new ArrayList<>();
        for (GlossaryEntry ge : glossaryEntries.values()) {
            if (chapterId.equals(ge.getChapterId())) {
                result.add(ge);
            }
        }
        return result;
    }

    @Override
    public GlossaryEntry getEntryById(String id) {
        return glossaryEntries.get(id);
    }

    @Override
    public GlossaryEntry getEntryByPersianWord(String persian) {
        if (persian == null) {
            return null;
        }
        for (GlossaryEntry ge : glossaryEntries.values()) {
            if (persian.equals(ge.getPersianWord())) {
                return ge;
            }
        }
        return null;
    }

    @Override
    public boolean updateEntry(GlossaryEntry entry) {
        if (failNextWrite) {
            failNextWrite = false;
            return false;
        }
        if (glossaryEntries.containsKey(entry.getEntryId())) {
            glossaryEntries.put(entry.getEntryId(), entry);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteEntry(String id) {
        return glossaryEntries.remove(id) != null;
    }

    @Override
    public boolean deleteEntriesByChapterId(String chapterId) {
        List<String> toRemove = new ArrayList<>();
        for (GlossaryEntry ge : glossaryEntries.values()) {
            if (chapterId.equals(ge.getChapterId())) {
                toRemove.add(ge.getEntryId());
            }
        }
        for (String id : toRemove) {
            glossaryEntries.remove(id);
        }
        return true;
    }

    // --- Cascade Delete ---

    @Override
    public boolean deleteChapterCascade(String id) {
        deleteEntriesByChapterId(id);
        deleteComponentsByChapterId(id);
        return deleteChapter(id);
    }
}
