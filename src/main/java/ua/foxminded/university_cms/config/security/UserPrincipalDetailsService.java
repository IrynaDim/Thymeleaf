package ua.foxminded.university_cms.config.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.foxminded.university_cms.exception.NotFoundException;
import ua.foxminded.university_cms.model.User;
import ua.foxminded.university_cms.repository.UserRepository;

import java.util.Collections;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserPrincipalDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new NotFoundException("User with such login not found: " + login));
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + user.getRole());
        return new org.springframework.security.core.userdetails.User(user.getLogin(),
                user.getPassword(),
                Collections.singleton(grantedAuthority));
    }
}
