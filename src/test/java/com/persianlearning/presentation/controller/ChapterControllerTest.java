package com.persianlearning.presentation.controller;

import com.persianlearning.business.bo.ChapterBO;
import com.persianlearning.model.Chapter;
import com.persianlearning.presentation.view.ChapterView;
import com.persianlearning.stubs.InMemoryDALFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChapterControllerTest {

    private InMemoryDALFacade dalFacade;
    private ChapterBO chapterBO;
    private TestChapterView testView;
    private ChapterController controller;

    private static class TestChapterView extends ChapterView {
        List<Chapter> displayedChapters;
        Chapter displayedChapter;
        String displayedMessage;
        String displayedWarning;

        @Override
        public void displayChapterList(List<Chapter> chapters) {
            this.displayedChapters = chapters;
        }

        @Override
        public void displayChapter(Chapter chapter) {
            this.displayedChapter = chapter;
        }

        @Override
        public void displayMessage(String message) {
            this.displayedMessage = message;
        }

        @Override
        public void displayDeleteWarning(String message) {
            this.displayedWarning = message;
        }
    }

    @BeforeEach
    public void setUp() {
        dalFacade = new InMemoryDALFacade();
        chapterBO = new ChapterBO(dalFacade);
        testView = new TestChapterView();
        controller = new ChapterController(chapterBO, testView);
    }

    @Test
    public void testShowChapterList() {
        chapterBO.createChapter("Ch 1");
        chapterBO.createChapter("Ch 2");

        controller.showChapterList();

        assertNotNull(testView.displayedChapters);
        assertEquals(2, testView.displayedChapters.size());
    }

    @Test
    public void testCreateChapterSuccess() {
        controller.createChapter("New Persian Chapter");

        assertNotNull(testView.displayedMessage);
        assertTrue(testView.displayedMessage.toLowerCase().contains("success"));
        assertNotNull(testView.displayedChapters);
        assertEquals(1, testView.displayedChapters.size());
    }

    @Test
    public void testCreateChapterFailure() {
        controller.createChapter("   ");

        assertNotNull(testView.displayedMessage);
        assertTrue(testView.displayedMessage.toLowerCase().contains("empty"));
    }

    @Test
    public void testOpenChapter() {
        chapterBO.createChapter("Target Chapter");
        String id = dalFacade.getAllChapters().get(0).getChapterId();

        controller.openChapter(id);

        assertNotNull(testView.displayedChapter);
        assertEquals("Target Chapter", testView.displayedChapter.getTitle());
    }

    @Test
    public void testRenameChapterSuccess() {
        chapterBO.createChapter("Old Title");
        String id = dalFacade.getAllChapters().get(0).getChapterId();

        controller.renameChapter(id, "Renamed Title");

        assertNotNull(testView.displayedMessage);
        assertTrue(testView.displayedMessage.toLowerCase().contains("success"));
        assertEquals("Renamed Title", dalFacade.getChapterById(id).getTitle());
    }

    @Test
    public void testRequestDelete() {
        chapterBO.createChapter("Chapter to Delete");
        String id = dalFacade.getAllChapters().get(0).getChapterId();

        controller.requestDelete(id);

        assertNotNull(testView.displayedWarning);
        assertTrue(testView.displayedWarning.toLowerCase().contains("components"));
    }

    @Test
    public void testConfirmDeleteTrue() {
        chapterBO.createChapter("Chapter to Delete");
        String id = dalFacade.getAllChapters().get(0).getChapterId();

        controller.confirmDelete(id, true);

        assertNotNull(testView.displayedMessage);
        assertTrue(testView.displayedMessage.toLowerCase().contains("success"));
        assertNull(dalFacade.getChapterById(id));
    }

    @Test
    public void testConfirmDeleteFalse() {
        chapterBO.createChapter("Chapter to keep");
        String id = dalFacade.getAllChapters().get(0).getChapterId();

        controller.confirmDelete(id, false);

        assertNotNull(testView.displayedMessage);
        assertNotNull(dalFacade.getChapterById(id));
    }
}
