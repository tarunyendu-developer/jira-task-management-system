package com.taskmanager.repository;

import com.taskmanager.entity.Task;
import com.taskmanager.entity.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByProjectId(Long projectId);
    List<Task> findByAssignedUserId(Long userId);
    List<Task> findByStatus(TaskStatus status);
    Page<Task> findByProjectId(Long projectId, Pageable pageable);
    List<Task> findByTitleContainingIgnoreCase(String keyword);
}