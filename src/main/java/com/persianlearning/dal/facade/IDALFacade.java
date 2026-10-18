package com.persianlearning.dal.facade;

import com.persianlearning.dal.dao.IChapterDAO;
import com.persianlearning.dal.dao.IChapterComponentDAO;
import com.persianlearning.dal.dao.IGlossaryEntryDAO;

public interface IDALFacade extends IChapterDAO, IChapterComponentDAO, IGlossaryEntryDAO {
    boolean deleteChapterCascade(String id);
}
