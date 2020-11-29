package ui.controller;

import domain.db.DbException;
import domain.model.Contact;
import domain.model.DomainException;
import domain.model.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class addContacts extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Role[] roles = {Role.ADMIN};
        Utility.checkRole(request, roles);

        List<String> result = new ArrayList<String>();
        Contact contact = new Contact();

        setFirstName(contact, request, result);
        setLastName(contact, request, result);
        setDate(contact, request, result);
        setGSM(contact, request, result);
        setEmail(contact, request, result);
        setFitness(contact, request, result);

        if (result.size() > 0) {
            try {
                service.addContact(contact);
                clearPreviousValues(request);
                response.sendRedirect("Controller?command=Contacts");
            } catch (DbException | IOException e) {
                result.add(e.getMessage());
            }
        } else {
            try {
                request.setAttribute("result", result);
                request.getRequestDispatcher("Controller?command=Contacts").forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void clearPreviousValues(HttpServletRequest request) {
        request.removeAttribute("voornaamVorige");
        request.removeAttribute("naamVorige");
        request.removeAttribute("dateVorige");
        request.removeAttribute("uurVorige");
        request.removeAttribute("gsmVorige");
        request.removeAttribute("emailVorige");
    }

    //Setters

    private void setGSM(Contact contact, HttpServletRequest request, List<String> result) {
        String gsm = request.getParameter("gsm");
        request.setAttribute("gsmVorige", gsm);
        try {
            contact.setGsm(gsm);
            request.setAttribute("nameClass", "has-succes");
        } catch (DomainException exc) {
            result.add(exc.getMessage());
            request.setAttribute("nameClass", "has-error");
        }
    }


    private void setDate(Contact contact, HttpServletRequest request, List<String> result) {
        String datum = request.getParameter("date").trim();
        String hour = request.getParameter("hour").trim();
        try {
            Timestamp date = Timestamp.valueOf(datum + " " + hour + ":00");
            contact.setDate(date);
            request.setAttribute("dateVorige", datum);
            request.setAttribute("uurVorige", hour);
        } catch (Exception e) {
            result.add(e.getMessage());
        }
    }

    private void setFitness(Contact contact, HttpServletRequest request, List<String> result) {
        String fitness = request.getParameter("fitness");
        try {
            contact.setFitness(fitness);
            request.setAttribute("nameClass", "has-succes");
        } catch (Exception exc) {
            result.add(exc.getMessage());
            request.setAttribute("nameClass", "has-error");
        }
    }

    private void setEmail(Contact contact, HttpServletRequest request, List<String> result) {
        String email = request.getParameter("email");
        request.setAttribute("emailVorige", email);
        try {
            contact.setEmail(email);
            request.setAttribute("nameClass", "has-succes");
        } catch (DomainException exc) {
            result.add(exc.getMessage());
            request.setAttribute("nameClass", "has-error");
        }
    }

    private void setFirstName(Contact contact, HttpServletRequest request, List<String> result) {
        String firstName = request.getParameter("firstName");
        request.setAttribute("voornaamVorige", firstName);
        try {
            contact.setFirstName(firstName);
            request.setAttribute("nameClass", "has-succes");
        } catch (DomainException exc) {
            result.add(exc.getMessage());
            request.setAttribute("nameClass", "has-error");
        }
    }

    private void setLastName(Contact contact, HttpServletRequest request, List<String> result) {
        String lastName = request.getParameter("lastName");
        request.setAttribute("naamVorige", lastName);
        try {
            contact.setLastName(lastName);
            request.setAttribute("nameClass", "has-succes");
        } catch (DomainException exc) {
            result.add(exc.getMessage());
            request.setAttribute("nameClass", "has-error");
        }
    }
}
