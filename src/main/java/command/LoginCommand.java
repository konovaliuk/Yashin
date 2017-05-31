package command;

import model.entity.User;
import service.AdminService;
import service.LoginService;
import service.RouteService;
import util.Config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
            page = Config.getInstance().getConfig(Config.REGISTER);
        } else {
            if (user.getPassword().equals(password)) {
                if(user.isAdmin()){
                    page = Config.getInstance().getConfig(Config.ADMIN);
                    request.setAttribute("users", AdminService.getInstance().getAllUsers());
                } else {
                    page = Config.getInstance().getConfig(Config.DATE);
                    request.setAttribute("cityFrom", RouteService.getInstance().findAvailableFromStations());
                    request.setAttribute("cityTo", RouteService.getInstance().findAvailableToStations());
                    request.setAttribute("trains", null);

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    request.setAttribute("d", format.format(new Date()));

                }
            } else {
                request.setAttribute("errorMessage", "Invalid email or password");
                page = Config.getInstance().getConfig(Config.LOGIN);
            }
        }

        return page;
    }
}
