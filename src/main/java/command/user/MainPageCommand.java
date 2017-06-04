package command.user;

import command.Command;
import service.RouteService;
import util.Configuration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainPageCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("cityFrom", RouteService.getInstance().findAvailableFromStations());
        request.setAttribute("cityTo", RouteService.getInstance().findAvailableToStations());
        request.setAttribute("trains", null);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        request.setAttribute("d", format.format(new Date()));
        return Configuration.getInstance().getConfig(Configuration.DATE);
    }
}
