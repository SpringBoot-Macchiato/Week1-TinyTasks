# TinyTasks - Crudzaso

A simple task management application built with Spring Boot and vanilla JavaScript.

## Project Overview

TinyTasks is a microapplication designed to practice building a minimal project structure with **Spring Boot** and **native frontend** (HTML + JavaScript + Bootstrap). The application provides a REST API with small layers and in-memory data persistence.

## Tech Stack

- **Backend:**
  - Java 21
  - Spring Boot 3.5.7
  - Spring Web
  - JUnit 5 for testing

- **Frontend:**
  - HTML5
  - CSS3
  - JavaScript (ES6+)
  - Bootstrap 5.3.0

- **Persistence:** In-memory (no database)

## Project Structure

```
crudzaso-tinytasks/
├── backend/
│   └── src/
│       ├── main/java/com/crudzaso/TinyTasks/
│       │   ├── controller/     # REST Controllers
│       │   ├── service/        # Business Logic
│       │   ├── repository/     # Data Access Layer
│       │   ├── model/          # Domain Models
│       │   └── config/         # Configuration (CORS, etc.)
│       └── test/               # Unit Tests
├── frontend/
│   ├── index.html              # Main HTML
│   ├── css/
│   │   └── main.css           # Styles
│   └── js/
│       ├── app.js             # Main application orchestrator
│       ├── api.js             # Backend communication
│       ├── ui.js              # DOM manipulation
│       └── utils.js           # Utility functions
├── pom.xml                     # Maven configuration
└── README.md
```

## Getting Started

### Prerequisites

- Java 21 or higher
- Maven 3.x
- A modern web browser

### Backend Setup

1. Navigate to the project root:
```bash
cd crudzaso-tinytasks
```

2. Run tests:
```bash
mvn clean test
```

3. Start the backend server:
```bash
mvn spring-boot:run
```

The backend will start on `http://localhost:8080`

### Frontend Setup

1. Navigate to the frontend directory:
```bash
cd frontend
```

2. Start a local HTTP server (choose one):

**Option A - Python:**
```bash
python -m http.server 5500
```

**Option B - Python 3:**
```bash
python3 -m http.server 5500
```

**Option C - Node.js:**
```bash
npx http-server -p 5500
```

**Option D - Live Server (VS Code Extension):**
```bash
# With Live Server extension installed in VS Code
# Right-click on index.html and select "Open with Live Server"
# Or use the shortcut in the bottom right corner
```

3. Open your browser and go to: `http://localhost:5500` (or the port assigned by Live Server)

## API Endpoints

| Method | Endpoint | Description | Request Body |
|--------|----------|-------------|--------------|
| GET | `/api/todos` | Get all tasks | - |
| POST | `/api/todos` | Create a new task | `{ "title": "string" }` |
| PUT | `/api/todos/{id}/toggle` | Toggle task completion | - |
| DELETE | `/api/todos/{id}` | Delete a task | - |

### Example Response

```json
{
  "id": 1,
  "title": "Learn Spring Boot",
  "done": false
}
```

### Error Responses

- **400 Bad Request:** `{ "error": "Title is required" }`
- **404 Not Found:** `{ "error": "Not found" }`

## Features

- List all tasks
- Create new tasks (minimum 3 characters)
- Toggle task completion status
- Delete tasks
- Input validation
- Error handling
- Unit tests (20 tests, all passing)
- CORS configuration
- Modular frontend architecture
- Responsive design with Bootstrap

## Testing

Run all tests:
```bash
mvn test
```

Test coverage includes:
- **Repository Tests:** 6 tests
- **Service Tests:** 13 tests
- **Total:** 20 tests (all passing)

## Architecture

The application follows the **layered architecture** pattern:

1. **Controller Layer:** Handles HTTP requests and responses
2. **Service Layer:** Contains business logic and validations
3. **Repository Layer:** Manages in-memory data storage
4. **Model Layer:** Defines domain objects

### Frontend Architecture

The frontend is organized into separate modules:
- **app.js:** Main orchestrator, initializes the application
- **api.js:** All backend communication (fetch API)
- **ui.js:** DOM manipulation and rendering
- **utils.js:** Utility functions (HTML escaping, show/hide elements)

## Design Principles

This project follows:
- **SOLID Principles**
- **DRY (Don't Repeat Yourself)**
- **KISS (Keep It Simple, Stupid)**
- **Separation of Concerns**
- **Interface-based programming**

## CORS Configuration

CORS is configured to allow requests from:
- `http://localhost:5500`
- `http://127.0.0.1:5500`

Allowed methods: GET, POST, PUT, DELETE

## User Stories (All Completed)

- **HU-01:** List all tasks
- **HU-02:** Create a new task
- **HU-03:** Toggle task completion status
- **HU-04:** Delete a task
- **HU-05:** Unit tests with positive and negative cases

## Learning Objectives

This project helps practice:
- Building REST APIs with Spring Boot
- Layered architecture
- Unit testing with JUnit 5
- Frontend-backend integration
- CORS configuration
- Modular JavaScript
- Bootstrap framework
- Error handling
- Input validation

## Acknowledgments

Built as part of the Crudzaso learning initiative.
