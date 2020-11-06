package ui.controller;

import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class Login extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<String> result = new ArrayList<String>();

        Person thePerson = getUserid(request, result);
        if (thePerson != null) {
            checkPassword(thePerson, request, result);
        }

        if (result.size() > 0)  request.setAttribute("result", result);
        return "index.jsp";
    }

    private Person getUserid(HttpServletRequest request, List<String> result) {
        String userId = request.getParameter("userid");
        request.setAttribute("loginId", userId);

        List<Person> personList = personService.getAll();
        Person thePerson = null;

        for (Person person :  personList) {
            if (person.getUserid().equals(userId)) {
                thePerson = person;
            }
        }

        if (thePerson == null) {
            result.add("User id not in system");
            request.setAttribute("nameClass", "has-error");
        } else {
            request.setAttribute("nameClass", "has-succes");
        }
        return thePerson;
    }

    private void checkPassword(Person person, HttpServletRequest request, List<String> result) {
        String password = request.getParameter("password");
        if (!person.isCorrectPassword(password)) {
            result.add("Password is wrong");
            request.setAttribute("nameClass", "has-error");
        } else {
            request.setAttribute("nameClass", "has-succes");
            HttpSession session = request.getSession();
            session.setAttribute("user", person.getFirstName());
        }
    }

}


