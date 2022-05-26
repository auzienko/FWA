package edu.school21.cinema.repositories;

import edu.school21.cinema.models.UserAvatar;

import java.util.List;

public interface UsersAvatarRepository extends CrudRepository<UserAvatar> {
    List<UserAvatar> findByUserId(Long id);
    Long add(UserAvatar entity);
}
