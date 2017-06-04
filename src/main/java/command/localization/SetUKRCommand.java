package command.localization;

import command.Command;
import util.Configuration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.Locale;

public class SetUKRCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Config.set(request, Config.FMT_LOCALE, new Locale("uk", "UA"));
        return Configuration.getInstance().getConfig(Configuration.LOGIN);
    }
}
