package ui.controller;

import domain.model.Contact;
import domain.model.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class Contacts extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response){
        Role[] roles = {Role.ADMIN};
        Utility.checkRole(request, roles);

        try {
            List<Contact> contacts = service.getContactAll();
            request.setAttribute("contacts", contacts);
            String populairsteFitness = service.popularFitness();
            request.setAttribute("populairsteFitness", populairsteFitness);
            request.getRequestDispatcher("contacts.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}