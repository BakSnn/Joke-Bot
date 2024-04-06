package ru.baksnn.project.JokeBot.model;

import jakarta.persistence.*;
import lombok.*;


import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity(name = "jokes") 
@Table(name = "jokes")
public class JokesModel {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jokes_id_seq")
    @SequenceGenerator(name = "jokes_id_seq", sequenceName = "jokes_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "joke")
    private String joke;

    @Column(name = "time_created")
    private Date timeCreated;

    @Column(name = "time_updated")
    private Date timeUpdated;

}
