package ui.controller;

import domain.model.DomainException;
import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class addContacts extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<String> result = new ArrayList<String>();
        Person person = new Person();

        setFirstName(person, request, result);
        setLastName(person, request, result);
        setDate(person, request, result);
        setHour(person, request, result);
        setGSM(person, request, result);
        setEmail(person, request, result);
        setFitness(person, request, result);

        String destination;
        if (result.size() > 0) {
            request.setAttribute("result", result);
            destination = "contacts.jsp";
        } else {
            service.add(person);
            destination = "index.jsp";
        }
        return destination;
    }

    private void setGSM(Person person, HttpServletRequest request, List<String> result) {
        String gsm = request.getParameter("gsm");
        request.setAttribute("gsmVorige", gsm);
        try {
            person.setGsm(Integer.parseInt(gsm));
            request.setAttribute("nameClass", "has-succes");
        } catch (DomainException exc) {
            result.add(exc.getMessage());
            request.setAttribute("nameClass", "has-error");
        }
    }

    private void setHour(Person person, HttpServletRequest request, List<String> result) {
        String hour = request.getParameter("hour");
        request.setAttribute("hourVorige", hour);
        try {
            person.setHour(Integer.parseInt(hour));
            request.setAttribute("nameClass", "has-succes");
        } catch (DomainException exc) {
            result.add(exc.getMessage());
            request.setAttribute("nameClass", "has-error");
        }
    }

    private void setDate(Person person, HttpServletRequest request, List<String> result) {
        String date = request.getParameter("date");
        request.setAttribute("dateVorige", date);
        try {
            person.setDate(Integer.parseInt(date));
            request.setAttribute("nameClass", "has-succes");
        } catch (DomainException exc) {
            result.add(exc.getMessage());
            request.setAttribute("nameClass", "has-error");
        }
    }

    private void setFitness(Person person, HttpServletRequest request, List<String> result) {
        String fitness = request.getParameter("fitness");
        try {
            person.setFitness(fitness);
            request.setAttribute("nameClass", "has-succes");
        } catch (Exception exc) {
            result.add(exc.getMessage());
            request.setAttribute("nameClass", "has-error");
        }
    }

    private void setEmail(Person person, HttpServletRequest request, List<String> result) {
        String email = request.getParameter("email");
        request.setAttribute("emailVorige", email);

        try {
            person.setEmail(email);
            request.setAttribute("nameClass", "has-succes");
        } catch (DomainException exc) {
            result.add(exc.getMessage());
            request.setAttribute("nameClass", "has-error");
        }
    }

    private void setFirstName(Person person, HttpServletRequest request, List<String> result) {
        String firstName = request.getParameter("firstName");
        request.setAttribute("voornaamVorige", firstName);
        try {
            person.setFirstName(firstName);
            request.setAttribute("nameClass", "has-succes");
        } catch (DomainException exc) {
            result.add(exc.getMessage());
            request.setAttribute("nameClass", "has-error");
        }
    }

    private void setLastName(Person person, HttpServletRequest request, List<String> result) {
        String lastName = request.getParameter("lastName");
        request.setAttribute("naamVorige", lastName);
        try {
            person.setLastName(lastName);
            request.setAttribute("nameClass", "has-succes");
        } catch (DomainException exc) {
            result.add(exc.getMessage());
            request.setAttribute("nameClass", "has-error");
        }
    }
}
