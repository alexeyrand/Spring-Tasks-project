package com.alexeyrand.task.api.store.repositories;

import com.alexeyrand.task.api.store.entities.ProjectEntity;
import com.alexeyrand.task.api.store.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    Optional<TaskEntity> findByName(String name);

}
