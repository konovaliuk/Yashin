package command;

import model.entity.User;
import service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginCommand implements Command {
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);

        User user = LoginService.getInstance().isPresentLogin(email);
        if(user == null){
            page = "/login.jsp";
        } else {
            page = "/register.jsp";
        }

        return page;
    }
}
