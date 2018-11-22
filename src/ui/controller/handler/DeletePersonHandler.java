package ui.controller.handler;

import domain.model.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeletePersonHandler extends RequestHandler{
    public DeletePersonHandler(ShopService service) {
        setService(service);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        getService().deletePerson(request.getParameter("userid"));
        PersonOverviewHandler handler = new PersonOverviewHandler(getService());
        return handler.handleRequest(request, response);
    }
}