package com.crudzaso.TinyTasks.service;

import com.crudzaso.TinyTasks.model.Todo;
import com.crudzaso.TinyTasks.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for Todo business logic.
 * Enforces validation rules and coordinates between controller and repository.
 */
@Service
public class TodoService {

    private final TodoRepository todoRepository;

    /**
     * Constructs service with repository dependency injection.
     *
     * @param todoRepository the data access layer
     */
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    /**
     * Retrieves all tasks from the repository.
     *
     * @return list of all {@link Todo} objects
     */
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    /**
     * Creates a new task with validation.
     * Validates that title is not empty and has at least 3 characters.
     * Whitespace is trimmed automatically.
     *
     * @param title the task description
     * @return the created {@link Todo} with auto-generated ID
     * @throws IllegalArgumentException if title is null, empty, or less than 3 characters
     */
    public Todo createTodo(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title is required");
        }
        if (title.trim().length() < 3) {
            throw new IllegalArgumentException("Title must be at least 3 characters");
        }

        Todo newTodo = new Todo(title.trim());
        return todoRepository.save(newTodo);
    }

    /**
     * Toggles the completion status of a task.
     * Changes done field from true to false or vice versa.
     *
     * @param id the task identifier
     * @return Optional containing the updated {@link Todo} if found, empty otherwise
     */
    public Optional<Todo> toggleTodo(int id) {
        Optional<Todo> todoOpt = todoRepository.findById(id);
        if (todoOpt.isPresent()) {
            Todo todo = todoOpt.get();
            todo.setDone(!todo.isDone());
            return Optional.of(todo);
        }
        return Optional.empty();
    }

    /**
     * Deletes a task if it exists.
     *
     * @param id the task identifier
     * @return true if the task was found and deleted, false otherwise
     */
    public boolean deleteTodo(int id) {
        if (todoRepository.findById(id).isPresent()) {
            todoRepository.delete(id);
            return true;
        }
        return false;
    }
}
