package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;
import edu.school21.cinema.models.UserAuthHistory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserAuthHistoryRepositoryImpl implements UserAuthHistoryRepository {
    private final JdbcTemplate jdbcTemplate;

    private static String SQL_FIND_BY_ID = "SELECT * FROM user_auth_history WHERE id=? LIMIT 1;";
    private static String SQL_FIND_ALL = "SELECT * FROM user_auth_history;";
    private static String SQL_SAVE = "INSERT INTO user_auth_history (id, date_time, ip, user_id) VALUES (?,?,?,?);";
    private static String SQL_SAVE_AUTOID = "INSERT INTO user_auth_history (date_time, ip, user_id) VALUES (?,?,?);";
    private static String SQL_UPDATE = "UPDATE user_auth_history SET date_time=?, ip=?, user_id=? WHERE id=?;";
    private static String SQL_DELETE = "DELETE FROM user_auth_history WHERE id=?;";
    private static String SQL_FIND_BY_USERID = "SELECT * FROM user_auth_history WHERE user_id=?;";

    public UserAuthHistoryRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private class UserAuthHistoryMapper implements RowMapper<UserAuthHistory> {

        @Override
        public UserAuthHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserAuthHistory userAuthHistory = new UserAuthHistory();
            userAuthHistory.setId(rs.getLong("id"));
            userAuthHistory.setIp(rs.getString("ip"));
            userAuthHistory.setUserId(rs.getLong("user_id"));
            Object date_time_tmp = rs.getObject("date_time");
            if (date_time_tmp == null) {
                userAuthHistory.setDateTime(null);
            } else {
                userAuthHistory.setDateTime(rs.getTimestamp("date_time").toLocalDateTime());
            }
            return userAuthHistory;
        }
    }

    @Override
    public Optional<UserAuthHistory> findById(Long id) {
        List<UserAuthHistory> query = jdbcTemplate.query(SQL_FIND_BY_ID, new UserAuthHistoryMapper(), id);
        return query.size() == 0 ? Optional.empty() : Optional.of(query.get(0));
    }

    @Override
    public List<UserAuthHistory> findAll() {
        return jdbcTemplate.query(SQL_FIND_BY_ID, new UserAuthHistoryMapper());
    }

    @Override
    public void save(UserAuthHistory entity) {
        if (entity.getId() != null) {
            jdbcTemplate.update(SQL_SAVE, entity.getId(), entity.getDateTime(), entity.getIp(), entity.getUserId());
        } else {
            jdbcTemplate.update(SQL_SAVE_AUTOID, entity.getDateTime(), entity.getIp(), entity.getUserId());
        }
    }

    @Override
    public void update(UserAuthHistory entity) {
        jdbcTemplate.update(SQL_UPDATE, entity.getDateTime(), entity.getIp(), entity.getUserId(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public List<UserAuthHistory> findAllByUserId(Long id) {
        return jdbcTemplate.query(SQL_FIND_BY_USERID, new UserAuthHistoryMapper(), id);
    }
}
