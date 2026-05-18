package com.taskmanager.controller;

import com.taskmanager.dto.ProjectRequestDto;
import com.taskmanager.entity.Project;
import com.taskmanager.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    // CREATE PROJECT API
    @PostMapping
    public ResponseEntity<Project> createProject(@Valid @RequestBody ProjectRequestDto dto) {
        return ResponseEntity.status(201).body(projectService.createProject(dto));
    }

    // GET ALL PROJECTS API
    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }
}