package edu.school21.cinema.services;

import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class UserServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder bCryptEncoder;

    public UserServiceImpl(UsersRepository usersRepository, PasswordEncoder bCryptEncoder) {
        this.usersRepository = usersRepository;
        this.bCryptEncoder = bCryptEncoder;
    }

    @Override
    public Long signUp(User entity) {
        Optional<User> tmp = usersRepository.findByEmail(entity.getEmail());
        if (!tmp.isPresent()) {
            entity.setPassword(bCryptEncoder.encode(entity.getPassword()));
            usersRepository.save(entity);
            tmp = usersRepository.findByEmail(entity.getEmail());
            return tmp.isPresent() ? tmp.get().getId() : null;
        }
        return null;
    }

    @Override
    public Long signIn(String email, String password) {
        Optional<User> tmp = usersRepository.findByEmail(email);
        if (tmp.isPresent()) {
            if (bCryptEncoder.matches(password, tmp.get().getPassword())) {
                return tmp.get().getId();
            }
        }
        return null;
    }
}
