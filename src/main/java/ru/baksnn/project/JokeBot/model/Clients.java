package ru.baksnn.project.JokeBot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "clients")
@Table(name = "clients")
@Getter
@Setter
@ToString
public class Clients implements UserDetails {
    @Id
    @SequenceGenerator(sequenceName = "clients_id_seq", name = "clients_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clients_id_seq")
    private Long id;
    private String username;
    private String password;
    private boolean expired;
    private boolean locked;
    private boolean enabled;

    @OneToMany(mappedBy = "clients", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ClientsRole> clientsRoles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return clientsRoles.stream().map(ClientsRole::getClientsAuthority).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !expired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
    @Setter
    @Getter
    private ClientsAuthority authority;



}