package ru.baksnn.project.JokeBot.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity(name = "users")
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq", sequenceName = "users_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "joke_id")
    private Long jokeId;

    @Column(name = "call_time")
    private LocalDateTime callTime;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "joke_text")
    private String jokeText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "joke_id", insertable = false, updatable = false)
    private Jokes jokes;

}