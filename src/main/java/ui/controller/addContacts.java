package ui.controller;

import domain.db.DbException;
import domain.model.Contact;
import domain.model.DomainException;
import domain.model.Person;
import domain.model.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class addContacts extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Role[] roles = {Role.CUSTOMER};
        Utility.checkRole(request, roles);

        List<String> errors = new ArrayList<String>();
        Contact contact = new Contact();

        setPersonId(contact, request, errors);
        setFirstName(contact, request, errors);
        setLastName(contact, request, errors);
        setDate(contact, request, errors);
        setGSM(contact, request, errors);
        setEmail(contact, request, errors);
        setFitness(contact, request, errors);

        if (errors.size() == 0) {
            try {
                System.out.println("Service?");
                service.addContact(contact);
                System.out.println("Yes");
                clearPreviousValues(request);
                request.setAttribute("gelukt", "Succesvol een contact toegevoegd");
                request.getRequestDispatcher("Controller?command=Contacts").forward(request, response);
            } catch (DbException | IOException | ServletException e) {
                errors.add(e.getMessage());
            }
        } else {
            try {
                request.setAttribute("errors", errors);
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

    private void setPersonId(Contact contact, HttpServletRequest request, List<String> errors) {
        HttpSession session = request.getSession();
        String personId = ((Person) session.getAttribute("user")).getUserid();
        contact.setPersonId(personId);
    }

    private void setGSM(Contact contact, HttpServletRequest request, List<String> errors) {
        String gsm = request.getParameter("gsm");
        request.setAttribute("gsmVorige", gsm);
        try {
            contact.setGsm(gsm);
            request.setAttribute("nameClass", "has-succes");
        } catch (Exception exc) {
            request.setAttribute("nameClass", "has-error");
            errors.add(exc.getMessage());
        }
    }


    private void setDate(Contact contact, HttpServletRequest request, List<String> errors) {
        String datum = request.getParameter("date").trim();
        String hour = request.getParameter("hour").trim();
        try {
            Timestamp date = Timestamp.valueOf(datum + " " + hour + ":00");
            contact.setDate(date);
            request.setAttribute("dateVorige", datum);
            request.setAttribute("uurVorige", hour);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void setFitness(Contact contact, HttpServletRequest request, List<String> errors) {
        String fitness = request.getParameter("fitness");
        try {
            contact.setFitness(fitness);
            request.setAttribute("nameClass", "has-succes");
        } catch (Exception exc) {
            request.setAttribute("nameClass", "has-error");
            errors.add(exc.getMessage());
        }
    }

    private void setEmail(Contact contact, HttpServletRequest request, List<String> errors) {
        String email = request.getParameter("email");
        request.setAttribute("emailVorige", email);
        try {
            contact.setEmail(email);
            request.setAttribute("nameClass", "has-succes");
        } catch (Exception exc) {
            request.setAttribute("nameClass", "has-error");
            errors.add(exc.getMessage());
        }
    }

    private void setFirstName(Contact contact, HttpServletRequest request, List<String> errors) {
        String firstName = request.getParameter("firstName");
        request.setAttribute("voornaamVorige", firstName);
        try {
            contact.setFirstName(firstName);
            request.setAttribute("nameClass", "has-succes");
        } catch (Exception exc) {
            request.setAttribute("nameClass", "has-error");
            errors.add(exc.getMessage());
        }
    }

    private void setLastName(Contact contact, HttpServletRequest request, List<String> errors) {
        String lastName = request.getParameter("lastName");
        request.setAttribute("naamVorige", lastName);
        try {
            contact.setLastName(lastName);
            request.setAttribute("nameClass", "has-succes");
        } catch (Exception exc) {
            request.setAttribute("nameClass", "has-error");
            errors.add(exc.getMessage());
        }
    }
}
