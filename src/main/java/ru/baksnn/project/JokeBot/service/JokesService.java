package ru.baksnn.project.JokeBot.service;


import ru.baksnn.project.JokeBot.model.Jokes;
import ru.baksnn.project.JokeBot.model.Users;

import java.util.List;
import java.util.Optional;

public interface JokesService {
    List<Jokes> allJokes();

    Optional<Jokes> addNewJoke(Jokes jokes);

    Optional<Jokes> getJokesById(Long id);

    Jokes updateJoke(Jokes jokeToUpdate);

    Jokes deleteJoke(Jokes jokeToDelete);

    Jokes getRandomJoke();

    List<Jokes> topFiveJokes();
}