package ui.controller;

import domain.model.Contact;

import domain.model.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CoronaOverview extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Role[] roles = {Role.ADMIN};
        Utility.checkRole(request, roles);

        Contact c = (Contact) request.getSession().getAttribute("user");
        try {
            String email = c.getEmail();
            List<Contact> contacts = service.getPossibleCorona(email);
            request.setAttribute("coronaPositives", contacts);
            request.getRequestDispatcher("coronaOverview.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
