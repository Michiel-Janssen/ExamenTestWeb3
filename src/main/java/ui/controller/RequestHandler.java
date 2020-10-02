package ui.controller;

import domain.db.PersonService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class RequestHandler {

    protected PersonService service;

    public abstract String handleRequest (HttpServletRequest request, HttpServletResponse response);

    public void setModel (PersonService personService) {
        this.service = personService;
    }

    public PersonService getService() {
        return service;
    }


}
