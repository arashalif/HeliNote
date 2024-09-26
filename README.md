# HeliNote App

This is a fully Kotlin-based note-taking application built with Jetpack Compose. The app allows users to create, edit, and manage notes. It also supports adding reminders through the Android Alarm Manager, with notifications for each reminder. The project is modularized by feature and follows a clean architecture approach.

## Features

- **Create and Save Notes**: Notes are stored locally in a Room database.
- **List and Grid View Support**: Users can toggle between list and grid views for displaying notes.
- **Edit Notes**: Directly click on any note to edit its content.
- **Reminders and Notifications**: Add reminders to notes, schedule them with Alarm Manager, and receive notifications when the reminder triggers.
- **Modular Architecture**: The project is organized into multiple layers and modules.
- **Dependency Injection**: Hilt is used for dependency management.
- **Compose UI**: The UI is built entirely using Jetpack Compose.

## Tech Stack

- **Kotlin**: 100% Kotlin codebase.
- **Jetpack Compose**: Used for building modern, declarative UIs.
- **Room Database**: Local storage solution for notes.
- **Alarm Manager**: Used to schedule and manage reminders.
- **Hilt**: For dependency injection.
- **Coroutines**: For asynchronous programming.
- **Modular Architecture**: Organized into distinct modules for better maintainability and scalability.

## Modular Architecture

The project is structured into several modules, adhering to a clean architecture principle:

1. **Database Module**: 
   - Implements the `NoteDatabase`, `NoteDAO`, and the local data source (`LocalDataSource`).
   - Depends on the repository module to interact with data sources.

2. **Repository Module**: 
   - Acts as the single source of truth, managing data operations from the local data source.
   - Depends on the domain module for use cases and models.

3. **Domain Module**: 
   - Responsible for defining business logic (use cases) and models.

4. **Feature Modules**: 
   - **Note List Module**: Displays a list or grid of notes, with an associated ViewModel.
   - **Note Details Module**: Provides note details for editing, with its ViewModel.

## Minimum SDK

- The project supports **Android SDK 21** and above due to the Compose UI restrictions.

## Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/arashalif/HeliNote.git
