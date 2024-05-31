package ru.baksnn.project.JokeBot.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.baksnn.project.JokeBot.model.Jokes;
import ru.baksnn.project.JokeBot.service.JokesService;

import java.time.LocalDateTime;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("jokes")
@AllArgsConstructor
public class JokesController {
    private final JokesService jokesService;

    @GetMapping
    public List<Jokes> allJokes() {
        return jokesService.allJokes();
    }

    @GetMapping("/{id}")
    ResponseEntity<Jokes> getJokes(@PathVariable("id") Long id) {
        return jokesService.getJokesById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Jokes> addNewJoke(@RequestBody Jokes jokes) {
        Optional<Jokes> newJoke = jokesService.addNewJoke(jokes);
        return newJoke.map(jm -> ResponseEntity.status(HttpStatus.CREATED).body(jm))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jokes> updateJoke(@PathVariable Long id, @RequestBody Jokes updatedJoke) {
        Optional<Jokes> existingJoke = jokesService.getJokesById(id);

        if (existingJoke.isPresent()) {

            Jokes jokeToUpdate = existingJoke.get();
            jokeToUpdate.setJoke(updatedJoke.getJoke());

            jokeToUpdate.setTimeUpdated(LocalDateTime.now());

            Jokes savedJoke = jokesService.updateJoke(jokeToUpdate);

            return ResponseEntity.ok(savedJoke);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Jokes> deleteJoke(@PathVariable Long id) {
        Optional<Jokes> deleteToJoke = jokesService.getJokesById(id);
        if (deleteToJoke.isPresent()) {
            Jokes jokeToDelete = deleteToJoke.get();
            Jokes deleteJoke = jokesService.deleteJoke(jokeToDelete);
            return ResponseEntity.ok(deleteJoke);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/random")
    public Jokes getRandomJoke() {
        return jokesService.getRandomJoke();
    }
    @GetMapping("/top5")
    public ResponseEntity<List<Jokes>> topFiveJokes() {
        List<Jokes> topFiveJokes = jokesService.topFiveJokes();
        return ResponseEntity.ok(topFiveJokes);
    }
}