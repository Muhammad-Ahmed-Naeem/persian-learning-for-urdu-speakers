-- Database Schema for Persian Language Learning App (Iteration 1)
-- Target DBMS : MySQL 8.0+
-- Encoding    : UTF-8 (utf8mb4 / utf8mb4_unicode_ci)


-- 1. Create and select Database
CREATE DATABASE IF NOT EXISTS persian_learning
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE persian_learning;


-- Table: component_type (Lookup table for supported chapter components)
CREATE TABLE IF NOT EXISTS component_type (
    type_name       VARCHAR(50)  NOT NULL,
    display_name    VARCHAR(100) NOT NULL,
    description     VARCHAR(255) NULL,
    PRIMARY KEY (type_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- supported component types
INSERT INTO component_type (type_name, display_name, description) VALUES
('LEARNING_OBJECTIVES', 'Learning Objectives', 'Stated goals and learning outcomes for the chapter'),
('PERSIAN_DIALOGUE',    'Persian Dialogue',    'Conversational Persian exchange between speakers'),
('PERSIAN_READING',     'Persian Reading',     'Persian reading passage or text'),
('URDU_EXPLANATION',   'Urdu Explanation',    'Instructional Urdu explanation or translation'),
('GRAMMAR_NOTE',        'Grammar Note',        'Specific Persian grammar rules and linguistic notes'),
('EXAMPLES',            'Examples',            'Illustrative Persian sentences with Urdu equivalents'),
('GLOSSARY',            'Glossary',            'Persian-to-Urdu vocabulary introduced in this chapter')
ON DUPLICATE KEY UPDATE display_name = VALUES(display_name);


-- Table: chapter
-- UC-01: Manage Chapters and Basic Details
CREATE TABLE IF NOT EXISTS chapter (
    chapter_id      VARCHAR(36)  NOT NULL,
    title           VARCHAR(255) NOT NULL,
    created_at      TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (chapter_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Note: 'is_complete' is a derived attribute (EER diagram) which will be evaluated at runtime



-- Table: chapter_component (Weak entity set belonging to Chapter)
-- UC-02: Configure Chapter Structure & UC-03: Manage Instructional Content
CREATE TABLE IF NOT EXISTS chapter_component (
    component_id    VARCHAR(36)  NOT NULL,
    chapter_id      VARCHAR(36)  NOT NULL,
    component_type  VARCHAR(50)  NOT NULL,
    order_index     INT          NOT NULL,
    content         MEDIUMTEXT   NOT NULL DEFAULT '',
    created_at      TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (component_id),
    
    -- EER Constraint: Weak entity identified by chapter + component_type
    -- (A chapter has at most one component of each type)
    CONSTRAINT uq_chapter_component UNIQUE (chapter_id, component_type),
    
    -- EER Constraint: order_index is unique within each chapter
    CONSTRAINT uq_chapter_order UNIQUE (chapter_id, order_index),
    
    -- Cascade delete components when chapter is deleted 
    CONSTRAINT fk_component_chapter
        FOREIGN KEY (chapter_id) REFERENCES chapter (chapter_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
        
    CONSTRAINT fk_component_type
        FOREIGN KEY (component_type) REFERENCES component_type (type_name)
        ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- Table: glossary_entry 
-- UC-04: Manage Chapter Glossary
CREATE TABLE IF NOT EXISTS glossary_entry (
    entry_id        VARCHAR(36)  NOT NULL,
    chapter_id      VARCHAR(36)  NOT NULL,
    persian_word    VARCHAR(255) NOT NULL,
    urdu_meaning    TEXT         NOT NULL,
    created_at      TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (entry_id),
    
    -- Cascade delete glossary entries when chapter is deleted 
    CONSTRAINT fk_glossary_chapter
        FOREIGN KEY (chapter_id) REFERENCES chapter (chapter_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
        
    -- a Persian word may appear in at most one chapter's glossary (enforced globally).
    -- Under utf8mb4_unicode_ci, words that differ only in diacritics may be treated as equal
    -- (e.g. كتاب vs کتاب — Arabic kaf vs Persian kaf). The GlossaryBO layer does an exact-string
    -- check first; this constraint is a safety net at the database level.
    CONSTRAINT uq_glossary_persian UNIQUE (persian_word),
    INDEX idx_glossary_chapter (chapter_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
