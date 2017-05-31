package controller;

import command.*;

import javax.servlet.http.HttpServletRequest;
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
        commands.put(bundle.getString("command.entry"), new LoginCommand());
        commands.put(bundle.getString("command.register"), new RegisterCommand());
        commands.put(bundle.getString("command.inputFrom"), new FromCommand());
        commands.put(bundle.getString("command.confirm"), new OrderCommand());
        commands.put(bundle.getString("command.adminConfirm"), new AdminCommand());
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
