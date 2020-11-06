package ui.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogOut extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response){
        request.getSession().invalidate();
        return "index.jsp";
    }
}

