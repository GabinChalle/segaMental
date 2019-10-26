package model;

import bo.User;
import dal.DAOFactory;
import dal.jdbc.UserDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserBean implements Serializable {

    private static final String FORM_FIELD_LOGIN = "form-username";
    private static final String FORM_FIELD_PWD = "form-password";
    public static final String ATT_SESSION_CONNECTED_USER = "connectedUser";

    private static final String ATT_SESS_USERS_LIST = "usersList";

    private User currentUser;
    private User user;
    private String authResult;
    private Map<String, User> users;
    UserDAO userDAO = new UserDAO();

    public UserBean() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAuthResult() {
        return authResult;
    }

    public void setAuthResult(String authResult) {
        this.authResult = authResult;
    }

    public boolean isConnected(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User connectedUser = (User) session.getAttribute(ATT_SESSION_CONNECTED_USER);
        return connectedUser != null;
    }

    public void loadUserList(HttpServletRequest request) {
        HttpSession session = request.getSession();
        users = (Map<String, User>) session.getAttribute(ATT_SESS_USERS_LIST);
        if (null == users) {
            users = new HashMap<>();
            try {
                users = userDAO.findByAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            session.setAttribute(ATT_SESS_USERS_LIST, users);
        }
    }

    public void authenticate(HttpServletRequest request) {
        String login = request.getParameter(FORM_FIELD_LOGIN);
        String password = request.getParameter(FORM_FIELD_PWD);
        User user = null;
        try {
            user = DAOFactory.getUserDAO().authenticate(login, password);
            if (null != user) {
                request.getSession().setAttribute(ATT_SESSION_CONNECTED_USER, user);
                authResult = "Bienvenue " + login + "!";
            } else {
                user = new User(login, password);
                authResult = "Identification échouée, merci de recommencer...";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            authResult = "Identification échouée : Pb de connexions à la base de données !";
        }
    }

    public boolean setUserFromForm(HttpServletRequest request) {
        String id = request.getParameter("id");
        String name = request.getParameter(FORM_FIELD_LOGIN);
        String password = request.getParameter(FORM_FIELD_PWD);
        loadUserList(request);
        if (users.containsKey(id)) {
            currentUser = users.get(id);
            currentUser.setLogin(name);
            currentUser.setPassword(password);
            try {
                userDAO.create(currentUser);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            currentUser = new User();
            currentUser.setLogin(name);
            currentUser.setPassword(password);
            try {
                userDAO.create(currentUser);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        users.put(id, currentUser);
        return true;
    }

    public boolean deleteCurrentUserRequest(HttpServletRequest request) {
        String id = request.getParameter("id");
        String name = request.getParameter(FORM_FIELD_LOGIN);
        String password = request.getParameter(FORM_FIELD_PWD);
        loadUserList(request);
        if (users.containsKey(id)) {
            currentUser = users.get(id);
            currentUser.setLogin(name);
            currentUser.setPassword(password);
            try {
                userDAO.delete(currentUser);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            users.remove(id, currentUser);
        }
        return true;
    }

    public void loadCurrentUserFromRequest(HttpServletRequest request) {
        String path = request.getServletPath();
        if (-1 != path.indexOf("details")) {
            String id = request.getParameter("id");
            if (null == id) id = "";
            loadUserList(request);
            if (users.containsKey(id)) {
                currentUser = users.get(id);
            }
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }
}
