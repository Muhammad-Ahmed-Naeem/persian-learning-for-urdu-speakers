package com.persianlearning.business.bo;

import com.persianlearning.dal.exception.DataAccessException;
import com.persianlearning.model.ChapterComponent;
import com.persianlearning.model.ComponentType;
import com.persianlearning.stubs.InMemoryDALFacade;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChapterStructureBOTest {

    private RecordingDALFacade facade;
    private ChapterStructureBO structureBO;

    @BeforeEach
    public void setUp() {
        facade = new RecordingDALFacade();
        structureBO = new ChapterStructureBO(facade);
    }

    @AfterEach
    public void readOperationsNeverSaveStructure() {
        assertEquals(0, facade.saveCalls, "Loading UC-02 data must not save a structure");
    }

    @Test
    public void supportedComponentsIncludeAllSevenTypesWithoutDatabaseReads() {
        assertEquals(Arrays.asList(
                ComponentType.LEARNING_OBJECTIVES,
                ComponentType.PERSIAN_DIALOGUE,
                ComponentType.PERSIAN_READING,
                ComponentType.URDU_EXPLANATION,
                ComponentType.GRAMMAR_NOTE,
                ComponentType.EXAMPLES,
                ComponentType.GLOSSARY), structureBO.getSupportedComponents());
        assertEquals(0, facade.readCalls);
    }

    @Test
    public void supportedComponentsCannotBeChangedByCaller() {
        List<ComponentType> supported = structureBO.getSupportedComponents();

        assertThrows(UnsupportedOperationException.class, () -> supported.remove(ComponentType.GLOSSARY));
        assertThrows(UnsupportedOperationException.class, () -> supported.set(0, ComponentType.GLOSSARY));
        assertEquals(7, structureBO.getSupportedComponents().size());
        assertEquals(0, facade.readCalls);
    }

    @Test
    public void loadsSavedComponentsWithTheirIdsOrderAndUnicodeContent() {
        facade.seedStructure("chapter-1", Arrays.asList(
                new ChapterComponent("component-1", "chapter-1", ComponentType.PERSIAN_DIALOGUE,
                        0, "سلام — اردو وضاحت"),
                new ChapterComponent("component-2", "chapter-1", ComponentType.GLOSSARY, 1, "")));

        List<ChapterComponent> structure = structureBO.getStructure("chapter-1");

        assertEquals(2, structure.size());
        ChapterComponent first = structure.get(0);
        assertEquals("component-1", first.getComponentId());
        assertEquals("chapter-1", first.getChapterId());
        assertEquals(ComponentType.PERSIAN_DIALOGUE, first.getType());
        assertEquals(0, first.getOrderIndex());
        assertEquals("سلام — اردو وضاحت", first.getContent());
        ChapterComponent second = structure.get(1);
        assertEquals("component-2", second.getComponentId());
        assertEquals("chapter-1", second.getChapterId());
        assertEquals(ComponentType.GLOSSARY, second.getType());
        assertEquals(1, second.getOrderIndex());
        assertEquals("", second.getContent());
        assertEquals(1, facade.readCalls);
        assertEquals("chapter-1", facade.lastReadChapterId);
    }

    @Test
    public void readsOnlyTheRequestedChapter() {
        facade.seedStructure("chapter-1", Collections.singletonList(
                new ChapterComponent("component-1", "chapter-1", ComponentType.GLOSSARY, 0, "")));
        facade.seedStructure("chapter-2", Collections.singletonList(
                new ChapterComponent("component-2", "chapter-2", ComponentType.EXAMPLES, 0, "Other chapter")));

        List<ChapterComponent> structure = structureBO.getStructure("chapter-1");

        assertEquals(1, structure.size());
        assertEquals("component-1", structure.get(0).getComponentId());
        assertEquals("chapter-1", facade.lastReadChapterId);
    }

    @Test
    public void notYetConfiguredChapterLoadsAnEmptyStructure() {
        assertTrue(structureBO.getStructure("chapter-1").isEmpty());
        assertEquals(1, facade.readCalls);
    }

    @Test
    public void nullChapterIdIsRejectedWithoutDatabaseReads() {
        assertThrows(IllegalArgumentException.class, () -> structureBO.getStructure(null));
        assertEquals(0, facade.readCalls);
    }

    @Test
    public void blankChapterIdIsRejectedWithoutDatabaseReads() {
        assertThrows(IllegalArgumentException.class, () -> structureBO.getStructure(" \t "));
        assertEquals(0, facade.readCalls);
    }

    @Test
    public void trimsChapterIdBeforeDelegating() {
        facade.seedStructure("chapter-1", Collections.singletonList(
                new ChapterComponent("component-1", "chapter-1", ComponentType.GLOSSARY, 0, "")));

        List<ChapterComponent> structure = structureBO.getStructure("  chapter-1  ");

        assertEquals("chapter-1", facade.lastReadChapterId);
        assertEquals(1, structure.size());
    }

    @Test
    public void readFailureIsPropagatedInsteadOfReturningAnEmptyStructure() {
        facade.readFailure = new DataAccessException("Test read failure", new SQLException("Test database failure"));

        DataAccessException failure = assertThrows(DataAccessException.class,
                () -> structureBO.getStructure("chapter-1"));

        assertSame(facade.readFailure, failure);
        assertEquals(1, facade.readCalls);
    }

    @Test
    public void repeatedLoadsReadTheCurrentSavedStructure() {
        facade.seedStructure("chapter-1", Collections.singletonList(
                new ChapterComponent("component-1", "chapter-1", ComponentType.GLOSSARY, 0, "")));
        assertEquals(1, structureBO.getStructure("chapter-1").size());
        facade.seedStructure("chapter-1", Arrays.asList(
                new ChapterComponent("component-1", "chapter-1", ComponentType.GLOSSARY, 0, ""),
                new ChapterComponent("component-2", "chapter-1", ComponentType.EXAMPLES, 1, "Updated content")));

        List<ChapterComponent> reloaded = structureBO.getStructure("chapter-1");

        assertEquals(2, reloaded.size());
        assertEquals("Updated content", reloaded.get(1).getContent());
        assertEquals(2, facade.readCalls);
    }

    @Test
    public void missingFacadeIsRejectedAtConstruction() {
        assertThrows(NullPointerException.class, () -> new ChapterStructureBO(null));
    }

    private static class RecordingDALFacade extends InMemoryDALFacade {
        private int readCalls;
        private int saveCalls;
        private String lastReadChapterId;
        private DataAccessException readFailure;

        void seedStructure(String chapterId, List<ChapterComponent> components) {
            super.saveStructure(chapterId, components);
        }

        @Override
        public List<ChapterComponent> getComponentsByChapterId(String chapterId) {
            readCalls++;
            lastReadChapterId = chapterId;
            if (readFailure != null) {
                throw readFailure;
            }
            return super.getComponentsByChapterId(chapterId);
        }

        @Override
        public boolean saveStructure(String chapterId, List<ChapterComponent> components) {
            saveCalls++;
            throw new AssertionError("Read operations must not save structure");
        }
    }
}
