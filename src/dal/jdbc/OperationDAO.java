package dal.jdbc;

import bo.Operation;
import bo.User;
import dal.DAOFactory;
import dal.IDAO;

import java.sql.*;
import java.util.*;

public class OperationDAO implements IDAO<Long, Operation> {

    private static final String INSERT_QUERY = "INSERT INTO operations(score, id_user) VALUES (?, ?)";
    private static final String UPDATE_QUERY = "UPDATE operations SET score = ? , id_user = ? WHERE id_op= ?";
    private static final String REMOVE_QUERY = "DELETE FROM operations WHERE id_op= ? ";
    private static final String FIND_QUERY = "SELECT * from operations Where id_op = ?";
    //private static final String FINDALL_QUERY = "SELECT pseudo, score FROM operations inner join utilisateurs on operations.id_user = utilisateurs.id_user";
    private static final String FINDALL_QUERY = "SELECT * FROM operations";

    @Override
    public void create(Operation object) throws SQLException {
        Connection connection = DAOFactory.getJDBCConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
                // ps.setInt(1, object.getId());
                ps.setInt(1, object.getScore());
                ps.setInt(2, object.getIdUser());
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
    public void update(Operation object) throws SQLException {
        Connection connection = DAOFactory.getJDBCConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY)) {
                ps.setInt(1, object.getScore());
                ps.setInt(2, object.getIdUser());
                ps.setInt(3, object.getId());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public void delete(Operation object) throws SQLException {
        Connection connection = DAOFactory.getJDBCConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(REMOVE_QUERY)) {
                ps.setInt(1, object.getId());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public Operation findById(Long aLong) throws SQLException {
        Operation operation = null;
        Connection connection = DAOFactory.getJDBCConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(FIND_QUERY)) {
                ps.setLong(1, aLong);
                try (ResultSet rs = ps.executeQuery()) {
                    operation.setId(rs.getInt("id_op"));
                }
            }
        }
        return operation;
    }

    @Override
    public Map findByAll() throws SQLException {
        System.out.println("BONJOUR je suis find by all");
        Map<String, Operation> list = new HashMap<>();
        Connection connection = DAOFactory.getJDBCConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(FINDALL_QUERY)) {
                System.out.println("11"+ps);
                try (ResultSet rs = ps.executeQuery()) {
                    System.out.println("12"+rs);
                    System.out.println("BONJOUR je suis dans le find by all");
                    while (rs.next()) {
                        Operation operation;
                        operation = new Operation();
                        operation.setId(rs.getInt("id_op"));
                        operation.setScore(rs.getInt("score"));
                        operation.setIdUser(rs.getInt("id_user"));
                        list.put(UUID.randomUUID().toString(), operation);
                    }
                }
            }
        }
        return list;
    }
}
