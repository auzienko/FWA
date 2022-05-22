package edu.school21.cinema.services;

import edu.school21.cinema.models.User;

public interface UsersService {
    Long signUp(User entity);
    Long signIn(String email, String password);
}
