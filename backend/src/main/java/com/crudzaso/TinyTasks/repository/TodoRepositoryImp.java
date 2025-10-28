package com.crudzaso.TinyTasks.repository;

import com.crudzaso.TinyTasks.model.Todo;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * In-memory implementation of {@link TodoRepository}.
 * Uses HashMap for storage with auto-incrementing IDs.
 * Data is volatile and not thread-safe.
 */
@Repository
public class TodoRepositoryImp implements TodoRepository {

    /** Internal storage for todos, mapped by ID */
    private final Map<Integer, Todo> todos = new HashMap<>();

    /** Counter for auto-incrementing IDs */
    private Integer nextId = 1;

    /**
     * Retrieves all stored tasks as a new list.
     *
     * @return list of all {@link Todo} objects
     */
    @Override
    public List<Todo> findAll(){
        return new ArrayList<>(todos.values());
    }

    /**
     * Finds a task by ID using HashMap lookup.
     *
     * @param id the task identifier
     * @return Optional containing the {@link Todo} if found, empty otherwise
     */
    @Override
    public Optional<Todo> findById(Integer id){
        return Optional.ofNullable(todos.get(id));
    }

    /**
     * Saves a task with auto-generated ID.
     * Assigns the next available ID and stores in HashMap.
     *
     * @param todo the task to save
     * @return the saved {@link Todo} with assigned ID
     */
    @Override
    public Todo save(Todo todo) {
        todo.setId(nextId++);
        todos.put(todo.getId(), todo);
        return todo;
    }

    /**
     * Removes a task from storage by ID.
     *
     * @param id the task identifier
     */
    @Override
    public void delete(int id) {
        todos.remove(id);
    }

}
