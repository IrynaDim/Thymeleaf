package ua.foxminded.university_cms.repository;

import ua.foxminded.university_cms.model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByLogin(String login);
}
