package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;
import edu.school21.cinema.models.UserAuthHistory;
import edu.school21.cinema.models.UserAvatar;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class UsersAvatarRepositoryImpl implements UsersAvatarRepository {

    private final JdbcTemplate jdbcTemplate;

    private static String SQL_FIND_BY_ID = "SELECT * FROM user_avatars WHERE id=? LIMIT 1;";
    private static String SQL_FIND_ALL = "SELECT * FROM user_avatars;";
    private static String SQL_SAVE = "INSERT INTO user_avatars (id, file_name, size, mime, user_id) VALUES (?,?,?,?,?);";
    private static String SQL_SAVE_AUTOID = "INSERT INTO user_avatars (file_name, size, mime, user_id) VALUES (?,?,?,?);";
    private static String SQL_UPDATE = "UPDATE user_avatars SET file_name=?, size=?, mime=?, user_id=? WHERE id=?;";
    private static String SQL_DELETE = "DELETE FROM user_avatars WHERE id=?;";
    private static String SQL_FIND_BY_USERID = "SELECT * FROM user_avatars WHERE user_id=?;";

    public UsersAvatarRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private class UserAvatarMapper implements RowMapper<UserAvatar> {

        @Override
        public UserAvatar mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserAvatar result = new UserAvatar();
            result.setId(rs.getLong("id"));
            result.setFileName(rs.getString("file_name"));
            result.setSize(rs.getLong("size"));
            result.setMime(rs.getString("mime"));
            result.setUserId(rs.getLong("user_id"));
            return result;
        }
    }


    @Override
    public Optional<UserAvatar> findById(Long id) {
        List<UserAvatar> query = jdbcTemplate.query(SQL_FIND_BY_ID, new UserAvatarMapper(), id);
        return query.size() == 0 ? Optional.empty() : Optional.of(query.get(0));
    }

    @Override
    public List<UserAvatar> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, new UserAvatarMapper());
    }

    @Override
    public void save(UserAvatar entity) {
        if (entity.getId() != null) {
            jdbcTemplate.update(SQL_SAVE, entity.getId(), entity.getFileName(), entity.getSize(), entity.getMime(), entity.getUserId());
        } else {
            jdbcTemplate.update(SQL_SAVE_AUTOID, entity.getFileName(), entity.getSize(), entity.getMime(), entity.getUserId());
        }
    }

    @Override
    public Long add(UserAvatar entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_SAVE_AUTOID, new String[]{"id"});
            ps.setString(1, entity.getFileName());
            ps.setLong(2, entity.getSize());
            ps.setString(3, entity.getMime());
            ps.setLong(4, entity.getUserId());
            return ps;
        }, keyHolder);
        return (Long)keyHolder.getKey();
    }

    @Override
    public void update(UserAvatar entity) {
        jdbcTemplate.update(SQL_UPDATE, entity.getFileName(), entity.getSize(), entity.getMime(), entity.getUserId(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public List<UserAvatar> findByUserId(Long id) {
        return jdbcTemplate.query(SQL_FIND_BY_USERID, new UserAvatarMapper(), id);
    }
}
