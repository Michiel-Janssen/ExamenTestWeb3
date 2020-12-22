package ui.controller;

import domain.db.DbException;
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
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            String id = request.getParameter("userid").trim();
            Person person = service.get(id);

            if (person != null && person.isCorrectHashedPassword(request.getParameter("password"))) {
                HttpSession session = request.getSession();
                request.setAttribute("gelukt", "Succesvol ingelogd");
                session.setAttribute("user", person);
            } else {
                request.setAttribute("fout", "Wrong password");
                request.setAttribute("loginId", id);
            }
        } catch (DbException | NoSuchAlgorithmException e) {
            request.setAttribute("fout", "ID Doesn't Exist");
        }
        try {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}


