package controller;

import command.*;
import command.admin.*;
import command.localization.SetENCommand;
import command.localization.SetUKRCommand;
import command.user.BookTicketsCommand;
import command.user.MainPageCommand;
import command.user.SelectCityDateTimeCommand;
import command.user.MakeTicketsCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.jstl.core.Config;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ControllerHelper {
    private Map<String, Command> commands = new HashMap<>();
    private static final String BUNDLE_NAME = "command";
    private static ControllerHelper INSTANCE;

    private ResourceBundle bundle;

    private ControllerHelper() {
        bundle = ResourceBundle.getBundle(BUNDLE_NAME);
        commands.put(bundle.getString("command.login"), new LoginCommand());
        commands.put(bundle.getString("command.register"), new RegisterCommand());
        commands.put(bundle.getString("command.selectDateTime"), new SelectCityDateTimeCommand());
        commands.put(bundle.getString("command.make"), new MakeTicketsCommand());
        commands.put(bundle.getString("command.logout"), new LogoutCommand());
        commands.put(bundle.getString("command.book"), new BookTicketsCommand());
        commands.put(bundle.getString("command.en"), new SetENCommand());
        commands.put(bundle.getString("command.ukr"), new SetUKRCommand());

        commands.put(bundle.getString("command.adminUser"), new ChangeUsersCommand());
        commands.put(bundle.getString("command.tickets"), new TicketCommand());
        commands.put(bundle.getString("command.users"), new UsersCommand());
        commands.put(bundle.getString("command.main"), new MainPageCommand());

        commands.put(bundle.getString("command.cancel"), new CancelTicketsCommand());
        commands.put(bundle.getString("command.cancelAll"), new CancelAllTicketsCommand());
    }

    public static ControllerHelper getInstance() {
        if (INSTANCE == null) {
            synchronized (ControllerHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ControllerHelper();
                }
            }
        }
        return INSTANCE;
    }

    public Command getCommand(HttpServletRequest request) {
        String commandString = request.getParameter("command");
        Command command = commands.get(commandString);
        if (command == null) {
            command = new MissingCommand();
        }
        return command;
    }

}
