package command.admin;

import command.Command;
import service.AdminService;
import util.Configuration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UsersCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("users", AdminService.getInstance().getAllUsers());
        return Configuration.getInstance().getConfig(Configuration.ADMIN);
    }
}
