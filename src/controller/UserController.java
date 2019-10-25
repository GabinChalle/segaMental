package controller;


import model.UserBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(urlPatterns = {"/users", "/users/details", "/users/delete", "/user"})
public class UserController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());
    private static final String PAGE_USERS_LIST_JSP = "/WEB-INF/jsp/adminList.jsp";
    private static final String PAGE_USERS_DETAILS_JSP = "/WEB-INF/jsp/users_details.jsp";
    private static final String PAGE_USERS_LIST_SLT = "/user";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserBean bean = new UserBean();
        request.setAttribute("contactBean", bean);

        String path = request.getServletPath();
        LOGGER.log(Level.INFO, "In the doGet for {0}", path);
        String realPath = path.substring(path.lastIndexOf("/") + 1);

        switch (realPath) {
            case "details":
                bean.loadCurrentUserFromRequest(request);
                request.getServletContext().getRequestDispatcher(PAGE_USERS_DETAILS_JSP).forward(request, response);
                break;

            case "users":
            default:
                bean.loadUserList(request);
                request.getServletContext().getRequestDispatcher(PAGE_USERS_LIST_JSP).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserBean bean = new UserBean();
        if (bean.setUserFromForm(request)) {
            response.sendRedirect(request.getContextPath() + PAGE_USERS_LIST_SLT);
        } else {
            request.setAttribute("contactBean", bean);
            request.getServletContext().getRequestDispatcher(PAGE_USERS_DETAILS_JSP).forward(request, response);
        }
    }
}

