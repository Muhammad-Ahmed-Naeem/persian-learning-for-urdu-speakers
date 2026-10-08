# Persian Learning for Urdu Speakers

A textbook-style Persian language learning application designed specifically for native Urdu speakers. The system provides structured learning chapters, chapter-specific glossaries, interactive exercises, and immediate feedback to help learners understand and practice Persian.

## Overview

Learning a new language becomes easier when explanations are available in a learner's native language. This project aims to help Urdu-speaking learners study Persian through organized instructional content, Urdu explanations, vocabulary, and automatically evaluated exercises.

The application follows a chapter-based learning approach. Each chapter contains configurable instructional components, a dedicated glossary of new Persian vocabulary, and exercises selected and prepared by an administrator.

## Objectives

* Provide structured, textbook-style Persian lessons.
* Explain Persian concepts and vocabulary in Urdu.
* Organize learning material into manageable chapters.
* Maintain a separate glossary for each chapter.
* Offer different types of interactive exercises.
* Automatically evaluate learner responses where applicable.
* Display scores, correct answers, and feedback immediately.
* Allow administrators to manage learning content and exercises.

## Core Features

### 1. Chapter Management

Administrators can create, view, update, rename, list, and delete chapters.

Each chapter represents a unit of Persian learning material and contains basic details and a configurable structure.

### 2. Configurable Chapter Structure

Administrators can select and arrange the components included in a chapter.

Supported chapter components may include:

* Dialogue
* Grammar notes
* Learning objectives
* Reading passages
* Examples
* Urdu explanations and notes

The system displays these components in the order configured by the administrator.

### 3. Chapter-Specific Glossary

Every chapter has its own glossary containing newly introduced Persian words and their Urdu meanings.

Administrators can:

* Add glossary entries.
* View glossary entries.
* Edit existing entries.
* Delete entries.

Learners can consult the glossary while studying the associated chapter.

Each glossary entry belongs to exactly one chapter. The glossary is designed to introduce new vocabulary without unnecessarily repeating words across chapters.

### 4. Interactive Exercises

The application supports five exercise types:

| Exercise Type                    | Description                                                              |
| -------------------------------- | ------------------------------------------------------------------------ |
| Multiple-Choice Questions (MCQs) | Select the correct answer from available options.                        |
| Vocabulary Matching              | Match Persian words with their Urdu meanings.                            |
| Sentence Completion              | Complete an incomplete Persian sentence.                                 |
| Translation                      | Translate content between Persian and Urdu as specified by the exercise. |
| Persian Typing                   | Type the required Persian word, phrase, or sentence.                     |

Administrators determine which exercise types are used in each chapter and provide the questions and correct answers.

### 5. Automatic Evaluation and Feedback

The system evaluates submitted answers using predefined model answers and accepted alternatives where applicable.

Learners receive:

* Immediate scores.
* Correct or incorrect answer feedback.
* Correct answers when appropriate.
* An exercise attempt record.

Evaluation rules may vary by exercise type. For example, translation exercises may allow predefined alternative answers, while Persian typing exercises may require text normalization or other comparison rules.

### 6. Exercise Attempts

An attempt represents a learner's submitted responses to an exercise.

The system records exercise attempts so that submitted answers, evaluation results, scores, and feedback can be associated with the relevant attempt.

This feature supports reviewing an exercise result without introducing a broader long-term learner progress tracking system.

## User Roles

### Administrator

The administrator is responsible for preparing and maintaining the learning material.

Responsibilities include:

* Managing chapters and their basic details.
* Configuring chapter components and their order.
* Entering and editing Persian and Urdu instructional content.
* Managing chapter-specific glossary entries.
* Selecting exercise types for chapters.
* Creating exercise questions and supplying model answers and accepted alternatives.

### Learner

The learner is a native Urdu speaker studying Persian.

Learners can:

* Browse available chapters.
* Read Persian lessons and Urdu explanations.
* View chapter-specific glossaries.
* Attempt chapter exercises.
* Receive automatically evaluated results.
* Review scores, feedback, and correct answers where applicable.

## Technology Stack

The planned technology stack is:

* **Programming Language:** Java
* **Relational Database:** MySQL
* **Database Approach:** Relational database design

Java naming conventions and appropriate object-oriented programming practices will be followed throughout the implementation.

The final architecture, frameworks, and supporting tools may be documented here as development progresses.

## Database Design Considerations

The database will use a relational structure to maintain consistency between chapters, instructional content, glossaries, exercises, questions, answers, and attempts.

The anticipated core entities include:

* **Chapter:** Stores chapter details.
* **Chapter Component:** Represents a configurable section within a chapter.
* **Chapter Content:** Stores the instructional content associated with a component.
* **Glossary Entry:** Stores a Persian word and its Urdu meaning, associated with one chapter.
* **Exercise:** Represents an exercise associated with a chapter.
* **Exercise Type:** Identifies the type of exercise.
* **Question:** Stores exercise questions and their relevant data.
* **Model Answer:** Stores the correct answer supplied by the administrator.
* **Accepted Answer:** Stores alternative answers accepted by the evaluation mechanism, where applicable.
* **Attempt:** Represents a learner's submission for an exercise.
* **Attempt Response:** Stores individual responses and their evaluation results.

