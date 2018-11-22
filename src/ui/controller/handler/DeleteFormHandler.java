package ui.controller.handler;

import domain.model.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteFormHandler extends RequestHandler{
    public DeleteFormHandler(ShopService service) {
        setService(service);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("productId",Integer.parseInt(request.getParameter("productId")));
        return "deleteProduct.jsp";
    }
}
