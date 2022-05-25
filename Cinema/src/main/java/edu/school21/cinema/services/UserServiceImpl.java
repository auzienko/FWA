package edu.school21.cinema.services;

import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class UserServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder bCryptEncoder;

    public UserServiceImpl(UsersRepository usersRepository, PasswordEncoder bCryptEncoder) {
        this.usersRepository = usersRepository;
        this.bCryptEncoder = bCryptEncoder;
    }

    @Override
    public Optional<User> signUp(User entity) {
        Optional<User> tmp = usersRepository.findByEmail(entity.getEmail());
        if (!tmp.isPresent()) {
            entity.setPassword(bCryptEncoder.encode(entity.getPassword()));
            usersRepository.save(entity);
            tmp = usersRepository.findByEmail(entity.getEmail());
            return tmp;
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> signIn(String email, String password) {
        Optional<User> tmp = usersRepository.findByEmail(email);
        if (tmp.isPresent()) {
            if (bCryptEncoder.matches(password, tmp.get().getPassword())) {
                return tmp;
            }
        }
        return Optional.empty();
    }
}
