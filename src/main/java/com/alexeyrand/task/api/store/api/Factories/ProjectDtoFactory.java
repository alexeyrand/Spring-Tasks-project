package com.alexeyrand.task.api.store.api.Factories;


import com.alexeyrand.task.api.store.api.dto.ProjectDto;
import com.alexeyrand.task.api.store.entities.ProjectEntity;
import org.springframework.stereotype.Component;

/**
 * ProjectEntityToDtoConvertor
 */
@Component
public class ProjectDtoFactory {
    public ProjectDto makeProjectDto(ProjectEntity entity) {

//      Lombok builder
        return ProjectDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

}
