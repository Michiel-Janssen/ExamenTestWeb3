package ui.controller;

import domain.service.ContactService;
import domain.service.PersonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class RequestHandler {

    protected PersonService personService;
    protected ContactService contactService;

    public abstract String handleRequest (HttpServletRequest request, HttpServletResponse response);

    public void setModel (PersonService personService, ContactService contactService) {
        this.personService = personService;
        this.contactService = contactService;
    }
}
