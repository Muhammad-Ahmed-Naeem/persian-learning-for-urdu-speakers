package com.persianlearning.dal.factory;

import com.persianlearning.dal.dao.IChapterDAO;
import com.persianlearning.dal.dao.IChapterComponentDAO;
import com.persianlearning.dal.dao.IGlossaryEntryDAO;

public interface IDAOFactory {
    IChapterDAO createChapterDAO();
    IChapterComponentDAO createChapterComponentDAO();
    IGlossaryEntryDAO createGlossaryEntryDAO();
}
