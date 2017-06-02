package controller;

import command.Command;
import util.Config;
import util.Message;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class Controller extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(Controller.class.getName());
    ControllerHelper controllerHelper = ControllerHelper.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = null;
        try {
            Command command = controllerHelper.getCommand(request);
            page = command.execute(request, response);

        } catch (ServletException e) {
            LOG.severe(e.getMessage());
            request.setAttribute("messageError", Message.getInstance().getMessage(Message.SERVLET_EXCEPTION));
            page = Config.getInstance().getConfig(Config.ERROR);

        } catch (IOException e) {
            LOG.severe(e.getMessage());
            request.setAttribute("messageError", Message.getInstance().getMessage(Message.IO_EXCEPTION));
            page = Config.getInstance().getConfig(Config.ERROR);

        } catch (Exception e){
            LOG.severe(e.getMessage());
            request.setAttribute("messageError", Message.getInstance().getMessage(Message.EXCEPTION));
            page = Config.getInstance().getConfig(Config.ERROR);

        }

        if (page == null){
            request.setAttribute("messageError", Message.getInstance().getMessage(Message.PAGE_IS_NULL));
            page = Config.getInstance().getConfig(Config.ERROR);
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }


}
