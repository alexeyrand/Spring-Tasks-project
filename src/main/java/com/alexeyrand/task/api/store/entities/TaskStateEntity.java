package com.alexeyrand.task.api.store.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder                                                  // Builder генерит все виды конструкторов на все случаи жизни
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task_state")
public class TaskStateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Column(unique = true)
    private String name;
    private Long ordinal;
    @Builder.Default

    private Instant createAt = Instant.now();

    @Builder.Default
    @OneToMany
    @JoinColumn(name = "task_state_id", referencedColumnName = "id")
    private List<TaskEntity> tasks = new ArrayList<>();    // В момент инициализации объекта может быть такое, что список пустой -> NullPointerEcx;
}
