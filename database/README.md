# Database

This folder contains the relational MySQL schema and seed data for the Persian Learning App (Iteration 1).

## Schema Overview

The database strictly reflects our **Chen notation EER diagram** and **UML class diagrams**:

| Table | Entity Type | Purpose | Key Constraints |
|---|---|---|---|
| `component_type` | Lookup table | Supported instructional components (FR-CS-01) | Primary key on `type_name` |
| `chapter` | Strong entity | Core chapter records (UC-01) | Primary key on `chapter_id`, non-blank `title` |
| `chapter_component` | Weak entity | Configurable chapter sections (UC-02, UC-03) | `UNIQUE(chapter_id, component_type)`, `UNIQUE(chapter_id, order_index)`, `ON DELETE CASCADE` |
| `glossary_entry` | Strong entity | Persian-to-Urdu vocabulary (UC-04) | Total participation with `chapter`, `ON DELETE CASCADE` |

*Note: `is_complete` is modeled as a derived attribute in the EER diagram, computed dynamically at runtime (`ChapterContentBO.isChapterComplete()`).*

## Setup Instructions

### Option 1: Via MySQL Command Line
Run this from the project root:
```bash
mysql -u root -p < database/schema.sql
```

### Option 2: Via MySQL Workbench
1. Open MySQL Workbench and connect to your local MySQL 8.0 server.
2. Go to **File** ➔ **Open SQL Script...** and select `database/schema.sql`.
3. Click the yellow lightning bolt icon (⚡) to execute the whole script.

### Option 3: Update Local Configuration
Once executed:
1. Make a copy of `src/main/resources/config.properties.example` and name it `config.properties`.
2. Set your MySQL `db.username` and `db.password`.

