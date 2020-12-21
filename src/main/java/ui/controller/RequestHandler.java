package ui.controller;

import domain.service.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class RequestHandler {

    protected Service service;

    public abstract void handleRequest (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

    public void setModel (Service service) {
        this.service = service;
    }

    public Service getService() {
        return service;
    }
}
