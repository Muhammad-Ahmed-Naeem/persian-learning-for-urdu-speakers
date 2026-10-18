package com.persianlearning.dal.factory;

import com.persianlearning.dal.dao.IChapterDAO;
import com.persianlearning.dal.dao.IChapterComponentDAO;
import com.persianlearning.dal.dao.IGlossaryEntryDAO;
import com.persianlearning.dal.dao.mongo.ChapterMongoDAO;
import com.persianlearning.dal.dao.mongo.ChapterComponentMongoDAO;
import com.persianlearning.dal.dao.mongo.GlossaryEntryMongoDAO;

public class MongoDAOFactory extends AbstractDAOFactory {

    public MongoDAOFactory() {
    }

    @Override
    public IChapterDAO createChapterDAO() {
        return new ChapterMongoDAO();
    }

    @Override
    public IChapterComponentDAO createChapterComponentDAO() {
        return new ChapterComponentMongoDAO();
    }

    @Override
    public IGlossaryEntryDAO createGlossaryEntryDAO() {
        return new GlossaryEntryMongoDAO();
    }
}
