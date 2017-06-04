package command.admin;

import command.Command;
import dto.Ticket;
import service.RequestService;
import util.Configuration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TicketCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Ticket> tickets = RequestService.getInstance().findAllTickets();
        request.setAttribute("tickets", tickets);
        return Configuration.getInstance().getConfig(Configuration.TICKETS_ADMIN);
    }
}
