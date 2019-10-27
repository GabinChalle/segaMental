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

    public GameBean() {
    }

    public String getBinaryExp() {
        return Calcul.genererCalculBinaire();
    }

    public String getUnaryExp() {
        return Calcul.genererCalculUnaire();
    }

    // Création d'un test avec 10 calculs
    public ArrayList<Expression> createTest() {
        ArrayList<Expression> test = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String calcul;
            if(i<5) {
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
                System.out.println("/////////////////////////////"+operations);
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
}
