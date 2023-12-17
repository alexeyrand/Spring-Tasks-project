package com.alexeyrand.task.api.store.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@Builder                                                    // Builder генерит все виды конструкторов на все случаи жизни
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Column(unique = true)
    private String name;
    @Builder.Default
    private Instant createdAt = Instant.now();
    private String description;
}



