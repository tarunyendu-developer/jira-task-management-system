package com.taskmanager.service;

import com.taskmanager.dto.ProjectRequestDto;
import com.taskmanager.entity.Project;
import com.taskmanager.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    // CREATE PROJECT
    public Project createProject(ProjectRequestDto dto) {

        log.info("Creating project: {}", dto.getName());

        Project project = Project.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .createdBy(dto.getCreatedBy())
                .build();

        Project savedProject = projectRepository.save(project);
        log.info("Project created successfully with ID: {}", savedProject.getId());
        return savedProject;
    }

    // GET ALL PROJECTS
    public List<Project> getAllProjects() {
        log.info("Fetching all projects");
        return projectRepository.findAll();
    }
}