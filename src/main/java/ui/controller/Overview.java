package ui.controller;

import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class Overview extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<Person> persons  = personService.getAll();
        request.setAttribute("persons", persons);
        return "personoverview.jsp";

        /*
        request.setAttribute("persons", service.getAll());
        return "personoverview.jsp";
         */
    }
}
