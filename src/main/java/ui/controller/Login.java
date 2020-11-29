package ui.controller;

import domain.model.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Login extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, NoSuchAlgorithmException {
        List<String> result = new ArrayList<String>();

        Person thePerson = getUserid(request, result);
        if (thePerson != null) {
            checkPassword(thePerson, request, result);
        }

        if (result.size() > 0)  request.setAttribute("result", result);
        try{
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    //getters

    private Person getUserid(HttpServletRequest request, List<String> result) {
        String userId = request.getParameter("userid");
        request.setAttribute("loginId", userId);

        List<Person> personList = service.getPersonAll();
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
        System.out.println(thePerson.getUserid());
        return thePerson;
    }

    //Extra

    private void checkPassword(Person person, HttpServletRequest request, List<String> result) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String password = request.getParameter("password");
        System.out.println(person.getPassword());
        if (!person.isCorrectHashedPassword(password)) {
            result.add("Password is wrong");
            request.setAttribute("nameClass", "has-error");
        } else {
            request.setAttribute("nameClass", "has-succes");
            HttpSession session = request.getSession();
            session.setAttribute("user", person);
        }
    }

}


