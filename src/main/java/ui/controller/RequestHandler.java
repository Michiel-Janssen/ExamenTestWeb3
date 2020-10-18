package ui.controller;

import domain.service.PersonService;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class RequestHandler {

    protected PersonService service;

    public abstract String handleRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    public void setModel (PersonService personService) {
        this.service = personService;
    }

    public PersonService getService() {
        return service;
    }


}
