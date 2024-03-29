package dal.jdbc;

import bo.Operation;
import dal.DAOFactory;
import dal.IDAO;

import java.sql.*;
import java.util.*;

public class OperationDAO implements IDAO<Long, Operation> {

    private static final String INSERT_QUERY = "INSERT INTO operations(score, id_user) VALUES (?, ?)";
    private static final String UPDATE_QUERY = "UPDATE operations SET score = ? , id_user = ? WHERE id_op= ?";
    private static final String REMOVE_QUERY = "DELETE FROM operations WHERE id_op= ? ";
    private static final String FIND_QUERY = "SELECT * from operations Where id_op = ?";
    private static final String FINDALL_QUERY = "SELECT id_user, id_op, score  FROM operations ORDER BY score DESC LIMIT 10";

    @Override
    public void create(Operation object) throws SQLException {
        Connection connection = DAOFactory.getJDBCConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
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
        connection.close();
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
        connection.close();
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
        connection.close();
    }

    @Override
    public Operation findById(Long aLong) throws SQLException {
        Operation operation = null;
        Connection connection = DAOFactory.getJDBCConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(FIND_QUERY)) {
                ps.setLong(1, aLong);
                try (ResultSet rs = ps.executeQuery()) {
                    if( rs.next()) {
                        operation.setId(rs.getInt("id_op"));
                        operation.setIdUser(rs.getInt("id_user"));
                        operation.setScore(rs.getInt("score"));
                    }
                }
            }
        }
        connection.close();
        return operation;
    }

    @Override
    public Map findByAll() throws SQLException {
        Map<String, Operation> list = new HashMap<>();
        Connection connection = DAOFactory.getJDBCConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(FINDALL_QUERY)) {
                try (ResultSet rs = ps.executeQuery()) {
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
        connection.close();
        return list;
    }
}
