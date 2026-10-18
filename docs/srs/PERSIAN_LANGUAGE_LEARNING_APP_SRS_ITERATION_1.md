# Persian Language Learning App

## Iteration 1 Software Requirements Specification

**Version:** 1.0  
**Date:** 14 October 2026

## 1. Purpose

This document specifies the first-iteration requirements for preparing textbook-style Persian learning chapters for Urdu-speaking learners. The iteration enables an administrator to manage chapters, configure their structure, enter instructional content, and maintain chapter-specific Persian-to-Urdu glossaries.

Only functionality assigned to Iteration 1 is specified in this document.

## 2. Scope

The iteration shall support:

- Creating, viewing, editing, and deleting chapters.
- Selecting and ordering the instructional components used by each chapter.
- Entering Persian instructional text and Urdu explanations.
- Maintaining a glossary of new Persian words and their Urdu meanings for each chapter.
- Persisting the prepared learning content between application sessions.

## 3. Actor

### 3.1 Administrator

The administrator prepares and maintains the learning material. The administrator manages chapters, decides the structure of each chapter, enters its instructional content, and maintains its glossary.

Authentication and account management are outside this iteration's scope. Entry into the administration area is therefore treated as a precondition rather than a separate use case.

## 4. Key Terms

| Term | Meaning |
|---|---|
| Chapter | A unit of Persian instructional material on a particular topic. |
| Chapter component | A configurable section within a chapter, such as learning objectives, a dialogue, a reading passage, an Urdu explanation, a grammar note, or examples. |
| Glossary | The Persian words newly introduced in a chapter together with their Urdu meanings. |

## 5. Functional Requirements

### 5.1 Chapter Management

- **FR-CH-01:** The application shall allow the administrator to create a chapter.
- **FR-CH-02:** Each chapter shall have a system-generated unique identifier and an administrator-provided title.
- **FR-CH-03:** The application shall allow the administrator to view a list of existing chapters.
- **FR-CH-04:** The application shall allow the administrator to open and view an existing chapter.
- **FR-CH-05:** The application shall allow the administrator to edit a chapter's title.
- **FR-CH-06:** The application shall allow the administrator to delete a chapter after confirming the operation.
- **FR-CH-07:** Deleting a chapter shall also delete its components and glossary entries.
- **FR-CH-08:** The application shall preserve chapter data between application sessions.

### 5.2 Chapter Structure and Instructional Content

- **FR-CS-01:** The application shall support the following chapter components:
  - Learning objectives
  - Persian dialogue
  - Persian reading passage
  - Urdu translation or explanation
  - Grammar or language note
  - Examples
  - Glossary
- **FR-CS-02:** The administrator shall decide which supported components are included in each chapter.
- **FR-CS-03:** The administrator shall be able to arrange the selected components in the desired sequence.
- **FR-CS-04:** The administrator shall be able to add, view, edit, and remove the content of a selected component.
- **FR-CS-05:** Different chapters may have different structures.
- **FR-CS-06:** The application shall preserve the configured component selection and order.
- **FR-CS-07:** The application shall preserve Persian and Urdu text without loss or character substitution.

### 5.3 Glossary Management

- **FR-GL-01:** Each chapter shall have an associated glossary.
- **FR-GL-02:** The administrator shall be able to add a glossary entry containing a Persian word and its Urdu meaning.
- **FR-GL-03:** The administrator shall be able to list and view a chapter's glossary entries.
- **FR-GL-04:** The administrator shall be able to edit a glossary entry.
- **FR-GL-05:** The administrator shall be able to delete a glossary entry after confirming the operation.
- **FR-GL-06:** A glossary entry shall belong to exactly one chapter.

## 6. Data Requirements

### 6.1 Chapter

- Unique identifier
- Title
- Selected chapter components
- Component order
- Component content

### 6.2 Glossary Entry

- Unique identifier
- Parent chapter identifier
- Persian word
- Urdu meaning

## 7. Validation and Business Rules

