package com.crudzaso.TinyTasks.controller;

import com.crudzaso.TinyTasks.model.Todo;
import com.crudzaso.TinyTasks.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST controller for Todo task management.
 * Provides CRUD endpoints following RESTful conventions.
 */
@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    /**
     * Constructs controller with service dependency injection.
     *
     * @param todoService the service layer for business logic
     */
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    /**
     * Retrieves all tasks from the system.
     *
     * @return list of all {@link Todo} objects
     */
    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    /**
     * Creates a new task with the specified title.
     * Title must be at least 3 characters after trimming.
     *
     * @param request map containing the task title
     * @return HTTP 201 with created todo on success, HTTP 400 on validation error
     */
    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody Map<String, String> request) {
        try {
            String title = request.get("title");
            Todo newTodo = todoService.createTodo(title);
            return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Toggles the completion status of a task.
     * Inverts the done field (true to false or vice versa).
     *
     * @param id the unique identifier of the task
     * @return HTTP 200 with updated todo if found, HTTP 404 otherwise
     */
    @PutMapping("/{id}/toggle")
    public ResponseEntity<?> toggleTodo(@PathVariable int id) {
        return todoService.toggleTodo(id)
                .map(todo -> ResponseEntity.ok(todo))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null));
    }

    /**
     * Deletes a task from the system.
     *
     * @param id the unique identifier of the task
     * @return HTTP 204 if successfully deleted, HTTP 404 if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable int id) {
        if (todoService.deleteTodo(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
