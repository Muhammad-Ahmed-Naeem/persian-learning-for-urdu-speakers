package com.persianlearning;

import com.persianlearning.business.bo.ChapterBO;
import com.persianlearning.business.bo.ChapterContentBO;
import com.persianlearning.business.bo.ChapterStructureBO;
import com.persianlearning.business.bo.GlossaryBO;
import com.persianlearning.dal.dao.IChapterComponentDAO;
import com.persianlearning.dal.dao.IChapterDAO;
import com.persianlearning.dal.dao.IGlossaryEntryDAO;
import com.persianlearning.dal.facade.DALFacade;
import com.persianlearning.dal.facade.IDALFacade;
import com.persianlearning.dal.factory.AbstractDAOFactory;
import com.persianlearning.dal.factory.IDAOFactory;
import com.persianlearning.presentation.controller.ChapterContentController;
import com.persianlearning.presentation.controller.ChapterController;
import com.persianlearning.presentation.controller.ChapterStructureController;
import com.persianlearning.presentation.controller.GlossaryController;
import com.persianlearning.presentation.view.AdminView;
import com.persianlearning.presentation.view.ChapterContentView;
import com.persianlearning.presentation.view.ChapterStructureView;
import com.persianlearning.presentation.view.ChapterView;
import com.persianlearning.presentation.view.GlossaryView;

public class AppMain {

    public static void main(String[] args) {
        // Initialize DAO factory and DAOs
        IDAOFactory factory = AbstractDAOFactory.getInstance();
        IChapterDAO chapterDAO = factory.createChapterDAO();
        IChapterComponentDAO componentDAO = factory.createChapterComponentDAO();
        IGlossaryEntryDAO glossaryDAO = factory.createGlossaryEntryDAO();

        // Facade
        IDALFacade dalFacade = new DALFacade(chapterDAO, componentDAO, glossaryDAO);

        // Business Objects
        ChapterBO chapterBO = new ChapterBO(dalFacade);
        ChapterStructureBO structureBO = new ChapterStructureBO(dalFacade);
        ChapterContentBO contentBO = new ChapterContentBO(dalFacade);
        GlossaryBO glossaryBO = new GlossaryBO(dalFacade);

        // Views
        AdminView adminView = new AdminView();
        ChapterView chapterView = new ChapterView();
        ChapterStructureView structureView = new ChapterStructureView();
        ChapterContentView contentView = new ChapterContentView();
        GlossaryView glossaryView = new GlossaryView();

        // Controllers
        ChapterController chapterController = new ChapterController(chapterBO, chapterView);
        ChapterStructureController structureController = new ChapterStructureController(structureBO, structureView);
        ChapterContentController contentController = new ChapterContentController(contentBO, contentView);
        GlossaryController glossaryController = new GlossaryController(glossaryBO, glossaryView);

        // Show main window
        adminView.showMainMenu();
    }
}
