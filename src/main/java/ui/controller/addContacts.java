package ui.controller;

import domain.model.Contact;
import domain.model.DomainException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class addContacts extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<String> result = new ArrayList<String>();
        Contact contact = new Contact();

        setFirstName(contact, request, result);
        setLastName(contact, request, result);
        setDate(contact, request, result);
        setHour(contact, request, result);
        setGSM(contact, request, result);
        setEmail(contact, request, result);
        setFitness(contact, request, result);

        String destination;
        if (result.size() > 0) {
            request.setAttribute("result", result);
            destination = "contacts.jsp";
        } else {
            contactService.add(contact);
            destination = "Controller?command=Contacts";
        }
        return destination;
    }

    private void setGSM(Contact contact, HttpServletRequest request, List<String> result) {
        String gsm = request.getParameter("gsm");
        request.setAttribute("gsmVorige", gsm);
        try {
            contact.setGsm(Integer.parseInt(gsm));
            request.setAttribute("nameClass", "has-succes");
        } catch (DomainException exc) {
            result.add(exc.getMessage());
            request.setAttribute("nameClass", "has-error");
        }
    }

    private void setHour(Contact contact, HttpServletRequest request, List<String> result) {
        String hour = request.getParameter("hour");
        request.setAttribute("hourVorige", hour);
        try {
            contact.setHour(hour);
            request.setAttribute("nameClass", "has-succes");
        } catch (DomainException exc) {
            result.add(exc.getMessage());
            request.setAttribute("nameClass", "has-error");
        }
    }

    private void setDate(Contact contact, HttpServletRequest request, List<String> result) {
        String date = request.getParameter("date");
        request.setAttribute("dateVorige", date);
        try {
            contact.setDate(date);
            request.setAttribute("nameClass", "has-succes");
        } catch (DomainException exc) {
            result.add(exc.getMessage());
            request.setAttribute("nameClass", "has-error");
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
