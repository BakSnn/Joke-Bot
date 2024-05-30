package ru.baksnn.project.JokeBot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.baksnn.project.JokeBot.model.Users;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    @Query(value = "SELECT u.jokeText, COUNT(u.jokeId) as jokeCount " +
            "FROM Users u " +
            "GROUP BY u.jokeText " +
            "ORDER BY jokeCount DESC " +
            "LIMIT 5", nativeQuery = true)
    List<Users> topFiveJokes();
    Page<Users> findAll(Pageable pageable);
}