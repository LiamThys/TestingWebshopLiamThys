package ui.controller.handler;

import domain.model.Product;
import domain.model.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateFormHandler extends RequestHandler{
    public UpdateFormHandler(ShopService service) {
        setService(service);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Product p = getService().getProduct(Integer.parseInt(request.getParameter("productId")));
        request.setAttribute("productId",p.getProductId());
        request.setAttribute("name",p.getName());
        request.setAttribute("description",p.getDescription());
        request.setAttribute("price",p.getPrice());
        return "updateProduct.jsp";
    }
}
