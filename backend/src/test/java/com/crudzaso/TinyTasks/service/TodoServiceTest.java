package com.crudzaso.TinyTasks.service;

import com.crudzaso.TinyTasks.model.Todo;
import com.crudzaso.TinyTasks.repository.TodoRepository;
import com.crudzaso.TinyTasks.repository.TodoRepositoryImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TodoService Tests")
class TodoServiceTest {

    private TodoService todoService;
    private TodoRepository todoRepository;

    @BeforeEach
    void setUp() {
        todoRepository = new TodoRepositoryImp();
        todoService = new TodoService(todoRepository);
    }

    // ===== CREATE TODO TESTS =====

    @Test
    @DisplayName("Should create todo with valid title")
    void shouldCreateTodoWithValidTitle() {
        // Given
        String validTitle = "Learn Spring Boot";

        // When
        Todo createdTodo = todoService.createTodo(validTitle);

        // Then
        assertNotNull(createdTodo);
        assertNotNull(createdTodo.getId());
        assertEquals("Learn Spring Boot", createdTodo.getTitle());
        assertFalse(createdTodo.isDone());
    }

    @Test
    @DisplayName("Should throw exception when title is null")
    void shouldThrowExceptionWhenTitleIsNull() {
        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> todoService.createTodo(null)
        );

        assertEquals("Title is required", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when title is empty")
    void shouldThrowExceptionWhenTitleIsEmpty() {
        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> todoService.createTodo("")
        );

        assertEquals("Title is required", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when title is only whitespace")
    void shouldThrowExceptionWhenTitleIsOnlyWhitespace() {
        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> todoService.createTodo("   ")
        );

        assertEquals("Title is required", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when title is less than 3 characters")
    void shouldThrowExceptionWhenTitleIsTooShort() {
        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> todoService.createTodo("ab")
        );

        assertEquals("Title must be at least 3 characters", exception.getMessage());
    }

    @Test
    @DisplayName("Should trim whitespace from title")
    void shouldTrimWhitespaceFromTitle() {
        // Given
        String titleWithSpaces = "  Test Task  ";

        // When
        Todo createdTodo = todoService.createTodo(titleWithSpaces);

        // Then
        assertEquals("Test Task", createdTodo.getTitle());
    }

    // ===== TOGGLE TODO TESTS =====

    @Test
    @DisplayName("Should toggle todo from false to true")
    void shouldToggleTodoFromFalseToTrue() {
        // Given
        Todo todo = todoService.createTodo("Task to toggle");
        int id = todo.getId();

        // When
        Optional<Todo> toggledTodo = todoService.toggleTodo(id);

        // Then
        assertTrue(toggledTodo.isPresent());
        assertTrue(toggledTodo.get().isDone());
    }

    @Test
    @DisplayName("Should toggle todo from true to false")
    void shouldToggleTodoFromTrueToFalse() {
        // Given
        Todo todo = todoService.createTodo("Task to toggle");
        int id = todo.getId();
        todoService.toggleTodo(id); // First toggle to true

        // When
        Optional<Todo> toggledTodo = todoService.toggleTodo(id); // Toggle back to false

        // Then
        assertTrue(toggledTodo.isPresent());
        assertFalse(toggledTodo.get().isDone());
    }

    @Test
    @DisplayName("Should return empty Optional when toggling non-existent todo")
    void shouldReturnEmptyWhenTogglingNonExistentTodo() {
        // When
        Optional<Todo> result = todoService.toggleTodo(999);

        // Then
        assertFalse(result.isPresent());
    }

    // ===== DELETE TODO TESTS =====

    @Test
    @DisplayName("Should delete existing todo and return true")
    void shouldDeleteExistingTodo() {
        // Given
        Todo todo = todoService.createTodo("Task to delete");
        int id = todo.getId();

        // When
        boolean deleted = todoService.deleteTodo(id);

        // Then
        assertTrue(deleted);
        List<Todo> todos = todoService.getAllTodos();
        assertTrue(todos.isEmpty());
    }

    @Test
    @DisplayName("Should return false when deleting non-existent todo")
    void shouldReturnFalseWhenDeletingNonExistentTodo() {
        // When
        boolean deleted = todoService.deleteTodo(999);

        // Then
        assertFalse(deleted);
    }

    // ===== GET ALL TODOS TESTS =====

    @Test
    @DisplayName("Should return all todos")
    void shouldReturnAllTodos() {
        // Given
        todoService.createTodo("Task 1");
        todoService.createTodo("Task 2");
        todoService.createTodo("Task 3");

        // When
        List<Todo> todos = todoService.getAllTodos();

        // Then
        assertEquals(3, todos.size());
    }

    @Test
    @DisplayName("Should return empty list when no todos exist")
    void shouldReturnEmptyListWhenNoTodos() {
        // When
        List<Todo> todos = todoService.getAllTodos();

        // Then
        assertTrue(todos.isEmpty());
    }
}
