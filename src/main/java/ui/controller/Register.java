package ui.controller;

import domain.db.DbException;
import domain.model.DomainException;
import domain.model.Person;
import domain.model.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Register extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Role[] roles = {Role.ADMIN};
        Utility.checkRole(request, roles);

        List<String> result = new ArrayList<String>();
        Person person = new Person();

        setUserId(person, request, result);
        setFirstName(person, request, result);
        setLastName(person, request, result);
        setPassword(person, request, result);
        setEmail(person, request, result);
        setRole(person, request, result);

        if(result.size() == 0) {
            try {
                service.addPerson(person);
                response.sendRedirect("Controller?command=Overview");
            } catch (DbException | IOException e) {
                result.add(e.getMessage());
            }
        } else {
            try {
                request.setAttribute("result", result);
                request.getRequestDispatcher("Controller?command=Register").forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }


    //Setters

    private void setRole(Person person, HttpServletRequest request, List<String> result) {
        String roleAsString = request.getParameter("role").trim();
        try {
            Role role = Role.valueOf(roleAsString.toUpperCase());
            person.setRole(role);
            request.setAttribute("lastRole", roleAsString);
        } catch (Exception e) {
            result.add(e.getMessage());
        }
    }

    private void setUserId(Person person, HttpServletRequest request, List<String> result) {
        String userid = request.getParameter("userid");
        request.setAttribute("lastId", userid);
        try {
            person.setUserid(userid);
            request.setAttribute("nameClass", "has-succes");
        } catch (Exception exc) {
            request.setAttribute("nameClass", "has-error");
            result.add(exc.getMessage());
        }
    }

    private void setEmail(Person person, HttpServletRequest request, List<String> result) {
        String email = request.getParameter("email");
        request.setAttribute("lastEmail", email);

        try {
            person.setEmail(email);
            request.setAttribute("nameClass", "has-succes");
        } catch (DomainException exc) {
            request.setAttribute("nameClass", "has-error");
            result.add(exc.getMessage());
        }
    }

    private void setPassword(Person person, HttpServletRequest request, List<String> result) {
        String password = request.getParameter("password");
        request.setAttribute("lastPassword", password);
        try {
            person.setPasswordHashed(password);
            request.setAttribute("nameClass", "has-succes");
        } catch (DomainException exc) {
            request.setAttribute("nameClass", "has-error");
            result.add(exc.getMessage());
        }
    }

    private void setFirstName(Person person, HttpServletRequest request, List<String> result) {
        String firstName = request.getParameter("firstName");
        request.setAttribute("lastFirstName", firstName);
        try {
            person.setFirstName(firstName);
            request.setAttribute("nameClass", "has-succes");
        } catch (DomainException exc) {
            request.setAttribute("nameClass", "has-error");
            result.add(exc.getMessage());
        }
    }

    private void setLastName(Person person, HttpServletRequest request, List<String> result) {
        String lastName = request.getParameter("lastName");
        request.setAttribute("lastLastName", lastName);
        try {
            person.setLastName(lastName);
            request.setAttribute("nameClass", "has-succes");
        } catch (DomainException exc) {
            request.setAttribute("nameClass", "has-error");
            result.add(exc.getMessage());
        }
    }
}
