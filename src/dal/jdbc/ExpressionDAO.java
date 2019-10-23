package dal.jdbc;

import bo.Expression;
import dal.DAOFactory;
import dal.IDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpressionDAO implements IDAO<Long, Expression> {

    private static final String INSERT_QUERY = "INSERT INTO expression(id_calcul, libelle, res_attendu, res_donnee) VALUES (?,?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE expression SETlibelle=?, res_attendu = ?, res_donnee= ? WHERE id_calcul = ?";
    private static final String REMOVE_QUERY = "DELETE * FROM expression WHERE id_calcul= ? ";
    private static final String FIND_QUERY = "SELECT * from expression Where id_calcul = ?";

    @Override
    public void create(Expression object) throws SQLException {
        Connection connection = DAOFactory.getJDBCConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, object.getId());
                ps.setString(2, object.getLibelle());
                ps.setDouble(3, object.getResAttendu());
                ps.setDouble(4, object.getResDonnee());
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
        return expression;
    }

    @Override
    public List<Expression> findByAll() throws SQLException {
        List<Expression> list = new ArrayList<>();
        Connection connection = DAOFactory.getJDBCConnection();
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(FIND_QUERY)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Expression expression;
                        expression = new Expression();
                        expression.setId(rs.getInt("id_op"));
                        expression.setLibelle(rs.getString("libelle"));
                        expression.setResAttendu(rs.getDouble("res_attendu"));
                        expression.setResDonnee(rs.getDouble("res_donnee"));
                        list.add(expression);

                    }
                }
            }
        }
        return list;
    }
}
