package com.example.demo.service;

import com.example.demo.model.Task;
import com.example.demo.error.InvalidValueException;
import com.example.demo.error.TaskNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class TaskService {

    private final List<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    public List<Task> getAllTask(){
        return tasks;
    }

    public Task getByIdTask(String id) {

        boolean flagTask = false;

        try {
            int idTask = Integer.parseInt(id);

            for (Task task : tasks) {

                if (task.getId() == idTask) {
                    flagTask = true;
                    return task;
                }
            }
            if (!flagTask) {
                throw new TaskNotFoundException();
            }
        } catch (NumberFormatException e) {
            throw new InvalidValueException();
        }
        return null;
    }

    public Task addTask(String title, String description) {
        if (title == null || title.length() < 3) {
            throw new IllegalArgumentException("Title must have at least 3 characters");
        } else if (description == null) {
            throw new InvalidValueException();
        }
        Task task = new Task(nextId++, title, description, false);
        tasks.add(task);
        return task;
    }

    public Task deleteTask(String id){

        Task task = getByIdTask(id);
        tasks.remove(task);
        return task;
    }

    public Task updateTask(String id, Task task){

        Task taskById = getByIdTask(id);
        taskById.setTitle(task.getTitle());
        taskById.setDescription(task.getDescription());
        taskById.setActive(task.isActive());
        return taskById;
    }

    public Task updateTaskActive(String id, Boolean active){
        Task taskById = getByIdTask(id);
        taskById.setActive(active);
        return taskById;
    }
}
