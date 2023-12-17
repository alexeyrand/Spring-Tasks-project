package com.alexeyrand.task.api.store.api.Factories;

import com.alexeyrand.task.api.store.api.dto.TaskDto;
import com.alexeyrand.task.api.store.entities.TaskEntity;
import org.springframework.stereotype.Component;

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

}
