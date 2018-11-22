package ui.controller.handler;

import domain.model.DomainException;
import domain.model.Product;
import domain.model.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class AddProductHandler extends RequestHandler implements ProductHandler{
    public AddProductHandler(ShopService service) {
        setService(service);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<>();

        Product p = new Product();
        getName(p, request, errors);
        getDescription(p, request, errors);
        getPrice(p, request, errors);

        if (errors.size() == 0) {
            try {
                getService().addProduct(p);
                ProductOverviewHandler handler = new ProductOverviewHandler(getService());
                return handler.handleRequest(request, response);
            } catch (DomainException exc) {
                request.setAttribute("errors", exc.getMessage());
                return "productoverview.jsp";
            }
        } else {
            request.setAttribute("errors", errors);
            return "addProduct.jsp";
        }
    }

    public void getName(Product p, HttpServletRequest request, ArrayList<String> errors) {
        try {
            String name = request.getParameter("name");
            p.setName(name);
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
        }
    }

    public void getDescription(Product p, HttpServletRequest request, ArrayList<String> errors) {
        try {
            String description = request.getParameter("description");
            p.setDescription(description);
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
        }
    }


    public void getPrice(Product p, HttpServletRequest request, ArrayList<String> errors) {
        try {
            String price = request.getParameter("price");
            p.setPrice(price);
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
        }catch (NumberFormatException exc){
            errors.add("Price has to be a number");
        }
    }
}