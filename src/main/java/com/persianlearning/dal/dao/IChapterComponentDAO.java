package com.persianlearning.dal.dao;

import com.persianlearning.model.ChapterComponent;
import java.util.List;

public interface IChapterComponentDAO {
    boolean saveStructure(String chapterId, List<ChapterComponent> components);
    List<ChapterComponent> getComponentsByChapterId(String chapterId);
    ChapterComponent getComponentById(String id);
    boolean updateComponentContent(String id, String content);
    boolean deleteComponent(String id);
    boolean deleteComponentsByChapterId(String chapterId);
}
