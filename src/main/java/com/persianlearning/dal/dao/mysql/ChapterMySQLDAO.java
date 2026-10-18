package com.persianlearning.dal.dao.mysql;

import com.persianlearning.dal.dao.IChapterDAO;
import com.persianlearning.model.Chapter;

import java.util.Collections;
import java.util.List;

public class ChapterMySQLDAO implements IChapterDAO {

    @Override
    public boolean addChapter(Chapter chapter) {
        // TODO: MySQL insert for chapter
        return false;
    }

    @Override
    public Chapter getChapterById(String id) {
        // TODO: MySQL select chapter by id
        return null;
    }

    @Override
    public List<Chapter> getAllChapters() {
        // TODO: MySQL select all chapters
        return Collections.emptyList();
    }

    @Override
    public boolean updateChapter(Chapter chapter) {
        // TODO: MySQL update chapter
        return false;
    }

    @Override
    public boolean deleteChapter(String id) {
        // TODO: MySQL delete chapter
        return false;
    }
}
