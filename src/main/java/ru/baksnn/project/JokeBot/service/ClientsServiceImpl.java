package ru.baksnn.project.JokeBot.service;

import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetailsService;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.baksnn.project.JokeBot.exceptions.UsernameAlreadyExistsException;
import ru.baksnn.project.JokeBot.model.Clients;
import ru.baksnn.project.JokeBot.model.ClientsAuthority;
import ru.baksnn.project.JokeBot.model.ClientsRole;
import ru.baksnn.project.JokeBot.repository.ClientsRepository;
import ru.baksnn.project.JokeBot.repository.ClientsRolesRepository;

import java.util.List;


@RequiredArgsConstructor
@Service
public class ClientsServiceImpl implements ClientsService, UserDetailsService {
    private final ClientsRolesRepository clientsRolesRepository;
    private final ClientsRepository clientsRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void registration(String username, String password) {
        if (!clientsRepository.findByUsername(username).isPresent()) {
            Clients user = clientsRepository.save(
                    new Clients()
                            .setId(null)
                            .setUsername(username)
                            .setPassword(passwordEncoder.encode(password))
                            .setLocked(false)
                            .setExpired(false)
                            .setEnabled(true)
            );
            clientsRolesRepository.save(new ClientsRole(null, ClientsAuthority.DEFAULT_USER, user));
        }
        else {
            throw new UsernameAlreadyExistsException();
        }
    }
    @Override
    public List<ClientsRole> getUserRoles(String username) {
        Clients user = clientsRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return user.getClientsRoles();
    }

    @Override
    @Transactional
    public void setUserRole(String username, ClientsRole newRole) {
        Clients user = clientsRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        // Установить новую роль
        newRole.setClients(user);
        clientsRolesRepository.save(newRole);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return clientsRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}