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

public class Controller extends HttpServlet {
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
            if(request.getSession().getAttribute("user") == null){
                page =  Config.getInstance().getConfig(Config.LOGIN);
            } else {
                page = command.execute(request, response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
            request.setAttribute("messageError", Message.getInstance().getMessage(Message.SERVLET_EXCEPTION));

        } catch (IOException e) {
            e.printStackTrace();
            request.setAttribute("messageError", Message.getInstance().getMessage(Message.IO_EXCEPTION));

        }

        response.setContentType("text/html");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }


}
