package ui.controller;

import domain.model.Contact;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

public class Contacts extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response){
        List<Contact> contacts  = contactService.getAll();
        request.setAttribute("contacts", contacts);
        String popularFitness = contactService.popularFitness();
        request.setAttribute("populairsteFitness", popularFitness);
        return "contacts.jsp";
    }
}