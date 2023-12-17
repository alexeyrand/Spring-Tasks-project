package com.alexeyrand.task.api.store.api.Factories;


import com.alexeyrand.task.api.store.api.dto.ProjectDto;
import com.alexeyrand.task.api.store.api.dto.TaskStateDto;
import com.alexeyrand.task.api.store.entities.TaskStateEntity;
import org.springframework.stereotype.Component;

/**
 * ProjectEntityToDtoConvertor
 */
@Component
public class TaskStateDtoFactory {

    public TaskStateDto makeTaskStateDto(TaskStateEntity entity) {

//      Lombok builder
        return TaskStateDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createAt(entity.getCreateAt())
                .ordinal(entity.getOrdinal())
                .build();
    }
}
