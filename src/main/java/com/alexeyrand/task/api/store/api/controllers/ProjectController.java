package com.alexeyrand.task.api.store.api.controllers;

import com.alexeyrand.task.api.store.api.Factories.ProjectDtoFactory;
import com.alexeyrand.task.api.store.api.controllers.helpers.ControllerHelper;
import com.alexeyrand.task.api.store.api.dto.AckDto;
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

@RequiredArgsConstructor                                           // Заменяет конструктор и autowired
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
@RestController
public class ProjectController {
    final ProjectDtoFactory projectDtoFactory;
    final ProjectRepository projectRepository;
    final ControllerHelper controllerHelper;

    public static final String GET_PROJECTS = "/api/projects";
    public static final String FETCH_PROJECT = "/api/projects/{id}";
    public static final String CREATE_PROJECT = "/api/projects";
    public static final String EDIT_PROJECT = "/api/projects/{project_id}";
    public static final String DELETE_PROJECT = "/api/projects/{project_id}";

//    @GetMapping(FETCH_PROJECT)
//    public ProjectEntity fetchProjects( @PathVariable Long id) {
//        return projectRepository.getReferenceById(id);
//    }

    @GetMapping(GET_PROJECTS)
    public List<ProjectDto> fetchProjects() {
        List<ProjectEntity> projects = projectRepository.findAll();
        if (projects.isEmpty())
            throw new BadRequestException("Project list is empty");
        return projectDtoFactory.getProjectsDto(projects);
    }

    @PostMapping(CREATE_PROJECT)
    public ProjectDto createProject(@RequestParam String name) {
        if (name.trim().isEmpty()) {
            throw new BadRequestException("Name can't be empty");
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


    @PutMapping(EDIT_PROJECT)
    public ProjectDto editProject(@PathVariable(value = "project_id") Long projectId, @RequestParam(value = "project_name") String name) {

        if (name.trim().isEmpty()) {
            throw new BadRequestException("Name can't be empty");
        }

         projectRepository
                .findById(projectId)
                .orElseThrow(() ->
                         new NotFoundException(
                                String.format(
                                        "Project with \"%s\" already not exists.", projectId))
                );

        projectRepository
                .findByName(name)
                .filter(anotherProject -> !Objects.equals(anotherProject.getId(), projectId))
                .ifPresent(anotherProject -> {
                    throw new BadRequestException(String.format("Project \"%s\" already exists", name));
                });

        ProjectEntity project = projectRepository.saveAndFlush(
                ProjectEntity.builder()
                        .name(name)
                        .build()
        );

        project.setName(name);
        project = projectRepository.saveAndFlush(project);
        return projectDtoFactory.makeProjectDto(project);
    }


    @DeleteMapping(DELETE_PROJECT)
    public AckDto deleteProject(@PathVariable("project_id") Long projectId) {

        projectRepository
                .findById(projectId)
                .orElseThrow(() ->
                        new NotFoundException(
                                String.format(
                                        "Project with \"%s\" already exists.", projectId))
                );

        controllerHelper.getProjectOrThrowException(projectId);
        projectRepository.deleteById(projectId);

        return AckDto.makeDefault(true);
    }
}
