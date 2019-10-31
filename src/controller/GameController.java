package controller;


import bo.Expression;
import bo.Operation;
import dal.jdbc.ExpressionDAO;
import dal.jdbc.OperationDAO;
import model.GameBean;
import model.UserBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Request;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(urlPatterns = {"/game", "/test", "/list", "/score"})
public class GameController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(GameController.class.getName());
    private static final String PAGE_GAME_JSP = "/WEB-INF/jsp/game.jsp";
    private static final String PAGE_SCORES_LIST_SLT = "/WEB-INF/jsp/score_list.jsp";
    private static final String PAGE_SCORE = "/WEB-INF/jsp/score.jsp";
    private GameBean bean = new GameBean();
    private UserBean userBean = new UserBean();
    //private OperationDAO operationDAO = new OperationDAO();
    //private ExpressionDAO expressionDAO = new ExpressionDAO();
    private int id = 0;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("gameBean", bean);

        String path = request.getServletPath();
        LOGGER.log(Level.INFO, "In the doGet for {0}", path);
        //String realPath = path.substring(path.lastIndexOf("/") + 1);
        request.getServletContext().getRequestDispatcher(PAGE_GAME_JSP).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userBean = ( UserBean ) request.getAttribute( "userBean" );
        request.setAttribute("gameBean", bean);
        if(request.getServletPath().equals("/score")){
            System.out.println("doPOst gameControler");
            try {
                int score = 0;
                Operation operation = new Operation(score,1);
                //System.out.println(operation.getId());
                bean.operationCreate(operation);
                //System.out.println("operation "+operation.getId()+" "+operation.getIdUser()+" "+operation.getScore());
                ArrayList<Expression> expressions = bean.getExpressions();
                //System.out.println("expressions "+ expressions);
                 for (Expression exp: expressions) {
                    if (exp.getResDonnee() == exp.getResAttendu()) {
                        score ++;
                    }
                    exp.setIdOp(operation.getId());
                    //System.out.println("exp : "+exp.getId()+" "+exp.getIdOp()+ " "+exp.getResDonnee()+" "+ exp.getResAttendu()+ "  // "+exp.getLibelle());
                    try {
                        bean.expressionCreate(exp);
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                }
                //System.out.println("operation "+operation.getId()+" "+operation.getIdUser()+" "+operation.getScore());
                operation.setScore(score);
                //System.out.println("operation.getId()"+ operation.getId());

                bean.operationUpdate(operation);
                id++;
                request.setAttribute("expressions", expressions);
                request.setAttribute("score", score);

            }catch(Exception e){
                e.printStackTrace();
            }
            request.getServletContext().getRequestDispatcher(PAGE_SCORE).forward(request, response);
        } else if(request.getServletPath().equals("/list")){
            System.out.println("doPOst gameControler");
            try {
                bean.loadScoreList(request);
            }catch(Exception e){
                e.printStackTrace();
            }
            request.getServletContext().getRequestDispatcher(PAGE_SCORES_LIST_SLT).forward(request, response);
        }
        else if (request.getServletPath().equals("/test")) {
            int page = Integer.parseInt(request.getParameter("page"));
            //String action = request.getParameter("suivant");
            //System.out.println(action);
            //Enumeration<String> names = request.getAttributeNames();
            //String oui = "oui";
            if(request.getParameter("next") != null){
                if(!request.getParameter("result").equals("")) {
                    bean.getExpressions().get(page-1).setResDonnee(Double.parseDouble(request.getParameter("result")));
                }
                page ++;
            }
            else if(request.getParameter("precedent") != null){
                page --;
            }
            bean.getBinaryExp();
            request.getServletContext().getRequestDispatcher(PAGE_GAME_JSP + "?page=" + page).forward(request, response);
        }
        /*else {
            request.setAttribute("contactBean", bean);
            request.getServletContext().getRequestDispatcher(PAGE_CONTACTS_DETAILS_JSP).forward(request, response);
        }*/
    }
}

