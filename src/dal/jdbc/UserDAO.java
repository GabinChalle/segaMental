package dal.jdbc;

import bo.User;
import dal.DAOFactory;
import dal.IUserDAO;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserDAO implements IUserDAO<Long, User> {

    private static final String AUTHENT_QUERY = "SELECT * FROM utilisateurs WHERE pseudo = ? AND password = ?";
    private static final String INSERT_QUERY = "INSERT INTO utilisateurs(id_user, pseudo, password) VALUES (?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE utilisateurs SET pseudo = ?, password = ? WHERE id_user = ?";
    private static final String REMOVE_QUERY = "DELETE FROM utilisateurs WHERE id_user= ? ";
    private static final String FIND_QUERY = "SELECT * from utilisateurs Where id_user = ?";
    private static final String FINDALL_QUERY = "SELECT * from utilisateurs";

    @Override
    public User authenticate(String login, String password) throws SQLException {
        Connection connection = DAOFactory.getJDBCConnection();
        User user = null;
        if (null != connection) {
            try (PreparedStatement ps = connection
                    .prepareStatement(AUTHENT_QUERY, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                ps.setString(1, login);
                ps.setString(2, password);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        //int nbConnections = rs.getInt("connections") + 1;
                        //rs.updateInt("connections", nbConnections);
                        rs.updateRow();
                        user = new User();
                        user.setLogin(rs.getString("pseudo"));
                        user.setPassword(rs.getString("password"));
                        //user.setNbConnections(nbConnections);
                    }
                }
            }
        }
        return user;
    }

    @Override
    public void create(User object) throws SQLException {
        Connection connection = DAOFactory.getJDBCConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, object.getId());
                ps.setString(2, object.getLogin());
                ps.setString(3, object.getPassword());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        object.setId(rs.getInt(1));
                    }
                }
            }
        }
    }

    @Override
    public void update(User object) throws SQLException {
        Connection connection = DAOFactory.getJDBCConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY)) {
                ps.setString(1, object.getLogin());
                ps.setString(2, object.getPassword());
                ps.setInt(3, object.getId());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public void delete(User object) throws SQLException {
        Connection connection = DAOFactory.getJDBCConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(REMOVE_QUERY)) {
                ps.setInt(1, object.getId());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public User findById(Long aLong) throws SQLException {
        User user = new User();
        Connection connection = DAOFactory.getJDBCConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(FIND_QUERY)) {
                ps.setLong(1, aLong);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        user.setId(rs.getInt("id_user"));
                        user.setLogin(rs.getString("pseudo"));
                    }
                }
            }

        }
        return user;
    }

    @Override
    public Map findByAll() throws SQLException {
        Map<String, User> list = new HashMap<>();
        Connection connection = DAOFactory.getJDBCConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(FINDALL_QUERY)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        User user;
                        user = new User();
                        user.setId(rs.getInt("id_user"));
                        user.setLogin((rs.getString("pseudo")));
                        user.setPassword(rs.getString("password"));
                        list.put(UUID.randomUUID().toString(), user);
                    }
                }
            }
        }
        return list;
    }
}
