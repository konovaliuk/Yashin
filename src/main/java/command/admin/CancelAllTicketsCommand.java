package command.admin;

import command.Command;
import dto.Ticket;
import exception.InvalidDataBaseOperation;
import model.entity.User;
import service.RequestService;
import util.Configuration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static command.admin.CommandAdminUtil.TICKETS_ATTRIBUTE;
import static command.admin.CommandAdminUtil.USERNAME_ATTRIBUTE;
import static command.admin.CommandAdminUtil.USER_ATTRIBUTE;


public class CancelAllTicketsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        User userNow = (User) request.getSession(false).getAttribute(USER_ATTRIBUTE);
        if (userNow == null || !userNow.isAdmin())
            return Configuration.getInstance().getConfig(Configuration.LOGIN);

        List<Ticket> tickets = RequestService.getInstance().findAllTickets();
        try {
            RequestService.getInstance().cancelRequest(tickets);
        } catch (InvalidDataBaseOperation e) {
            request.setAttribute("messageError", e.getMessage());
            return Configuration.getInstance().getConfig(Configuration.ERROR);
        }

        request.setAttribute(USERNAME_ATTRIBUTE, userNow.getName());
        request.setAttribute(TICKETS_ATTRIBUTE, RequestService.getInstance().findAllTickets());
        page = Configuration.getInstance().getConfig(Configuration.TICKETS_ADMIN);
        return page;
    }
}
