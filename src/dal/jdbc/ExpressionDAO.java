package dal.jdbc;

import bo.Expression;
import dal.DAOFactory;
import dal.IDAO;

import java.sql.*;
import java.util.*;

public class ExpressionDAO implements IDAO<Long, Expression> {

    private static final String INSERT_QUERY = "INSERT INTO expressions(libelle, res_attendu, res_donnee, id_op) VALUES (?,?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE expressions SET libelle=?, res_attendu = ?, res_donnee= ? WHERE id_calcul = ?";
    private static final String REMOVE_QUERY = "DELETE FROM expressions WHERE id_calcul= ? ";
    private static final String FIND_QUERY = "SELECT * from expressions Where id_calcul = ?";
    private static final String FINDALL_QUERY = "SELECT * from expressions";
    private  static final  String INSERT_INTO = "INSERT INTO expressions(libelle, res_attendu, res_donnee, id_op) VALUES (?, ?, ? , ?), (?, ?, ? , ?),(?, ?, ? , ?),(?, ?, ? , ?),(?, ?, ? , ?),(?, ?, ? , ?),(?, ?, ? , ?),(?, ?, ? , ?),(?, ?, ? , ?),(?, ?, ? , ?)";

    @Override
    public void create(Expression object) throws SQLException {
        Connection connection = DAOFactory.getJDBCConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, object.getLibelle());
                ps.setDouble(2, object.getResAttendu());
                ps.setDouble(3, object.getResDonnee());
                ps.setInt(4, object.getIdOp());
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
    public void insertInto(ArrayList<Expression> object) throws SQLException {
        Connection connection = DAOFactory.getJDBCConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(INSERT_INTO, Statement.RETURN_GENERATED_KEYS)) {
                int i=0;
                for (Expression expression : object){
                    ps.setString(i, expression.getLibelle());
                    i++;
                    ps.setDouble(i, expression.getResAttendu());
                    i++;
                    ps.setDouble(i, expression.getResDonnee());
                    i++;
                    ps.setInt(i, expression.getIdOp());
                    i++;
                    ps.executeUpdate();
                }

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        for (Expression expression : object) {
                            expression.setId(rs.getInt(1));
                        }
                    }
                }
            }
        }
        connection.close();
    }

    @Override
    public void update(Expression object) throws SQLException {
        Connection connection = DAOFactory.getJDBCConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY)) {
                ps.setString(1, object.getLibelle());
                ps.setDouble(2, object.getResAttendu());
                ps.setDouble(3, object.getResDonnee());
                ps.executeUpdate();
            }
        }
        connection.close();
    }

    @Override
    public void delete(Expression object) throws SQLException {
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
    public Expression findById(Long aLong) throws SQLException {
        Expression expression = null;
        Connection connection = DAOFactory.getJDBCConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(FIND_QUERY)) {
                ps.setLong(1, aLong);
                try (ResultSet rs = ps.executeQuery()) {
                    expression.setId(rs.getInt("id_calcul"));
                }
            }
        }
        connection.close();
        return expression;
    }

    @Override
    public Map findByAll() throws SQLException {
        Map<String, Expression> list = new HashMap<>();
        Connection connection = DAOFactory.getJDBCConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(FINDALL_QUERY)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Expression expression;
                        expression = new Expression();
                        expression.setId(rs.getInt("id_calcul"));
                        expression.setLibelle(rs.getString("libelle"));
                        expression.setResAttendu(rs.getDouble("res_attendu"));
                        expression.setResDonnee(rs.getDouble("res_donnee"));
                        list.put(UUID.randomUUID().toString(), expression);
                    }
                }
            }
        }
        connection.close();
        return list;
    }
}
