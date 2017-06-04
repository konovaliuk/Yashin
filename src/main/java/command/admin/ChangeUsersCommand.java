package command.admin;

import command.Command;
import model.entity.User;
import service.AdminService;
import util.Configuration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ChangeUsersCommand implements Command {
    private static final String DELETE = "delete";
    private static final String ADMIN = "admin";
    private static final String USER = "user";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;

        List<User> users = AdminService.getInstance().getAllUsers();
        for(User user: users){
            String action = request.getParameter(user.getId().toString());
            if(action.equals(DELETE)){
                AdminService.getInstance().deleteUser(user);
            }
            if (action.equals(ADMIN)){
                user.makeAdmin();
                AdminService.getInstance().updateUser(user);
            }
            if (action.equals(USER)){
                user.makeUser();
                AdminService.getInstance().updateUser(user);
            }
        }

        users = AdminService.getInstance().getAllUsers();
        request.setAttribute("users", users);
        page = Configuration.getInstance().getConfig(Configuration.ADMIN);
        return page;
    }
}
