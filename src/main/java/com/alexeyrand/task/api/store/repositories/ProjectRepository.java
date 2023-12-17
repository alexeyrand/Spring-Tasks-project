package com.alexeyrand.task.api.store.repositories;

import com.alexeyrand.task.api.store.api.dto.ProjectDto;
import com.alexeyrand.task.api.store.entities.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

    Optional<ProjectEntity> findByName(String name);



    @Query( "SELECT name FROM ProjectEntity" )
    public List<String> findAllId();

}
