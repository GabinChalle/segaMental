package model;

import bo.Calcul;
import bo.Expression;
import bo.Operation;
import dal.jdbc.OperationDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameBean implements Serializable {

    private Map operations;
    private OperationDAO operationDAO;
    private static final String ATT_SESS_SCORES_LIST = "scorelist";
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
            } else {
                calcul = Calcul.genererCalculUnaire();
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
        HttpSession session = request.getSession();
        operations = (Map<String, Operation>) session.getAttribute(ATT_SESS_SCORES_LIST);
        if (null == operations) {
            operations = new HashMap<>();
            try {
                operations = operationDAO.findByAll();
                System.out.println("/////////////////////////////" + operations);
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

    //getters and setters
    public Map getOperations() {
        return operations;
    }

    public void setOperations(Map operations) {
        this.operations = operations;
    }

    public OperationDAO getOperationDAO() {
        return operationDAO;
    }

    public void setOperationDAO(OperationDAO operationDAO) {
        this.operationDAO = operationDAO;
    }

    public static String getAttSessScoresList() {
        return ATT_SESS_SCORES_LIST;
    }

    public Operation getCurrentOperation() {
        return currentOperation;
    }

    public void setCurrentOperation(Operation currentOperation) {
        this.currentOperation = currentOperation;
    }

    public void setExpressions(ArrayList<Expression> expressions) {
        this.expressions = expressions;
    }
}
