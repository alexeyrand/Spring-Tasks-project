package com.alexeyrand.task.api.store.repositories;

import com.alexeyrand.task.api.store.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskEntityRepository extends JpaRepository<TaskEntity, Long> {
}
