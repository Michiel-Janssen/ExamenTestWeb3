package ui.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Contacts extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("persons", service.getAll());
        return "contacts.jsp";
    }
}