- **BR-01:** A chapter title shall not be blank.
- **BR-02:** A selected chapter component shall contain content before the chapter is considered complete.
- **BR-03:** A glossary entry shall contain both a Persian word and an Urdu meaning.
- **BR-04:** The application shall request confirmation before deleting a chapter or glossary entry.
- **BR-05:** Chapter deletion confirmation shall identify that the chapter's components and glossary entries will also be deleted.
- **BR-06:** A failed validation shall not replace previously saved valid data.
- **BR-07:** The application shall reject invalid data with a clear message and preserve the administrator's entered data where practicable.

## 8. Non-Functional Requirements

### 8.1 Usability and Language Support

- **NFR-US-01:** Administration controls and messages shall be understandable to Urdu-speaking users.
- **NFR-US-02:** Persian and Urdu content shall support right-to-left display and text entry.
- **NFR-US-03:** Mixed Persian, Urdu, numbers, and punctuation shall remain readable and correctly ordered.
- **NFR-US-04:** The application shall use legible fonts capable of displaying all required Persian and Urdu characters.
- **NFR-US-05:** Common actions shall have clear labels, and destructive actions shall require confirmation.
- **NFR-US-06:** Validation errors shall identify the affected field and explain how the administrator can correct it.

### 8.2 Reliability and Data Integrity

- **NFR-RL-01:** Saved chapters, component configurations, component content, and glossary entries shall remain available after the application restarts.
- **NFR-RL-02:** A failed validation shall not create a partial or malformed record.
- **NFR-RL-03:** Confirmed chapter deletion shall not leave orphaned chapter components or glossary entries.

### 8.3 Performance

- **NFR-PF-01:** Under normal classroom-sized data, opening a saved chapter or administration view should complete within two seconds on the target laboratory computers.
- **NFR-PF-02:** Saving valid chapter content or a glossary entry should complete within two seconds on the target laboratory computers.

### 8.4 Maintainability and Testability

- **NFR-MT-01:** Chapter and glossary validation rules shall be testable without manual interaction with the user interface.
- **NFR-MT-02:** Chapter structure and ordering rules shall be independently testable.
- **NFR-MT-03:** Tests shall be able to supply controlled data without requiring the complete persistent data store.

## 9. Use-Case Catalogue

| ID | Use case | Primary actor | Summary |
|---|---|---|---|
| UC-01 | Manage chapters and basic details | Administrator | Create, list, view, rename, and delete chapters. |
| UC-02 | Configure chapter structure | Administrator | Select and order the components used by a chapter. |
| UC-03 | Manage chapter instructional content | Administrator | Enter and maintain the content of configured chapter components. |
| UC-04 | Manage chapter glossary | Administrator | Maintain Persian words and their Urdu meanings for a chapter. |

## 10. Detailed Use Cases

### UC-01: Manage Chapters and Basic Details

**Primary actor:** Administrator  
**Precondition:** The administrator has entered the administration area.  
**Trigger:** The administrator opens chapter management.

**Main flow:**

1. The system displays the existing chapters.
2. The administrator chooses to create, view, edit, or delete a chapter.
3. For creation or editing, the administrator enters valid chapter details.
4. The system validates and saves the details.
5. The system displays the updated chapter list.

**Alternative flows:**

- If required data is missing or invalid, the system explains the error and does not save the invalid record.
- If deletion is selected, the system identifies that related components and glossary entries will also be deleted and requests confirmation.
- If deletion is not confirmed, the system leaves the chapter unchanged.

**Postcondition:** The chapter collection reflects the confirmed operation.

### UC-02: Configure Chapter Structure

**Primary actor:** Administrator  
**Precondition:** The selected chapter exists.  
**Trigger:** The administrator chooses to configure the chapter structure.

**Main flow:**

1. The system displays the supported chapter components.
2. The administrator selects the components to include.
3. The administrator arranges the selected components in the desired order.
4. The system validates and saves the configuration.

**Alternative flow:** If the configuration is invalid, the system explains the problem and retains the previous valid configuration.

**Postcondition:** The chapter has a saved administrator-defined structure.

### UC-03: Manage Chapter Instructional Content

**Primary actor:** Administrator  
**Precondition:** The chapter and its component configuration exist.  
**Trigger:** The administrator chooses to edit the chapter content.

**Main flow:**

