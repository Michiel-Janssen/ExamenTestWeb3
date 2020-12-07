package ui.controller;

import domain.model.Contact;

import domain.model.CoronaPositiveModel;
import domain.model.Person;
import domain.model.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CoronaOverview extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Role[] roles = {Role.ADMIN};
        Utility.checkRole(request, roles);

        try {
            List<CoronaPositiveModel> coronaPositiveModels  = service.getCoronaPositiveAll();
            List<Person> persons  = service.getPersonAll();
            List<Person> coronaPersons = new ArrayList<>();
            List<String> corona = new ArrayList<>();
            for(CoronaPositiveModel c : coronaPositiveModels) {
                for(Person p : persons) {
                    if(c.getId().equals(p.getUserid())) {
                        coronaPersons.add(p);
                        corona.add(c.getDate());
                    }
                }
            }
            request.setAttribute("contactPersons", coronaPersons);
            request.setAttribute("coronadatums", corona);
            request.getRequestDispatcher("coronaOverview.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }

        /*Person p = (Person) request.getSession().getAttribute("user");
        try {
            String email = p.getEmail();
            List<Contact> contacts = service.getPossibleCorona(email);
            request.setAttribute("contacts", contacts);
            request.getRequestDispatcher("coronaOverview.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }

         */
    }
}
