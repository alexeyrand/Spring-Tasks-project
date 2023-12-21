package com.alexeyrand.task.api.store.api.controllers;

import com.alexeyrand.task.api.store.api.Factories.TaskDtoFactory;
import com.alexeyrand.task.api.store.api.controllers.helpers.ControllerHelper;
import com.alexeyrand.task.api.store.api.dto.TaskDto;
import com.alexeyrand.task.api.store.api.exceptions.BadRequestException;
import com.alexeyrand.task.api.store.entities.ProjectEntity;
import com.alexeyrand.task.api.store.entities.TaskEntity;
import com.alexeyrand.task.api.store.entities.TaskStateEntity;
import com.alexeyrand.task.api.store.repositories.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor                                           // Заменяет конструктор и autowired
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
@RestController
public class TaskController {
    final TaskDtoFactory taskDtoFactory;
    final TaskRepository taskRepository;
    final ControllerHelper controllerHelper;

    public static final String GET_TASKS = "/api/projects/{project_id}/tasks-states/{task_state_id}/tasks";
    public static final String CREATE_TASKS = "/api/projects/{project_id}/tasks-states/{task_state_id}/tasks";
    public static final String CREATE_PROJECT = "/api/projects";
    public static final String EDIT_PROJECT = "/api/projects/{project_id}";
    public static final String DELETE_PROJECT = "/api/projects/{project_id}";

    @GetMapping(GET_TASKS)
    public List<TaskDto> getTasks(@PathVariable(name = "project_id") Long pid, @PathVariable(name = "task_state_id") Long sid) {

        TaskStateEntity taskState = controllerHelper.getTaskStateOrThrowException(sid);
        List<TaskEntity> tasks = taskState.getTasks();
        System.out.println(sid);
        System.out.println(taskState.getTasks());

        return taskDtoFactory.getTasksDto(tasks);
    }

    @PostMapping(CREATE_TASKS)
    public TaskDto createTasks(@PathVariable(name = "project_id") Long pid,
                               @PathVariable(name = "task_state_id") Long sid,
                               @RequestParam String taskName,
                               @RequestParam String description) {

        if (taskName.trim().isEmpty()) {
            throw new BadRequestException("Task state name can't be empty.");
        }

        TaskStateEntity taskState = controllerHelper.getTaskStateOrThrowException(sid);

        taskRepository
                .findByName(taskName)
                .ifPresent(project -> {
                    throw new BadRequestException
                            (String.format("Project \"%s\" already exists", taskName));
                });

        TaskEntity task = taskRepository.saveAndFlush(
                TaskEntity.builder()
                        .name(taskName)
                        .description(description)
                        .taskState(taskState)
                        .build()
        );

        return taskDtoFactory.makeTaskDto(task);
    }

}




