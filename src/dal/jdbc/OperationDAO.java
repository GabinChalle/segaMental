package dal.jdbc;

import bo.Operation;
import bo.Operation;
import dal.DAOFactory;
import dal.IDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OperationDAO implements IDAO<Long, Operation> {

    private static final String INSERT_QUERY = "INSERT INTO operation(id_op, score) VALUES (?, ?)";
    private static final String UPDATE_QUERY = "UPDATE operation SET score = ? WHERE id = ?";
    private static final String REMOVE_QUERY = "DELETE * FROM operation WHERE id= ? ";
    private static final String FIND_QUERY = "SELECT * from operation Where id = ?";

    @Override
    public void create(Operation object) throws SQLException {
        Connection connection = DAOFactory.getJDBCConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, object.getId());
                ps.setInt(2, object.getScore());
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
    public List<Operation> findByAll() throws SQLException {
        List<Operation> list = new ArrayList<>();
        Connection connection = DAOFactory.getJDBCConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(FIND_QUERY)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Operation operation;
                        operation = new Operation();
                        operation.setId(rs.getInt("id_op"));
                        operation.setScore(rs.getInt("score"));
                        list.add(operation);
                    }
                }
            }
        }
        return list;
    }
}
