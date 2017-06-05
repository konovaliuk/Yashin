package command;

import model.entity.User;
import service.AdminService;
import service.LoginService;
import service.RouteService;
import util.Configuration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginCommand implements Command {
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        String email = request.getParameter(EMAIL).trim();
        String password = request.getParameter(PASSWORD).trim();

        User user = LoginService.getInstance().isPresentLogin(email);

        if(user == null){
            page = redirectToErrorPage(request);
        } else {
            if (user.getPassword().equals(password)) {
                if(user.isAdmin()){
                    page = redirectToAdminPage(request, user);
                } else {
                    page = redirectToUserPage(request, user);
                }
            } else {
                page = redirectToErrorPage(request);
            }
        }


        return page;
    }

    private String redirectToRegister(HttpServletRequest request){
        return Configuration.getInstance().getConfig(Configuration.REGISTER);
    }

    private String redirectToAdminPage(HttpServletRequest request, User user){
        HttpSession session = request.getSession(false);
        session.setAttribute("user", user);

        request.setAttribute("users", AdminService.getInstance().getAllUsers());
        request.setAttribute("username", user.getName());
        return Configuration.getInstance().getConfig(Configuration.ADMIN);
    }

    private String redirectToUserPage(HttpServletRequest request, User user){
        HttpSession session = request.getSession(false);
        session.setAttribute("user", user);
        session.setMaxInactiveInterval(30*60);

        request.setAttribute("cityFrom", RouteService.getInstance().findAvailableFromStations());
        request.setAttribute("cityTo", RouteService.getInstance().findAvailableToStations());
        request.setAttribute("trains", null);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        request.setAttribute("dateNow", format.format(new Date()));

        request.setAttribute("username", user.getName());
        return Configuration.getInstance().getConfig(Configuration.DATE);
    }

    private String redirectToErrorPage(HttpServletRequest request){
        request.setAttribute("errorMessage", true);
        return Configuration.getInstance().getConfig(Configuration.LOGIN);
    }
}
