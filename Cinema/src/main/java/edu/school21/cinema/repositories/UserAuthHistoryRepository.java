package edu.school21.cinema.repositories;

import edu.school21.cinema.models.UserAuthHistory;

import java.util.List;

public interface UserAuthHistoryRepository extends CrudRepository<UserAuthHistory> {
    List<UserAuthHistory> findAllByUserId(Long id);
}
