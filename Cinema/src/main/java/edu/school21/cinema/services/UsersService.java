package edu.school21.cinema.services;

import edu.school21.cinema.models.User;

import java.util.Optional;

public interface UsersService {
    Optional<User> signUp(User entity);
    Optional<User> signIn(String email, String password);

    void setAvatar(Long userId, Long avatarId);
}
