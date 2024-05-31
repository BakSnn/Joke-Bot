package ru.baksnn.project.JokeBot.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import ru.baksnn.project.JokeBot.model.Users;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
}