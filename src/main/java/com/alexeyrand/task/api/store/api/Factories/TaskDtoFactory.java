package com.alexeyrand.task.api.store.api.Factories;

import com.alexeyrand.task.api.store.api.dto.ProjectDto;
import com.alexeyrand.task.api.store.api.dto.TaskDto;
import com.alexeyrand.task.api.store.entities.ProjectEntity;
import com.alexeyrand.task.api.store.entities.TaskEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * ProjectEntityToDtoConvertor
 */
@Component
public class TaskDtoFactory {

    public TaskDto makeTaskDto(TaskEntity entity) {
        return TaskDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createdAt(entity.getCreatedAt())
                .description(entity.getDescription())
                .build();
    }


    public List<TaskDto> getTasksDto(List<TaskEntity> entities) {

        List<TaskDto> tasksDto = new ArrayList<>();

        for (TaskEntity entity : entities) {
//      Lombok builder
            tasksDto.add(TaskDto.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .description(entity.getDescription())
                    .createdAt(entity.getCreatedAt())
                    .build());
        }
        return tasksDto;
    }

}


