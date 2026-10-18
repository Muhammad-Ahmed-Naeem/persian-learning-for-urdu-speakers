package com.persianlearning.dal.factory;

import com.persianlearning.dal.dao.IChapterDAO;
import com.persianlearning.dal.dao.IChapterComponentDAO;
import com.persianlearning.dal.dao.IGlossaryEntryDAO;
import com.persianlearning.dal.dao.mysql.ChapterMySQLDAO;
import com.persianlearning.dal.dao.mysql.ChapterComponentMySQLDAO;
import com.persianlearning.dal.dao.mysql.GlossaryEntryMySQLDAO;

public class MySQLDAOFactory extends AbstractDAOFactory {

    public MySQLDAOFactory() {
    }

    @Override
    public IChapterDAO createChapterDAO() {
        return new ChapterMySQLDAO();
    }

    @Override
    public IChapterComponentDAO createChapterComponentDAO() {
        return new ChapterComponentMySQLDAO();
    }

    @Override
    public IGlossaryEntryDAO createGlossaryEntryDAO() {
        return new GlossaryEntryMySQLDAO();
    }
}
