package model;

import bo.Calcul;
import bo.Operation;
import dal.jdbc.OperationDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class GameBean implements Serializable {

    private static final String RESULTS = "result";
    private Map operations;
   private OperationDAO operationDAO;
    private static final String ATT_SESS_SCORES_LIST = "scoresList";
   private Operation currentOperation;

    public GameBean() {
    }

    public String getBinaryExp() {
        return Calcul.genererCalculBinaire();
    }

    public String getUnaryExp() {
        return Calcul.genererCalculUnaire();
    }


    public void loadScoreList(HttpServletRequest request) {
        HttpSession session = request.getSession();
        operations = (Map<String, Operation>) session.getAttribute(ATT_SESS_SCORES_LIST);
        if (null == operations) {
            operations = new HashMap<>();
            try {
                operations = operationDAO.findByAll();
                System.out.println(operations);
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
