package ui.controller;

import domain.service.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Service service = new Service();
    private HandlerFactory handlerFactory = new HandlerFactory();

    public Controller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        if(command == null || command.isEmpty())
            command = "Home";
        RequestHandler handler = handlerFactory.getHandler(command, service);

        try {
            handler.handleRequest(request, response);
        } catch (NotAuthorizedException | NoSuchAlgorithmException e) {
            request.setAttribute("notAutorized", "You have insufficient rights to have a look at this page.");
            try {
                handlerFactory.getHandler("Home", service).handleRequest(request,response);
            } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                noSuchAlgorithmException.printStackTrace();
            }
        }
    }
}