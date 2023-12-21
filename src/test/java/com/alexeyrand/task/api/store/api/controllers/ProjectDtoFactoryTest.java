package com.alexeyrand.task.api.store.api.controllers;

import com.alexeyrand.task.api.store.api.Factories.TaskDtoFactory;
import com.alexeyrand.task.api.store.entities.ProjectEntity;
import com.alexeyrand.task.api.store.repositories.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;


public class ProjectDtoFactoryTest {

    TaskDtoFactory taskDtoFactory;
    ProjectRepository projectRepository;

    @Test
    public void getProjectsDto(List<ProjectEntity> entities) {

    }
}
