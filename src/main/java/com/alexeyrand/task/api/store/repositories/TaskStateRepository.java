package com.alexeyrand.task.api.store.repositories;

import com.alexeyrand.task.api.store.entities.TaskStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskStateRepository extends JpaRepository<TaskStateEntity, Long> {
}
