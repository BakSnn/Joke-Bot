package ru.baksnn.project.JokeBot.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.baksnn.project.JokeBot.model.Jokes;
import ru.baksnn.project.JokeBot.repository.JokesRepository;

import java.time.LocalDateTime;
import java.util. *;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JokesServiceImpl implements JokesService {
    private final JokesRepository jokesRepository;
    private final Random random = new Random();


    @Override
    public List<Jokes> allJokes() {
        List<Jokes> jokesList = jokesRepository.findAll();
        jokesList.forEach(joke -> {
        });
        return jokesList;
    }

    public Optional<Jokes> addNewJoke(Jokes newJoke) {
        newJoke.setTimeCreated(LocalDateTime.now());
        newJoke.setTimeUpdated(LocalDateTime.now());
        try {
            Jokes savedJoke = jokesRepository.save(newJoke);
            return Optional.of(savedJoke);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
    public Jokes updateJoke(Jokes updatedJoke) {

        Optional<Jokes> existingJoke = jokesRepository.findById(updatedJoke.getId());

        if (existingJoke.isPresent()) {

            Jokes jokeToUpdate = existingJoke.get();
            jokeToUpdate.setJoke(updatedJoke.getJoke());
            jokeToUpdate.setTimeUpdated(LocalDateTime.now());

            return jokesRepository.save(jokeToUpdate);
        } else {
            throw new NoSuchElementException("Шутка с " + updatedJoke.getId() + " ID не найдена");
        }
    }
    public Jokes deleteJoke(Jokes deletedJoke) {
        Optional<Jokes> existingJoke = jokesRepository.findById(deletedJoke.getId());
        if (existingJoke.isPresent()) {
            jokesRepository.deleteById(deletedJoke.getId());
            return existingJoke.get();
        } else {
            throw new NoSuchElementException("Шутка с " + deletedJoke.getId() + " ID не найдена");
        }
    }
    public Optional<Jokes> getJokesById(Long id) {
        List<Jokes> allJokes = allJokes();
        return allJokes.stream()
                .filter(joke -> joke.getId().equals(id))
                .findFirst();
    }
    public Jokes getRandomJoke() {
        return jokesRepository.getRandomJoke();
    }

    public List<Jokes> topFiveJokes() {
        return jokesRepository.topFiveJokes();
    }


}