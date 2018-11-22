package ui.controller.handler;

import domain.model.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class AddToCartHandler extends RequestHandler {
    ShopService service;
    public AddToCartHandler(ShopService serv){
        service = serv;
    }

    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(3600);
        HashMap<Integer, Integer> prods;

        prods = (HashMap<Integer, Integer>) session.getAttribute("list");

        if(prods == null){
            prods = new HashMap<>();
        }
        String id = request.getParameter("productId");
        int nu = 0;
        try{
            if(prods.containsKey(Integer.valueOf(id))){
                nu = prods.get(Integer.valueOf(id));
            }
        }catch(Exception e){
        }

        prods.put(Integer.valueOf(id),Integer.valueOf("1")+nu);

        session.setAttribute("list", prods);
        return new ProductOverviewHandler(service).handleRequest(request,response);
    }

}
