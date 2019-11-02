package model;

import bo.Calcul;
import bo.Expression;
import bo.Operation;
import bo.User;
import dal.jdbc.ExpressionDAO;
import dal.jdbc.OperationDAO;
import dal.jdbc.UserDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GameBean implements Serializable {

    private Map operations;
    private OperationDAO operationDAO;
    private ExpressionDAO expressionDAO;
    private UserDAO userDAO;
    private static final String ATT_SESS_SCORES_LIST = "scoreslist";
    private Operation currentOperation;
    private ArrayList<Expression> expressions;

    public GameBean() {
        expressions = createExpression();
    }

    public ArrayList<Expression> getExpressions() {
        return expressions;
    }

    public String getBinaryExp() {
        return Calcul.genererCalculBinaire();
    }

    public String getUnaryExp() {
        return Calcul.genererCalculUnaire();
    }
    // Création d'un test avec 10 calculs
    public ArrayList<Expression> createExpression() {
        ArrayList<Expression> test = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String calcul;
            if (i < 5) {
                calcul = Calcul.genererCalculBinaire();
                while ((boolean)(Calcul.calculer(calcul) != (int)Calcul.calculer(calcul))) {
                    calcul = Calcul.genererCalculBinaire();
                }
            } else {
                calcul = Calcul.genererCalculUnaire();
                while ((boolean)(Calcul.calculer(calcul) != (int)Calcul.calculer(calcul))) {
                    calcul = Calcul.genererCalculUnaire();
                }
            }
            Double resAttendu = Calcul.calculer(calcul);
            Expression exp = new Expression();
            exp.setLibelle(calcul);
            exp.setResAttendu(resAttendu);
            test.add(exp);
        }
        return test;
    }

    public void loadScoreList(HttpServletRequest request) {
        userDAO = new UserDAO();
        operationDAO = new OperationDAO();
        HttpSession session = request.getSession();
        operations = (Map<String, Operation>)  session.getAttribute(ATT_SESS_SCORES_LIST);
        if (null == operations) {
            operations = new HashMap<>();
            try {
                operations = operationDAO.findByAll();
                operations.forEach((k,v)->{
                    Operation ope = (Operation) v;
                    try {
                        User u = userDAO.findById((long) ope.getIdUser());
                        ope.setPseudo(u.getLogin());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
            } catch (SQLException e) {
                e.printStackTrace();
            }
            session.setAttribute(ATT_SESS_SCORES_LIST, operations);
        }
    }

    public void loadCurrentOperationFromRequest(HttpServletRequest request) {
        String path = request.getServletPath();
        if (-1 != path.indexOf("details")) {
            String id = request.getParameter("id");
            if (null == id) id = "";
            loadScoreList(request);
            if (operations.containsKey(id)) {
                currentOperation = (Operation) operations.get(id);
            }
        }
    }

    public void operationCreate(Operation operation) throws SQLException {
        operationDAO = new OperationDAO();
        operationDAO.create(operation);
    }
    public void expressionCreate(ArrayList<Expression> expression) throws SQLException {
        expressionDAO = new ExpressionDAO();
        expressionDAO.insertInto(expression);
    }
    public void operationUpdate(Operation operation) throws SQLException {
        expressionDAO = new ExpressionDAO();
        operationDAO.update(operation);
    }

    //getters and setters
    public Map getOperations() {
        return operations;
    }
}
