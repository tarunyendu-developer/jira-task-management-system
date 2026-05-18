package com.taskmanager.service;

import com.taskmanager.dto.CommentRequestDto;
import com.taskmanager.dto.TaskRequestDto;
import com.taskmanager.entity.*;
import com.taskmanager.exception.ResourceNotFoundException;
import com.taskmanager.repository.ProjectRepository;
import com.taskmanager.repository.TaskActivityRepository;
import com.taskmanager.repository.TaskCommentRepository;
import com.taskmanager.repository.TaskRepository;
import com.taskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final TaskActivityRepository taskActivityRepository;
    private final TaskCommentRepository taskCommentRepository;

    // CREATE TASK
    public Task createTask(TaskRequestDto dto) {

        log.info("Creating task: {}", dto.getTitle());

        User user = userRepository.findById(dto.getAssignedUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Project project = projectRepository.findById(dto.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        Task task = Task.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .priority(dto.getPriority())
                .assignedUser(user)
                .project(project)
                .dueDate(dto.getDueDate())
                .build();

        Task savedTask = taskRepository.save(task);
        log.info("Task created successfully with ID: {}", savedTask.getId());
        return savedTask;
    }

    // UPDATE TASK
    public Task updateTask(Long id, TaskRequestDto dto) {

        log.info("Updating task with ID: {}", id);
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        // LOG ACTIVITY BEFORE UPDATE
        logActivity(task, "STATUS_CHANGE", task.getStatus().name(), dto.getStatus().name(), dto.getAssignedUserId());

        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setPriority(dto.getPriority());
        task.setDueDate(dto.getDueDate());

        log.warn("Task status changed from {} to {}", task.getStatus(), dto.getStatus());
        Task updatedTask = taskRepository.save(task);
        log.info("Task updated successfully with ID: {}", updatedTask.getId());
        return updatedTask;
    }

    // ADD COMMENT TO TASK
    public TaskComment addComment(Long taskId, CommentRequestDto dto) {

        log.info("Adding comment to task ID: {}", taskId);

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        TaskComment comment = TaskComment.builder()
                .task(task)
                .user(user)
                .commentText(dto.getCommentText())
                .build();

        TaskComment savedComment = taskCommentRepository.save(comment);
        log.info("Comment added successfully with ID: {}", savedComment.getId());
        return savedComment;
    }

    // GET TASKS WITH PAGINATION
    public Page<Task> getTasksPaginated(Long projectId, Pageable pageable) {
        log.info("Fetching paginated tasks for project ID: {}", projectId);
        return taskRepository.findByProjectId(projectId, pageable);
    }

    // SEARCH TASKS BY TITLE
    public List<Task> searchTasks(String keyword) {
        log.info("Searching tasks with keyword: {}", keyword);
        return taskRepository.findByTitleContainingIgnoreCase(keyword);
    }

    // GET OVERDUE TASKS
    public List<Task> getOverdueTasks() {

        log.warn("Fetching overdue tasks");
        return taskRepository.findAll().stream()
                .filter(task -> task.getDueDate() != null && task.getDueDate().isBefore(LocalDate.now()))
                .filter(task -> task.getStatus() != TaskStatus.DONE)
                .toList();
    }

    // LOG TASK ACTIVITY
    private void logActivity(Task task, String activityType, String oldValue, String newValue, Long updatedBy) {

        TaskActivity activity = TaskActivity.builder()
                .task(task)
                .activityType(activityType)
                .oldValue(oldValue)
                .newValue(newValue)
                .updatedBy(updatedBy)
                .build();

        taskActivityRepository.save(activity);
        log.info("Activity logged for task ID: {}", task.getId());
    }

    // DASHBOARD METRICS
    public Map<String, Object> getDashboardMetrics() {

        log.info("Fetching dashboard metrics");

        Map<String, Object> metrics = new HashMap<>();
        metrics.put("totalTasks", taskRepository.count());
        metrics.put("completedTasks", taskRepository.findByStatus(TaskStatus.DONE).size());
        metrics.put("pendingTasks", taskRepository.findByStatus(TaskStatus.OPEN).size());

        return metrics;
    }
}