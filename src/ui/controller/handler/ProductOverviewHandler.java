package ui.controller.handler;

import domain.model.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductOverviewHandler extends RequestHandler {

    public ProductOverviewHandler(ShopService service){
        setService(service);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("products", getService().getProducts());
        return "productoverview.jsp";
    }
}
