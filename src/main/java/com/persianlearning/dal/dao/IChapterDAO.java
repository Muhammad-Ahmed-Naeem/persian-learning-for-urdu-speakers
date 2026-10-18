package com.persianlearning.dal.dao;

import com.persianlearning.model.Chapter;
import java.util.List;

public interface IChapterDAO {
    boolean addChapter(Chapter chapter);
    Chapter getChapterById(String id);
    List<Chapter> getAllChapters();
    boolean updateChapter(Chapter chapter);
    boolean deleteChapter(String id);
}
