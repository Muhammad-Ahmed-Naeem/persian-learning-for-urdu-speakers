package com.persianlearning.business.bo;

import com.persianlearning.business.validation.ValidationResult;
import com.persianlearning.model.Chapter;
import com.persianlearning.model.ChapterComponent;
import com.persianlearning.model.ComponentType;
import com.persianlearning.model.GlossaryEntry;
import com.persianlearning.stubs.InMemoryDALFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChapterBOTest {

    private InMemoryDALFacade dalFacade;
    private ChapterBO chapterBO;

    @BeforeEach
    public void setUp() {
        dalFacade = new InMemoryDALFacade();
        chapterBO = new ChapterBO(dalFacade);
    }

    @Test
    public void testCreateChapterSuccess() {
        ValidationResult result = chapterBO.createChapter("Introduction to Persian");
        assertTrue(result.isValid());

        List<Chapter> chapters = dalFacade.getAllChapters();
        assertEquals(1, chapters.size());
        assertEquals("Introduction to Persian", chapters.get(0).getTitle());
        assertNotNull(chapters.get(0).getChapterId());
    }

    @Test
    public void testCreateChapterBlankTitleFails() {
        ValidationResult result = chapterBO.createChapter("   ");
        assertFalse(result.isValid());
        assertEquals("title", result.getField());
        assertTrue(dalFacade.getAllChapters().isEmpty());
    }

    @Test
    public void testCreateChapterNullTitleFails() {
        ValidationResult result = chapterBO.createChapter(null);
        assertFalse(result.isValid());
        assertEquals("title", result.getField());
        assertTrue(dalFacade.getAllChapters().isEmpty());
    }

    @Test
    public void testCreateChapterDatabaseFailure() {
        dalFacade.setFailNextWrite(true);
        ValidationResult result = chapterBO.createChapter("Valid Title");
        assertFalse(result.isValid());
        assertTrue(dalFacade.getAllChapters().isEmpty());
    }

    @Test
    public void testGetAllChapters() {
        chapterBO.createChapter("Chapter 1");
        chapterBO.createChapter("Chapter 2");

        List<Chapter> chapters = chapterBO.getAllChapters();
        assertEquals(2, chapters.size());
    }

    @Test
    public void testGetChapterFound() {
        chapterBO.createChapter("Persian Dialogue");
        String id = dalFacade.getAllChapters().get(0).getChapterId();

        Chapter found = chapterBO.getChapter(id);
        assertNotNull(found);
        assertEquals("Persian Dialogue", found.getTitle());
    }

    @Test
    public void testGetChapterNotFound() {
        Chapter found = chapterBO.getChapter("non-existent-id");
        assertNull(found);
    }

    @Test
    public void testRenameChapterSuccess() {
        chapterBO.createChapter("Old Title");
        String id = dalFacade.getAllChapters().get(0).getChapterId();

        ValidationResult result = chapterBO.renameChapter(id, "New Title");
        assertTrue(result.isValid());

        Chapter updated = dalFacade.getChapterById(id);
        assertEquals("New Title", updated.getTitle());
    }

    @Test
    public void testRenameChapterInvalidTitleDoesNotChangeOriginal() {
        chapterBO.createChapter("Original Title");
        String id = dalFacade.getAllChapters().get(0).getChapterId();

        ValidationResult result = chapterBO.renameChapter(id, "   ");
        assertFalse(result.isValid());

        Chapter unchanged = dalFacade.getChapterById(id);
        assertEquals("Original Title", unchanged.getTitle());
    }

    @Test
    public void testRenameNonExistentChapterFails() {
        ValidationResult result = chapterBO.renameChapter("fake-id", "New Title");
        assertFalse(result.isValid());
    }

    @Test
    public void testGetDeleteWarningContainsChildEntityDetails() {
        String warning = chapterBO.getDeleteWarning("any-id");
        assertNotNull(warning);
        assertTrue(warning.toLowerCase().contains("components"));
        assertTrue(warning.toLowerCase().contains("glossary"));
    }

    @Test
    public void testDeleteChapterUnconfirmedDoesNotDelete() {
        chapterBO.createChapter("Chapter to keep");
        String id = dalFacade.getAllChapters().get(0).getChapterId();

        ValidationResult result = chapterBO.deleteChapter(id, false);
        assertFalse(result.isValid());

        assertNotNull(dalFacade.getChapterById(id));
    }

    @Test
    public void testDeleteChapterConfirmedDeletesCascade() {
        chapterBO.createChapter("Chapter with children");
        String chapterId = dalFacade.getAllChapters().get(0).getChapterId();

        // Add a component and a glossary entry to simulate child records
        ChapterComponent comp = new ChapterComponent("c1", chapterId, ComponentType.LEARNING_OBJECTIVES, 1, "Objectives");
        dalFacade.saveStructure(chapterId, Collections.singletonList(comp));

        GlossaryEntry entry = new GlossaryEntry("g1", chapterId, "سلام", "ہیلو");
        dalFacade.addEntry(entry);

        // Delete with confirmation
        ValidationResult result = chapterBO.deleteChapter(chapterId, true);
        assertTrue(result.isValid());

        // Verify chapter and its children are deleted
        assertNull(dalFacade.getChapterById(chapterId));
        assertTrue(dalFacade.getComponentsByChapterId(chapterId).isEmpty());
        assertTrue(dalFacade.getEntriesByChapterId(chapterId).isEmpty());
    }

    @Test
    public void testDeleteNonExistentChapterFails() {
        ValidationResult result = chapterBO.deleteChapter("unknown-id", true);
        assertFalse(result.isValid());
    }
}
