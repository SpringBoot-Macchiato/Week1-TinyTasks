package com.example.demo.controller;

import com.example.demo.error.InvalidValueException;
import com.example.demo.error.TaskNotFoundException;
import com.example.demo.model.Task;
import com.example.demo.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("api/task")
@RestController
public class TaskController {

    private final TaskService taskService = new TaskService();

    @GetMapping("hello")
    public String hello(){
        return "Holaa";
    }

    @GetMapping("helloPost")
    public String helloPost(@RequestParam String name){
        return "Hola" + name;
    }

    @PostMapping
    public Task createTask(@RequestBody Task task){
        return taskService.addTask(task.getTitle(),task.getDescription());
    }

    @GetMapping
    public List<Task> getAllTask(){
        return taskService.getAllTask();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> byIdTask(@PathVariable String id){

        try {
            Task newTask = taskService.getByIdTask(id);
            return ResponseEntity.ok(newTask);
        } catch (TaskNotFoundException e) {
            return ResponseEntity.status(404).body(Map.of("error", e.getMessage()));
        }catch (InvalidValueException e){
            return ResponseEntity.badRequest().body(Map.of("error" , e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public Task deleteTask(@PathVariable String id){
        return taskService.deleteTask(id);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable String id,@RequestBody Task task){
        return taskService.updateTask(id,task);
    }

    @PatchMapping("/{id}")
    public Task updateTaskActive(@PathVariable String id, @RequestBody Task task){
        return taskService.updateTaskActive(id,task.isActive());
    }
}
