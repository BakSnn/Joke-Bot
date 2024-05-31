package ru.baksnn.project.JokeBot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.baksnn.project.JokeBot.model.Users;
import ru.baksnn.project.JokeBot.repository.UsersRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    @Override
    public Users logJokeCall(Long userId, Long jokeId, String jokeText) {
        Users users = new Users();
        users.setUserId(userId);
        users.setJokeId(jokeId);
        users.setJokeText(jokeText);
        users.setCallTime(LocalDateTime.now());
        return usersRepository.save(users);
    }



    @Override
    public List<Users> allJokesCalls() {
        return usersRepository.findAll();
    }

}