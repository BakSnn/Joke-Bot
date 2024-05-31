package ru.baksnn.project.JokeBot.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.baksnn.project.JokeBot.model.Users;
import ru.baksnn.project.JokeBot.repository.UsersRepository;
import ru.baksnn.project.JokeBot.service.UsersService;

import java.util.List;

@RestController
@AllArgsConstructor
public class UsersController {

    private final UsersService usersService;
    @GetMapping("/callJokes")
    public ResponseEntity<List<Users>> getJokesCalls() {
        List<Users> users = usersService.allJokesCalls();
        return ResponseEntity.ok(users);
    }

}