package ui.controller;

import domain.db.DbException;
import domain.model.Contact;
import domain.model.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public class SearchContact extends RequestHandler {
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Role[] roles = {Role.ADMIN};
        Utility.checkRole(request, roles);

        List<Contact> contacts = service.getContactAll();
        List<Contact> activeContacts = new ArrayList<>();
        List<String> errors = new ArrayList<>();

        String datum = request.getParameter("datum");

        if(datum.isEmpty()) {
            errors.add("Vul datum in");
        } else {
            Timestamp date = Timestamp.valueOf(datum);
            for(Contact c : contacts) {
                if(c.getDate().equals(date)) {
                    activeContacts.add(c);
                }
            }
        }

        if (errors.size() == 0) {
            try {
                request.setAttribute("contacts", activeContacts);
                request.getRequestDispatcher("contacts.jsp").forward(request, response);
            } catch (DbException | ServletException e) {
                errors.add(e.getMessage());
            }
        } else {
            try {
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("contacts.jsp").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
    }
}
