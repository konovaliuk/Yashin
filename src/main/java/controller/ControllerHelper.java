package controller;

import command.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ControllerHelper {
    private static ControllerHelper INSTANCE;
    private static final HashMap<String, Command> commands = new HashMap<>();
    private static final String BUNDLE_NAME = "command";
    private ResourceBundle bundle;

    private ControllerHelper(){
        bundle = ResourceBundle.getBundle(BUNDLE_NAME);
        commands.put(bundle.getString("command.entry"), new LoginCommand());
        commands.put(bundle.getString("command.inputFrom"), new FromCommand());
        commands.put(bundle.getString("command.checkCityTime"), new SearchTrainsCommand());
        commands.put(bundle.getString("command.selectTrain"), new SelectTrainCommand());
        commands.put(bundle.getString("command.confirm"), new OrderCommand());
    }

    public static ControllerHelper getInstance(){
        if(INSTANCE == null){
            synchronized (ControllerHelper.class){
                if(INSTANCE == null){
                    INSTANCE = new ControllerHelper();
                }
            }
        }
        return INSTANCE;
    }

    public Command getCommand(HttpServletRequest request){
        Command command = commands.get(request.getParameter("command"));
        if(command == null){
            command = new MissingCommand();
        }
        return command;
    }

}
