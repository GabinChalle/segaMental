package controller;


import model.GameBean;
import model.UserBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(urlPatterns = {"/game", "/test", "/list"})
public class GameController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(GameController.class.getName());
    private static final String PAGE_GAME_JSP = "/WEB-INF/jsp/game.jsp";
    private static final String PAGE_SCORES_LIST_SLT = "/WEB-INF/jsp/score_list.jsp";
    private GameBean bean = new GameBean();

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

        request.setAttribute("gameBean", bean);

        if(request.getServletPath().equals("/list")){
                bean.loadScoreList(request);
            request.getServletContext().getRequestDispatcher(PAGE_SCORES_LIST_SLT).forward(request, response);
        }
        else if (request.getServletPath().equals("/test")) {
            int page = Integer.parseInt(request.getParameter("page"));
            //String action = request.getParameter("suivant");
            //System.out.println(action);
            //Enumeration<String> names = request.getAttributeNames();
            //String oui = "oui";
            if(request.getParameter("next") != null){
                if(request.getParameter("result") != null) {
                    bean.getExpressions().get(page).setResDonnee(Double.parseDouble(request.getParameter("result")));
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

