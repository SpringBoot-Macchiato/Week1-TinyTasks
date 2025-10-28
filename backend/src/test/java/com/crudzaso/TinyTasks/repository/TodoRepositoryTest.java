package com.crudzaso.TinyTasks.repository;

import com.crudzaso.TinyTasks.model.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TodoRepository Tests")
class TodoRepositoryTest {

    private TodoRepository todoRepository;

    @BeforeEach
    void setUp() {
        todoRepository = new TodoRepositoryImp();
    }

    @Test
    @DisplayName("Should generate unique and auto-incremental IDs")
    void shouldGenerateUniqueAutoIncrementalIds() {
        // Given
        Todo todo1 = new Todo("First task");
        Todo todo2 = new Todo("Second task");

        // When
        Todo saved1 = todoRepository.save(todo1);
        Todo saved2 = todoRepository.save(todo2);

        // Then
        assertNotNull(saved1.getId());
        assertNotNull(saved2.getId());
        assertEquals(1, saved1.getId());
        assertEquals(2, saved2.getId());
    }

    @Test
    @DisplayName("Should save and retrieve a todo")
    void shouldSaveAndRetrieveTodo() {
        // Given
        Todo todo = new Todo("Test task");

        // When
        Todo savedTodo = todoRepository.save(todo);
        Optional<Todo> retrieved = todoRepository.findById(savedTodo.getId());

        // Then
        assertTrue(retrieved.isPresent());
        assertEquals(savedTodo.getId(), retrieved.get().getId());
        assertEquals("Test task", retrieved.get().getTitle());
        assertFalse(retrieved.get().isDone());
    }

    @Test
    @DisplayName("Should return empty Optional when todo not found")
    void shouldReturnEmptyWhenTodoNotFound() {
        // When
        Optional<Todo> result = todoRepository.findById(999);

        // Then
        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("Should return all todos")
    void shouldReturnAllTodos() {
        // Given
        todoRepository.save(new Todo("Task 1"));
        todoRepository.save(new Todo("Task 2"));
        todoRepository.save(new Todo("Task 3"));

        // When
        List<Todo> todos = todoRepository.findAll();

        // Then
        assertEquals(3, todos.size());
    }

    @Test
    @DisplayName("Should delete a todo by ID")
    void shouldDeleteTodoById() {
        // Given
        Todo todo = todoRepository.save(new Todo("Task to delete"));
        int id = todo.getId();

        // When
        todoRepository.delete(id);
        Optional<Todo> result = todoRepository.findById(id);

        // Then
        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("Should return empty list when no todos exist")
    void shouldReturnEmptyListWhenNoTodos() {
        // When
        List<Todo> todos = todoRepository.findAll();

        // Then
        assertTrue(todos.isEmpty());
    }
}
