package ui.controller.handler;

import domain.model.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteFormPersonHandler extends RequestHandler{
    public DeleteFormPersonHandler(ShopService service) {
        setService(service);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("userid",request.getParameter("userid"));
        return "deletePerson.jsp";
    }
}
