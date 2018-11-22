package ui.controller.handler;

import domain.model.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MyCartHandler extends RequestHandler {
    ShopService service;
    public MyCartHandler(ShopService serv){
        service = serv;
    }

    public String handleRequest(HttpServletRequest request, HttpServletResponse response){
        request.setAttribute("service", service);
        return "myCart.jsp";
    }

}
