package edu.school21.cinema.services;

import edu.school21.cinema.models.UserAvatar;

import java.util.List;
import java.util.Optional;

public interface UserAvatarService {
    Long add(UserAvatar entity);
    List<UserAvatar> getByUserId(Long id);
    Optional<UserAvatar> get(Long id);

    String getPathToSave(Long user_id);
}
