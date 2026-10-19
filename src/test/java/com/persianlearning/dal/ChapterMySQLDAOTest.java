package com.persianlearning.dal;

import com.persianlearning.dal.dao.mysql.ChapterMySQLDAO;
import com.persianlearning.model.Chapter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ChapterMySQLDAOTest {

    private ChapterMySQLDAO dao;
    private String testChapterId;

    @BeforeEach
    public void setUp() {
        dao = new ChapterMySQLDAO();
        testChapterId = UUID.randomUUID().toString();
    }

    @AfterEach
    public void tearDown() {
        if (testChapterId != null) {
            dao.deleteChapter(testChapterId);
        }
    }

    @Test
    public void testAddAndGetChapterWithPersianTitle() {
        Chapter chapter = new Chapter(testChapterId, "فصل اول: گفتگوی روزمره");
        boolean added = dao.addChapter(chapter);
        assertTrue(added, "Should successfully insert chapter into MySQL");

        Chapter retrieved = dao.getChapterById(testChapterId);
        assertNotNull(retrieved);
        assertEquals(testChapterId, retrieved.getChapterId());
        assertEquals("فصل اول: گفتگوی روزمره", retrieved.getTitle());
    }

    @Test
    public void testGetAllChapters() {
        Chapter chapter = new Chapter(testChapterId, "Test Listing Chapter");
        dao.addChapter(chapter);

        List<Chapter> chapters = dao.getAllChapters();
        assertNotNull(chapters);
        assertFalse(chapters.isEmpty());

        boolean found = false;
        for (Chapter c : chapters) {
            if (testChapterId.equals(c.getChapterId())) {
                found = true;
                break;
            }
        }
        assertTrue(found, "Inserted chapter should be present in getAllChapters");
    }

    @Test
    public void testUpdateChapter() {
        Chapter chapter = new Chapter(testChapterId, "Original Title");
        dao.addChapter(chapter);

        chapter.setTitle("فصل به‌روزشده");
        boolean updated = dao.updateChapter(chapter);
        assertTrue(updated, "Update should succeed");

        Chapter retrieved = dao.getChapterById(testChapterId);
        assertNotNull(retrieved);
        assertEquals("فصل به‌روزشده", retrieved.getTitle());
    }

    @Test
    public void testDeleteChapter() {
        Chapter chapter = new Chapter(testChapterId, "Chapter to delete");
        dao.addChapter(chapter);

        boolean deleted = dao.deleteChapter(testChapterId);
        assertTrue(deleted, "Delete should return true");

        Chapter retrieved = dao.getChapterById(testChapterId);
        assertNull(retrieved, "Deleted chapter should not be found in DB");
    }

    @Test
    public void testGetNonExistentChapterReturnsNull() {
        Chapter result = dao.getChapterById("non-existent-uuid-1234");
        assertNull(result);
    }
}
