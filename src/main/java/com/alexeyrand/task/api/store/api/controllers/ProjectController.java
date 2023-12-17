package com.alexeyrand.task.api.store.api.controllers;

import com.alexeyrand.task.api.store.api.Factories.ProjectDtoFactory;
import com.alexeyrand.task.api.store.api.dto.ProjectDto;
import com.alexeyrand.task.api.store.api.exceptions.BadRequestException;
import com.alexeyrand.task.api.store.api.exceptions.NotFoundException;
import com.alexeyrand.task.api.store.entities.ProjectEntity;
import com.alexeyrand.task.api.store.repositories.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor                                            // Заменяет конструктор и autowired
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
// Все поля, все бины которые наследуются - атвоматически забиваются в конструктор и инициализируются
@Transactional
@RestController
public class ProjectController {
    final ProjectDtoFactory projectDtoFactory;
    final ProjectRepository projectRepository;

    public static final String FETCH_PROJECTS = "/api/projects";
    public static final String FETCH_PROJECT = "/api/projects/{id}";
    public static final String CREATE_PROJECT = "/api/projects";
    public static final String EDIT_PROJECT = "/api/projects/{project_id}";
    public static final String DELETE_PROJECT = "/api/projects/{project_id}";

    @GetMapping(FETCH_PROJECT)
    public ProjectEntity deleteProjects(@RequestParam Long id) {
        return projectRepository.getById(id);

    }

    @GetMapping(FETCH_PROJECTS)
    public List<ProjectEntity> fetchProjects() {
        System.out.println("тут");
        List<ProjectEntity> projects = projectRepository.findAll();
        if (projects.isEmpty())
            System.out.println("Пусто");
        return projects;
    }

    @PostMapping(CREATE_PROJECT)
    public ProjectDto createProject(@RequestParam String name) {
        System.out.println("тут");
        if (name.trim().isEmpty()) {
            throw new BadRequestException("Name can't ve empty");
        }

        // Проверка на проект с таким именем
        projectRepository
                .findByName(name)
                .ifPresent(project -> {
                    throw new BadRequestException
                            (String.format("Project \"%s\" already exists", name));
                });

        ProjectEntity project = projectRepository.saveAndFlush(
                ProjectEntity.builder()
                        .name(name)
                        .build()
        );
        return projectDtoFactory.makeProjectDto(project);
    }


    @PatchMapping(EDIT_PROJECT)
    public ProjectDto editProject(@PathVariable("project_id") Long projectId, @RequestParam String name) {

        if (name.trim().isEmpty()) {
            throw new BadRequestException("Name can't be empty");
        }

        ProjectEntity project = projectRepository
                .findById(projectId)
                .orElseThrow(() ->
                        new NotFoundException(
                                String.format(
                                        "Project with \"%s\" already exists.", projectId))
                );

        projectRepository
                .findByName(name)
                .filter(anotherProject -> !Objects.equals(anotherProject.getId(), projectId))
                .ifPresent(anotherProject -> {
                    throw new BadRequestException(String.format("Project \"%s\" already exists", name));
                });

        project = projectRepository.saveAndFlush(
                ProjectEntity.builder()
                        .name(name)
                        .build()
        );

        return projectDtoFactory.makeProjectDto(project);
    }
}
