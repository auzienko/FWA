package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryImpl implements UsersRepository {
    private final JdbcTemplate jdbcTemplate;
    private static String SQL_FIND_BY_ID = "SELECT * FROM users WHERE id=? LIMIT 1;";
    private static String SQL_FIND_ALL = "SELECT * FROM users;";
    private static String SQL_SAVE = "INSERT INTO users (id, first_name, last_name, phone_number, email, password) VALUES (?,?,?,?,?,?);";
    private static String SQL_SAVE_AUTOID = "INSERT INTO users (first_name, last_name, phone_number, email, password) VALUES (?,?,?,?,?);";
    private static String SQL_UPDATE = "UPDATE users SET first_name=?, last_name=?, phone_number=?, email=?, password=? WHERE id=?;";
    private static String SQL_DELETE = "DELETE FROM users WHERE id=?;";
    private static String SQL_FIND_BY_EMAIL = "SELECT * FROM users WHERE email=? LIMIT 1;";

    public UsersRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User result = new User();
            result.setId(rs.getLong("id"));
            result.setFirstName(rs.getString("first_name"));
            result.setLastName(rs.getString("last_name"));
            result.setPhoneNumber(rs.getString("phone_number"));
            result.setEmail(rs.getString("email"));
            result.setPassword(rs.getString("password"));
            return result;
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        List<User> query = jdbcTemplate.query(SQL_FIND_BY_ID, new UserMapper(), id);
        return query.size() == 0 ? Optional.empty() : Optional.of(query.get(0));
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, new UserMapper());
    }

    @Override
    public void save(User entity) {
        if (entity.getId() != null) {
            jdbcTemplate.update(SQL_SAVE, entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getPhoneNumber(), entity.getEmail(), entity.getPassword());
        } else {
            jdbcTemplate.update(SQL_SAVE_AUTOID, entity.getFirstName(), entity.getLastName(), entity.getPhoneNumber(), entity.getEmail(), entity.getPassword());
        }
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update(SQL_UPDATE, entity.getFirstName(), entity.getLastName(), entity.getPhoneNumber(), entity.getEmail(), entity.getPassword(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        List<User> query = jdbcTemplate.query(SQL_FIND_BY_EMAIL, new UserMapper(), email);
        return query.size() == 0 ? Optional.empty() : Optional.of(query.get(0));
    }
}
