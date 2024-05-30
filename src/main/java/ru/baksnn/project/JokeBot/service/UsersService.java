package ru.baksnn.project.JokeBot.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import ru.baksnn.project.JokeBot.model.Users;

import java.util.List;

public interface UsersService {
    Users logJokeCall(Long userId, Long jokeId, String jokeText);

    List<Users> topFiveJokes();

    Page<Users> getAllJokesPaged(Pageable pageable);

    List<Users> allJokesCalls();
}