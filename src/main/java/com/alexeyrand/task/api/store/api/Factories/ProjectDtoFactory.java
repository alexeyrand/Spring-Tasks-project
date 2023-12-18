package com.alexeyrand.task.api.store.api.Factories;


import com.alexeyrand.task.api.store.api.dto.ProjectDto;
import com.alexeyrand.task.api.store.entities.ProjectEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    public List<ProjectDto> getProjectsDto(List<ProjectEntity> entities) {

        List<ProjectDto> projectsDto = new ArrayList<>();

        for (ProjectEntity entity : entities) {
//      Lombok builder
            projectsDto.add(ProjectDto.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .createdAt(entity.getCreatedAt())
                    .updatedAt(entity.getUpdatedAt())
                    .build());
        }
        return projectsDto;
    }

}
