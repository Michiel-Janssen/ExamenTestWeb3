package ui.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CoronaPositive_Pag extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            request.getRequestDispatcher("coronaPositive.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}