package command.user;

import command.Command;
import model.entity.User;
import service.RouteService;
import util.Configuration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static command.user.CommandUserAdmin.USERNAME_ATTRIBUTE;
import static command.user.CommandUserAdmin.USER_ATTRIBUTE;

public class MainPageCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User userNow = (User) request.getSession(false).getAttribute(USER_ATTRIBUTE);
        if(userNow == null)
            return Configuration.getInstance().getConfig(Configuration.LOGIN);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        request.setAttribute("cityFrom", RouteService.getInstance().findAvailableFromStations());
        request.setAttribute("cityTo", RouteService.getInstance().findAvailableToStations());
        request.setAttribute("trains", null);
        request.setAttribute(USERNAME_ATTRIBUTE, userNow.getName());
        request.setAttribute("dateNow", format.format(new Date()));
        return Configuration.getInstance().getConfig(Configuration.DATE);
    }
}
