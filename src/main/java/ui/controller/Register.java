package ui.controller;

import domain.model.DomainException;
import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class Register extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<String> result = new ArrayList<String>();
        Person person = new Person();

        setUserId(person, request, result);
        setFirstName(person, request, result);
        setLastName(person, request, result);
        setEmail(person, request, result);
        setPassword(person, request, result);
        setFitness(person, request, result);

        String destination;
        if (result.size() > 0) {
            request.setAttribute("result", result);
            destination = "register.jsp";
        } else {
            service.add(person);
            destination = "Controller?command=Overview";
        }
        return destination;
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

    private void setUserId(Person person, HttpServletRequest request, List<String> result) {
        String userid = request.getParameter("userid");
        request.setAttribute("lastId", userid);
        try {
            person.setUserid(userid);
            request.setAttribute("nameClass", "has-succes");
        } catch (Exception exc) {
            result.add(exc.getMessage());
            request.setAttribute("nameClass", "has-error");
        }
    }

    private void setEmail(Person person, HttpServletRequest request, List<String> result) {
        String email = request.getParameter("email");
        request.setAttribute("lastEmail", email);

        try {
            person.setEmail(email);
            request.setAttribute("nameClass", "has-succes");
        } catch (DomainException exc) {
            result.add(exc.getMessage());
            request.setAttribute("nameClass", "has-error");
        }
    }

    private void setPassword(Person person, HttpServletRequest request, List<String> result) {
        String password = request.getParameter("password");
        request.setAttribute("lastPassword", password);
        try {
            person.setPassword(password);
            request.setAttribute("nameClass", "has-succes");
        } catch (DomainException exc) {
            result.add(exc.getMessage());
            request.setAttribute("nameClass", "has-error");
        }
    }

    private void setFirstName(Person person, HttpServletRequest request, List<String> result) {
        String firstName = request.getParameter("firstName");
        request.setAttribute("lastFirstName", firstName);
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
        request.setAttribute("lastLastName", lastName);
        try {
            person.setLastName(lastName);
            request.setAttribute("nameClass", "has-succes");
        } catch (DomainException exc) {
            result.add(exc.getMessage());
            request.setAttribute("nameClass", "has-error");
        }
    }
}
