package ui.controller.handler;

import domain.model.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProductHandler extends RequestHandler{
    public DeleteProductHandler(ShopService service) {
        setService(service);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        getService().deleteProduct(Integer.parseInt(request.getParameter("productId")));
        ProductOverviewHandler handler = new ProductOverviewHandler(getService());
        return handler.handleRequest(request, response);
    }
}
