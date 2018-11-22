package ui.controller.handler;

import domain.model.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PersonOverviewHandler extends RequestHandler{

    public PersonOverviewHandler(ShopService service) {
        setService(service);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("persons", getService().getPersons());
        return "personoverview.jsp";
    }
}
