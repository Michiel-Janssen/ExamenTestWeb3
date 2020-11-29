package ui.controller;

import domain.db.DbException;
import domain.model.CoronaPositiveModel;
import domain.model.DomainException;
import domain.model.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CoronaPositiveAdd extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Role[] roles = {Role.CUSTOMER};
        Utility.checkRole(request, roles);

        List<String> result = new ArrayList<String>();
        CoronaPositiveModel coronaPositiveModel = new CoronaPositiveModel();

        setDate(coronaPositiveModel, request, result);
        setId(coronaPositiveModel, request, result);

        if (result.size() > 0) {
            try {
                service.addCoronaPositive(coronaPositiveModel);
                clearPreviousValues(request);
                response.sendRedirect("Controller?command=CoronaOverview");
            } catch (DbException e) {
                result.add(e.getMessage());
            }
        } else {
            try {
                request.setAttribute("result", result);
                request.getRequestDispatcher("Controller?command=CoronaOverview").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }

    }

    private void clearPreviousValues(HttpServletRequest request) {
        request.removeAttribute("voornaamVorige");
        request.removeAttribute("naamVorige");
        request.removeAttribute("datumVorige");
        request.removeAttribute("uurVorige");
        request.removeAttribute("gsmVorige");
        request.removeAttribute("emailVorige");
    }

    private void setDate(CoronaPositiveModel coronaPositiveModel, HttpServletRequest request, List<String> result) {
        String date = request.getParameter("date");
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