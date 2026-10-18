# Database

This folder contains the MySQL schema and any seed scripts for the Persian Learning App.

## Files

| File | Purpose |
|------|---------|
| `schema.sql` | `CREATE TABLE` statements — added once the team finalises the physical schema |

## Setup

1. Create the database: `CREATE DATABASE persian_learning CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;`
2. Run `schema.sql` to create all tables.
3. Update `src/main/resources/config.properties` with your local MySQL credentials.

> **Never commit database passwords.** Keep real credentials out of `config.properties` when pushing to GitHub.
