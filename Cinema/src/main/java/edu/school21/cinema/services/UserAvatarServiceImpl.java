package edu.school21.cinema.services;

import edu.school21.cinema.models.UserAvatar;
import edu.school21.cinema.repositories.UsersAvatarRepository;

import java.util.List;
import java.util.Optional;

public class UserAvatarServiceImpl implements UserAvatarService {

    private final UsersAvatarRepository usersAvatarRepository;
    private final String pathToSave;

    public UserAvatarServiceImpl(UsersAvatarRepository usersAvatarRepository, String pathToSave) {
        this.usersAvatarRepository = usersAvatarRepository;
        this.pathToSave = pathToSave;
    }

    @Override
    public Long add(UserAvatar entity) {
        return usersAvatarRepository.add(entity);
    }

    @Override
    public List<UserAvatar> getByUserId(Long id) {
        return usersAvatarRepository.findByUserId(id);
    }

    @Override
    public Optional<UserAvatar> get(Long id) {
        return usersAvatarRepository.findById(id);
    }

    @Override
    public String getPathToSave(Long user_id) {
        return pathToSave + user_id + "/";
    }
}
