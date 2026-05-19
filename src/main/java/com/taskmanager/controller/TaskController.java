package com.taskmanager.controller;

import com.taskmanager.dto.CommentRequestDto;
import com.taskmanager.dto.TaskRequestDto;
import com.taskmanager.entity.Task;
import com.taskmanager.entity.TaskComment;
import com.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    // CREATE TASK API
    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody TaskRequestDto dto) {
        return ResponseEntity.status(201).body(taskService.createTask(dto));
    }

    // UPDATE TASK API
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody TaskRequestDto dto) {
        return ResponseEntity.ok(taskService.updateTask(id, dto));
    }

    // ADD COMMENT API
    @PostMapping("/{taskId}/comments")
    public ResponseEntity<TaskComment> addComment(@PathVariable Long taskId, @RequestBody CommentRequestDto dto) {
        return ResponseEntity.status(201).body(taskService.addComment(taskId, dto));
    }

    // SEARCH TASKS API
    @GetMapping("/search")
    public ResponseEntity<List<Task>> searchTasks(@RequestParam String keyword) {
        return ResponseEntity.ok(taskService.searchTasks(keyword));
    }

    // PAGINATION API
    @GetMapping("/project/{projectId}/paged")
    public ResponseEntity<Page<Task>> getPagedTasks(
            @PathVariable Long projectId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);

        return ResponseEntity.ok(taskService.getTasksPaginated(projectId, pageable));
    }

    // OVERDUE TASKS API
    @GetMapping("/overdue")
    public ResponseEntity<List<Task>> getOverdueTasks() {
        return ResponseEntity.ok(taskService.getOverdueTasks());
    }

    // DASHBOARD METRICS API
    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> getDashboard() {
        return ResponseEntity.ok(taskService.getDashboardMetrics());
    }

    // GET TASKS BY PROJECT API
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Task>> getTasksByProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(taskService.getTasksByProject(projectId));
    }

    // GET TASKS BY USER API
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> getTasksByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(taskService.getTasksByUser(userId));
    }

    // PROJECT PROGRESS API
    @GetMapping("/project/{projectId}/progress")
    public ResponseEntity<Double> getProjectProgress(@PathVariable Long projectId) {
        return ResponseEntity.ok(taskService.getProjectProgress(projectId));
    }
}