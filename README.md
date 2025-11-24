# Task Manager Application

A robust, console-based Task Manager application built in Java. This project demonstrates a clean, layered architecture without the overhead of external frameworks, focusing on core Java principles and file-based persistence.

## 📋 Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Running the Application](#running-the-application)
- [Usage](#usage)
- [Data Storage](#data-storage)

## 🚀 Overview
The Task Manager is designed to help users organize their work by assigning tasks to specific users and tracking their status. It uses a text-based user interface (TUI) for simplicity and direct interaction. The application follows a Service-Oriented Architecture (SOA) principle on a small scale, separating concerns between models, services, and utilities.

## ✨ Features
- **User Management**: Create and list users with unique IDs.
- **Task Management**: Create tasks with titles, descriptions, and assign them to users.
- **Status Tracking**: Update task statuses (PENDING, IN_PROGRESS, COMPLETED).
- **Reporting**: Generate summary reports of task statuses.
- **Persistence**: All data is saved to local text files, ensuring data is not lost between sessions.
- **Input Validation**: Robust validation for user inputs to prevent errors.

## 🛠 Technology Stack
- **Language**: Java (JDK 8+)
- **Build Tool**: None (Pure `javac` compilation)
- **Persistence**: File I/O (Text files)
- **Interface**: Console/Command Line

## 📂 Project Structure
```
TaskManager/
├── src/
│   └── taskmanager/
│       ├── Main.java            # Entry point and UI logic
│       ├── model/               # Data entities
│       │   ├── User.java
│       │   ├── Task.java
│       │   └── TaskStatus.java
│       ├── service/             # Business logic
│       │   ├── UserService.java
│       │   ├── TaskService.java
│       │   └── ReportService.java
│       ├── util/                # Helper classes
│       │   ├── FileStorage.java
│       │   └── InputValidator.java
│       └── exception/           # Custom exceptions
│           └── TaskManagerException.java
├── data/                        # Data persistence directory
│   ├── users.txt
│   └── tasks.txt
├── docs/                        # Documentation
│   ├── UML_Diagrams.md
│   └── System_Design.md
└── README.md
```

## 🏁 Getting Started

### Prerequisites
- Java Development Kit (JDK) installed (version 8 or higher).
- A terminal or command prompt.

### Installation
1. Clone or download this repository.
2. Navigate to the project root directory:
   ```bash
   cd TaskManager
   ```
3. Ensure the `data` directory exists (it is created automatically, but good to check).

### Running the Application
To compile and run the application, use the following commands from the root directory:

**1. Compile:**
```bash
mkdir bin
javac -d bin src/taskmanager/model/*.java src/taskmanager/util/*.java src/taskmanager/exception/*.java src/taskmanager/service/*.java src/taskmanager/Main.java
```

**2. Run:**
```bash
java -cp bin taskmanager.Main
```

## 🖥 Usage
Upon running the application, you will be presented with a main menu:

1. **Add User**: Register a new user by providing a username and email.
2. **List Users**: View all registered users and their IDs.
3. **Add Task**: Create a new task, assigning it to a valid User ID.
4. **List Tasks**: View all tasks with their details and current status.
5. **Update Task Status**: Change the status of a task (e.g., from PENDING to COMPLETED).
6. **Generate Report**: View a statistical summary of tasks by status.
7. **Exit**: Close the application.

## 💾 Data Storage
Data is stored in CSV-like format in the `data/` directory:
- **users.txt**: `id,username,email`
- **tasks.txt**: `id,title,description,assignedUserId,status`

This allows for easy inspection and backup of data.
