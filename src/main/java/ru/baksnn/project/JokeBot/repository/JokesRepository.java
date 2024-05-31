package ru.baksnn.project.JokeBot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.baksnn.project.JokeBot.model.Jokes;
import ru.baksnn.project.JokeBot.model.Users;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface JokesRepository extends JpaRepository<Jokes, Long> {
    @Query(value = "SELECT j.id, j.joke, j.time_created, j.time_updated, COUNT(u.joke_id) as jokeCount " +
            "FROM jokes j " +
            "LEFT JOIN users u ON j.id = u.joke_id " +
            "GROUP BY j.joke, j.id " +
            "ORDER BY jokeCount DESC " +
            "LIMIT 5", nativeQuery = true)
    List<Jokes> topFiveJokes();

    @Query(value = "SELECT j.* FROM jokes j ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Jokes getRandomJoke();

    Page<Jokes> findAll(Pageable pageable);
}
