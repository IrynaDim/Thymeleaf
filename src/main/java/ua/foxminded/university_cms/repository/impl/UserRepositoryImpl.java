package ua.foxminded.university_cms.repository.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import ua.foxminded.university_cms.model.Role;
import ua.foxminded.university_cms.model.User;
import ua.foxminded.university_cms.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    // Imitate db
    private static final List<User> STORAGE = new ArrayList<>();

    // populate storage
    static {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        STORAGE.add(new User(1, "Ivan", "Ivanov", "admin",
                passwordEncoder.encode("1234"), Role.ADMIN));
        STORAGE.add(new User(1, "Olga", "Petrova", "student1",
                passwordEncoder.encode("5678"), Role.STUDENT));
    }

    public Optional<User> findByLogin(String login) {
        return STORAGE.stream()
                .filter(student -> student.getLogin().equals(login))
                .findFirst();
    }
}
