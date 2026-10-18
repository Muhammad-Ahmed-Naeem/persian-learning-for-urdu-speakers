package com.persianlearning.dal.dao.mysql;

import com.persianlearning.dal.dao.IChapterComponentDAO;
import com.persianlearning.model.ChapterComponent;

import java.util.Collections;
import java.util.List;

public class ChapterComponentMySQLDAO implements IChapterComponentDAO {

    @Override
    public boolean saveStructure(String chapterId, List<ChapterComponent> components) {
        // TODO: MySQL save chapter structure
        return false;
    }

    @Override
    public List<ChapterComponent> getComponentsByChapterId(String chapterId) {
        // TODO: MySQL get components for chapter
        return Collections.emptyList();
    }

    @Override
    public ChapterComponent getComponentById(String id) {
        // TODO: MySQL get component by id
        return null;
    }

    @Override
    public boolean updateComponentContent(String id, String content) {
        // TODO: MySQL update content
        return false;
    }

    @Override
    public boolean deleteComponent(String id) {
        // TODO: MySQL delete component
        return false;
    }

    @Override
    public boolean deleteComponentsByChapterId(String chapterId) {
        // TODO: MySQL delete all components for chapter
        return false;
    }
}
