package com.persianlearning.dal.dao.mongo;

import com.persianlearning.dal.dao.IChapterDAO;
import com.persianlearning.model.Chapter;

import java.util.Collections;
import java.util.List;

public class ChapterMongoDAO implements IChapterDAO {

    @Override
    public boolean addChapter(Chapter chapter) {
        // TODO: MongoDB add chapter
        return false;
    }

    @Override
    public Chapter getChapterById(String id) {
        // TODO: MongoDB find chapter by id
        return null;
    }

    @Override
    public List<Chapter> getAllChapters() {
        // TODO: MongoDB get all chapters
        return Collections.emptyList();
    }

    @Override
    public boolean updateChapter(Chapter chapter) {
        // TODO: MongoDB update chapter
        return false;
    }

    @Override
    public boolean deleteChapter(String id) {
        // TODO: MongoDB delete chapter
        return false;
    }
}
