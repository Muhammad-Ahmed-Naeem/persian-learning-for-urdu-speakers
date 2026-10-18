package com.persianlearning.dal.dao.mongo;

import com.persianlearning.dal.dao.IChapterComponentDAO;
import com.persianlearning.model.ChapterComponent;

import java.util.Collections;
import java.util.List;

public class ChapterComponentMongoDAO implements IChapterComponentDAO {

    @Override
    public boolean saveStructure(String chapterId, List<ChapterComponent> components) {
        // TODO: MongoDB save structure
        return false;
    }

    @Override
    public List<ChapterComponent> getComponentsByChapterId(String chapterId) {
        // TODO: MongoDB get components by chapter
        return Collections.emptyList();
    }

    @Override
    public ChapterComponent getComponentById(String id) {
        // TODO: MongoDB get component by id
        return null;
    }

    @Override
    public boolean updateComponentContent(String id, String content) {
        // TODO: MongoDB update component content
        return false;
    }

    @Override
    public boolean deleteComponent(String id) {
        // TODO: MongoDB delete component
        return false;
    }

    @Override
    public boolean deleteComponentsByChapterId(String chapterId) {
        // TODO: MongoDB delete components by chapter
        return false;
    }
}
