package command.admin;

import command.Command;
import dto.Ticket;
import service.RequestService;
import util.Configuration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CancelTicketsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Ticket> result = new ArrayList<>();
        List<Ticket> tickets = RequestService.getInstance().findAllTickets();
        for(Ticket ticket: tickets){
            if (request.getParameter(ticket.getRequestId().toString()) != null)
                RequestService.getInstance().cancelRequest(ticket);
            else
                result.add(ticket);
        }

        request.setAttribute("tickets", result);
        return Configuration.getInstance().getConfig(Configuration.TICKETS_ADMIN);
    }
}
