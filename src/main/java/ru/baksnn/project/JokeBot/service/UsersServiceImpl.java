package ru.baksnn.project.JokeBot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.baksnn.project.JokeBot.model.Users;
import ru.baksnn.project.JokeBot.repository.UsersRepository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository jokesCallRepository;

    @Override
    public Users logJokeCall(Long userId, Long jokeId, String jokeText) {
        Users users = new Users();
        users.setUserId(userId);
        users.setJokeId(jokeId);
        users.setJokeText(jokeText);
        users.setCallTime(LocalDateTime.now());
        return jokesCallRepository.save(users);
    }

    @Override
    public List<Users> topFiveJokes(){
        Map<Long, List<Users>> groupedJokes = jokesCallRepository.findAll().stream()
                .collect(Collectors.groupingBy(Users::getJokeId));

        return groupedJokes.values().stream()
                .map(jokeCalls -> jokeCalls.get(0))
                .sorted(Comparator.comparing(jokeCall -> groupedJokes.get(jokeCall.getJokeId()).size(), Comparator.reverseOrder()))
                .limit(5)
                .collect(Collectors.toList()).reversed();
    }

    @Override
    public Page<Users> getAllJokesPaged(Pageable pageable) {
        return jokesCallRepository.findAll(pageable);
    }

    @Override
    public List<Users> allJokesCalls() {
        return jokesCallRepository.findAll();
    }
}