package ui.controller;

import domain.db.DbException;
import domain.model.CoronaPositiveModel;
import domain.model.DomainException;
import domain.model.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CoronaPositiveAdd extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Role[] roles = {Role.CUSTOMER};
        Utility.checkRole(request, roles);

        List<String> errors = new ArrayList<String>();

        CoronaPositiveModel coronaPositiveModel = new CoronaPositiveModel();
        setDate(coronaPositiveModel, request, errors);
        setId(coronaPositiveModel, request, errors);

        if (errors.size() == 0) {
            try {
                service.addCoronaPositive(coronaPositiveModel);
                request.setAttribute("Gelukt", "Succesvol een positive corona test doorgegeven");
                request.getRequestDispatcher("Controller?command=Index").forward(request, response);
            } catch (DbException | ServletException e) {
                errors.add(e.getMessage());
            }
        } else {
            try {
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("Controller?command=CoronaPositive_Pag").forward(request, response);
            } catch (ServletException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    private void setDate(CoronaPositiveModel coronaPositiveModel, HttpServletRequest request, List<String> result) {
        Timestamp date = Timestamp.valueOf(request.getParameter("date"));
        request.setAttribute("lastDate", date);
        try {
            coronaPositiveModel.setDate(date);
            request.setAttribute("nameClass", "has-succes");
        } catch (DomainException exc) {
            result.add(exc.getMessage());
            request.setAttribute("nameClass", "has-error");
        }
    }

    private void setId(CoronaPositiveModel coronaPositiveModel, HttpServletRequest request, List<String> result) {
        String id = request.getParameter("id");
        request.setAttribute("lastId", id);
        try {
            coronaPositiveModel.setId(id);
            request.setAttribute("nameClass", "has-succes");
        } catch (Exception exc) {
            request.setAttribute("nameClass", "has-error");
            result.add(exc.getMessage());
        }
    }
}