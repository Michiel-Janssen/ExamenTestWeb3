package ui.controller;

import domain.model.CoronaPositiveModel;
import domain.model.Person;
import domain.model.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class filterContacts extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Role[] roles = {Role.ADMIN};
        Utility.checkRole(request, roles);

        try {
            String fromAsString = request.getParameter("from");
            String untilAsString = request.getParameter("until");
            Timestamp from = Timestamp.valueOf(fromAsString + " 00:00:00");
            Timestamp until = Timestamp.valueOf(untilAsString + " 00:00:00");
            List<CoronaPositiveModel> coronaPositiveModels  = service.getCoronaPositiveAllFiltered(from, until);
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
    }
}
