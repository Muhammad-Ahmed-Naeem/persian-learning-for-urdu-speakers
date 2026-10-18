package com.persianlearning.dal.facade;

import com.persianlearning.dal.dao.IChapterDAO;
import com.persianlearning.dal.dao.IChapterComponentDAO;
import com.persianlearning.dal.dao.IGlossaryEntryDAO;
import com.persianlearning.model.Chapter;
import com.persianlearning.model.ChapterComponent;
import com.persianlearning.model.GlossaryEntry;

import java.util.List;

public class DALFacade implements IDALFacade {
    private IChapterDAO chapterDAO;
    private IChapterComponentDAO componentDAO;
    private IGlossaryEntryDAO glossaryDAO;

    public DALFacade(IChapterDAO c, IChapterComponentDAO cc, IGlossaryEntryDAO g) {
        this.chapterDAO = c;
        this.componentDAO = cc;
        this.glossaryDAO = g;
    }

    @Override
    public boolean addChapter(Chapter chapter) {
        return chapterDAO.addChapter(chapter);
    }

    @Override
    public Chapter getChapterById(String id) {
        return chapterDAO.getChapterById(id);
    }

    @Override
    public List<Chapter> getAllChapters() {
        return chapterDAO.getAllChapters();
    }

    @Override
    public boolean updateChapter(Chapter chapter) {
        return chapterDAO.updateChapter(chapter);
    }

    @Override
    public boolean deleteChapter(String id) {
        return chapterDAO.deleteChapter(id);
    }

    @Override
    public boolean saveStructure(String chapterId, List<ChapterComponent> components) {
        return componentDAO.saveStructure(chapterId, components);
    }

    @Override
    public List<ChapterComponent> getComponentsByChapterId(String chapterId) {
        return componentDAO.getComponentsByChapterId(chapterId);
    }

    @Override
    public ChapterComponent getComponentById(String id) {
        return componentDAO.getComponentById(id);
    }

    @Override
    public boolean updateComponentContent(String id, String content) {
        return componentDAO.updateComponentContent(id, content);
    }

    @Override
    public boolean deleteComponent(String id) {
        return componentDAO.deleteComponent(id);
    }

    @Override
    public boolean deleteComponentsByChapterId(String chapterId) {
        return componentDAO.deleteComponentsByChapterId(chapterId);
    }

    @Override
    public boolean addEntry(GlossaryEntry entry) {
        return glossaryDAO.addEntry(entry);
    }

    @Override
    public List<GlossaryEntry> getEntriesByChapterId(String chapterId) {
        return glossaryDAO.getEntriesByChapterId(chapterId);
    }

    @Override
    public GlossaryEntry getEntryById(String id) {
        return glossaryDAO.getEntryById(id);
    }

    @Override
    public GlossaryEntry getEntryByPersianWord(String persian) {
        return glossaryDAO.getEntryByPersianWord(persian);
    }

    @Override
    public boolean updateEntry(GlossaryEntry entry) {
        return glossaryDAO.updateEntry(entry);
    }

    @Override
    public boolean deleteEntry(String id) {
        return glossaryDAO.deleteEntry(id);
    }

    @Override
    public boolean deleteEntriesByChapterId(String chapterId) {
        return glossaryDAO.deleteEntriesByChapterId(chapterId);
    }

    @Override
    public boolean deleteChapterCascade(String id) {
        // Delete child entities first, then the chapter
        boolean gOk = glossaryDAO.deleteEntriesByChapterId(id);
        boolean cOk = componentDAO.deleteComponentsByChapterId(id);
        boolean chOk = chapterDAO.deleteChapter(id);
        return gOk && cOk && chOk;
    }
}
