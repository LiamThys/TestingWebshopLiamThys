package ui.controller.handler;

import domain.model.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class DeleteFromCartHandler extends RequestHandler {
    private ShopService service;
    public DeleteFromCartHandler(ShopService serv){
        service = serv;
    }

    public String handleRequest(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        HashMap<Integer, Integer> prods;

        prods = (HashMap<Integer, Integer>) session.getAttribute("list");
        prods.remove(Integer.valueOf(request.getParameter("id")));
        session.setAttribute("list", prods);

        return new MyCartHandler(service).handleRequest(request, response);
    }
}