These entities are an initial conceptual outline. Their final structure, attributes, relationships, and constraints will be determined during database design.

## Project Scope

### In Scope

* Textbook-style Persian chapters.
* Urdu explanations and meanings.
* Configurable chapter components.
* Chapter-specific glossaries.
* Five supported exercise types.
* Administrator-managed instructional content and exercises.
* Automatic answer evaluation.
* Immediate scores and feedback.
* Exercise attempt records.

### Out of Scope

The following features are excluded from the current project scope:

* Speech recognition.
* Long-term learner progress tracking.
* Learner accounts.
* Leaderboards.
* Automatic generation of chapters, glossary entries, or questions and answers.
* Human grading.
* Authentication and account management.

The mechanisms used to enter the administrator and learner areas are treated as system boundary conditions rather than separate use cases in the current requirements.

## Main Use Cases

| Use Case                    | Primary Actor | Purpose                                                       |
| --------------------------- | ------------- | ------------------------------------------------------------- |
| Manage Chapters             | Administrator | Create, view, update, rename, list, and delete chapters.      |
| Configure Chapter Structure | Administrator | Select and arrange chapter components.                        |
| Manage Chapter Content      | Administrator | Add or edit instructional content in Persian and Urdu.        |
| Manage Chapter Glossary     | Administrator | Add, view, edit, and delete glossary entries.                 |
| Manage Exercises            | Administrator | Select exercise types and maintain questions and answers.     |
| Study Chapters              | Learner       | Read instructional material and Urdu explanations.            |
| Consult Glossary            | Learner       | View vocabulary associated with a chapter.                    |
| Attempt Exercises           | Learner       | Submit responses to chapter exercises.                        |
| Review Results              | Learner       | View scores, feedback, and correct answers where appropriate. |

## Repository Structure

The repository structure will evolve as the implementation progresses. A possible starting organization is:

```text
persian-learning-for-urdu-speakers/
├── docs/
│   ├── requirements/
│   ├── use-cases/
│   ├── database-design/
│   └── diagrams/
├── src/
├── database/
│   ├── schema.sql
│   └── README.md
├── tests/
├── .gitignore
└── README.md
```

The exact source-code structure will depend on the selected Java project architecture and development tools.

## Getting Started

This repository is currently intended as the shared workspace for the project team.

### Prerequisites

Install the following tools as needed for the chosen project setup:

* Git
* Java Development Kit (JDK)
* MySQL Server
* A Java-compatible IDE, such as IntelliJ IDEA, Eclipse, or NetBeans

### Clone the Repository

```bash
git clone https://github.com/YOUR-USERNAME/persian-learning-for-urdu-speakers.git
cd persian-learning-for-urdu-speakers
```

Replace `YOUR-USERNAME` with the GitHub account or organization that owns the repository.

Build instructions, database configuration, and execution steps will be added once the application structure has been established.

## Team Collaboration

This repository is shared by the project group to coordinate development, documentation, database design, and testing.

### Recommended Workflow

1. Clone the repository to your local machine.
2. Pull the latest changes before starting new work.
3. Create a separate branch for your task.
4. Make and test your changes.
5. Commit changes with clear, descriptive messages.
6. Push your branch to GitHub.
7. Open a pull request for review before merging into the main branch.

Example:

```bash
git pull origin main
git switch -c feature/chapter-management
```

After completing your work:

```bash
git add .
git commit -m "Add chapter management"
git push -u origin feature/chapter-management
```

Other suggested branch names include:

* `feature/chapter-content`
* `feature/glossary-management`
* `feature/exercise-management`
* `feature/automatic-evaluation`
* `docs/requirements`
* `docs/database-design`

Coordinate overlapping changes with teammates to minimize merge conflicts.

## Development Guidelines

* Follow Java naming conventions.
* Use meaningful class, method, and variable names.
* Maintain a clear separation between application logic and database operations.
* Use relational database constraints to preserve data integrity.
* Validate user input before saving it.
* Keep evaluation rules explicit and consistent.
* Document significant design decisions.
* Test new features before submitting changes.
* Never commit database passwords, credentials, or other secrets.

## Testing

Testing will cover the main application workflows, including:

* Chapter creation, editing, and deletion.
* Chapter component selection and ordering.
* Persian and Urdu content management.
* Glossary entry management and chapter association.
* Exercise creation and question management.
* Correct and incorrect answer evaluation.
* Accepted alternative answers.
* Score calculation and feedback.
* Recording and reviewing exercise attempts.
* Database relationships and validation rules.

Detailed test cases and test results will be documented as implementation progresses.

## Project Status

**Status:** Initial planning and collaborative project setup.

Requirements, database design, implementation, and testing will be developed iteratively by the project team.

## Contributing

Contributions from group members are welcome.

Before contributing, review the project requirements, coordinate your assigned tasks with the team, and follow the agreed-upon branching and coding conventions.

## License

A license has not yet been selected. The project team can add an appropriate license once the intended distribution and usage terms have been agreed upon.