1. The system displays the configured components in their selected order.
2. The administrator selects a component.
3. The administrator enters or edits its Persian or Urdu content.
4. The system validates and saves the content.
5. The administrator may repeat the process for another component.

**Alternative flow:** If content is invalid, the system identifies the problem and does not replace the last valid content.

**Postcondition:** The saved instructional content reflects the administrator's changes.

### UC-04: Manage Chapter Glossary

**Primary actor:** Administrator  
**Precondition:** The selected chapter exists.  
**Trigger:** The administrator opens the chapter glossary.

**Main flow:**

1. The system displays the chapter's glossary entries.
2. The administrator chooses to add, view, edit, or delete an entry.
3. For addition or editing, the administrator supplies a Persian word and its Urdu meaning.
4. The system validates and saves the entry.

**Alternative flows:**

- If either required value is missing, the system explains the error and does not save the invalid entry.
- Before deletion, the system asks for confirmation.

**Postcondition:** The chapter glossary reflects the confirmed operation.

## 11. Use-Case Dependencies

```text
UC-01 Manage chapters
  +-- UC-02 Configure chapter structure
  |     +-- UC-03 Manage instructional content
  +-- UC-04 Manage glossary
```

The listed use-case order is the recommended implementation order. Where team members work concurrently, they shall agree on shared data and operation contracts before implementing dependent behaviour.

## 12. Iteration 1 Plan

**Goal:** Establish the chapter, configurable content, and glossary foundation.

| Order | Team member | Use case | Deliverable |
|---:|---|---|---|
| 1 | Member 1 | UC-01: Manage chapters and basic details | Persistent chapter creation, listing, editing, and deletion. |
| 2 | Member 2 | UC-02: Configure chapter structure | Selection and ordering of supported chapter components. |
| 3 | Member 3 | UC-03: Manage chapter instructional content | Persian and Urdu content entry for configured components. |
| 4 | Member 4 | UC-04: Manage chapter glossary | Persistent Persian-word and Urdu-meaning management. |

Each member is assigned exactly one use case.

## 13. Unit-Testing Responsibilities

Each team member is responsible for implementing and maintaining the unit tests for the use case assigned to that member.

- Unit tests shall be written using JUnit 5.
- Every assigned use case shall be delivered together with its unit tests.
- Tests shall cover the normal flow, important alternative flows, validation failures, and relevant boundary cases.
- Chapter tests shall cover valid and blank titles, creation, retrieval, editing, confirmed deletion, cancelled deletion, and dependent-data deletion.
- Structure tests shall cover component selection, removal, ordering, invalid configurations, and preservation of the last valid configuration.
- Content tests shall cover Persian and Urdu text preservation, empty required content, editing, and component association.
- Glossary tests shall cover valid entries, missing Persian words, missing Urdu meanings, editing, deletion confirmation, and chapter association.
- Unit tests shall be repeatable and shall not depend on test execution order or manually prepared application state.
- When the unit under test depends on another component, the student may isolate it using a small custom stub written specifically for the test.
- Mocking frameworks, including Mockito, shall not be used.
- Custom stubs shall provide only the behaviour needed by the tests and shall not duplicate the production implementation.
- All assigned tests shall pass before the iteration is considered complete.

## 14. Acceptance Criteria

Iteration 1 shall be accepted when all of the following are demonstrated:

1. An administrator can create, view, edit, and delete persistent chapters.
2. Chapter deletion requires confirmation and does not leave orphaned components or glossary entries.
3. Different chapters can use different administrator-selected component structures and component orders.
4. The administrator can enter and maintain Persian and Urdu instructional content for configured components.
5. Each chapter can maintain its own Persian-to-Urdu glossary.
6. Persian and Urdu text is preserved and displayed correctly in a readable right-to-left form.
7. Saved chapter, component, and glossary data remains available after the application restarts.
8. Each of the four use cases is accompanied by passing JUnit 5 unit tests owned by its assigned team member.
9. Tests that require isolated collaborators use custom stubs and do not use Mockito or another mocking framework.

## 15. Assumptions

- The administrator is responsible for the linguistic correctness and educational quality of all entered chapter content and glossary meanings.
- The application is expected to contain classroom-sized learning content.
