package com.crudzaso.TinyTasks.repository;

import com.crudzaso.TinyTasks.model.Todo;

import java.util.*;

/**
 * Repository interface for Todo data access operations.
 * Defines the contract for CRUD operations following the Repository pattern.
 */
public interface TodoRepository {

    /**
     * Retrieves all tasks from the data store.
     *
     * @return list of all {@link Todo} objects
     */
    List<Todo> findAll();

    /**
     * Finds a task by its unique identifier.
     *
     * @param id the task identifier
     * @return Optional containing the {@link Todo} if found, empty otherwise
     */
    Optional<Todo> findById(Integer id);

    /**
     * Saves a new task and assigns an auto-generated ID.
     *
     * @param todo the task to save
     * @return the saved {@link Todo} with assigned ID
     */
    Todo save(Todo todo);

    /**
     * Removes a task from the data store.
     *
     * @param id the task identifier
     */
    void delete(int id);

}
