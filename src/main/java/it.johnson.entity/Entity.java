package it.johnson.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.time.LocalDate;

/**
 * Created by john.mukenge on 28/04/2023.
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@javax.persistence.Entity
public class Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String task;
    private LocalDate dueDate;
    private boolean isCompleted;
    private LocalDate dateCompleted;
    private LocalDate dateCreated;

    //before the entity be persisted into the database, the following method will be called --> lifecycle callbacks
    @PrePersist
    private void init() {
        setDateCreated(LocalDate.now());
    }

}
