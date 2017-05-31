package command;

import dto.TrainRoute;
import model.entity.Route;
import model.entity.Train;
import service.RouteService;
import service.TrainService;
import util.Config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FromCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = Config.getInstance().getConfig(Config.DATE);
        Long from_id = Long.parseLong(request.getParameter("from"));
        Long to_id = Long.parseLong(request.getParameter("to"));
        Integer time = Integer.parseInt(request.getParameter("time"));
        String dateString = request.getParameter("date");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        try {
            date = format.parse(dateString);
            date.setHours(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<TrainRoute> trains = TrainService.getInstance().findTrainsAndRoutes(from_id, to_id, date);

        request.setAttribute("cityFrom", RouteService.getInstance().findAvailableFromStations());
        request.setAttribute("cityTo", RouteService.getInstance().findAvailableToStations());

        request.setAttribute("from", from_id);
        request.setAttribute("to", to_id);
        request.setAttribute("trains", trains);
        if(trains.isEmpty()){
            request.setAttribute("noTrain", true);
        }
        request.setAttribute("d", format.format(date));
        request.setAttribute("time", time);
        return page;
    }
}
