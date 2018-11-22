package ui.controller.handler;
import domain.model.Product;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public interface ProductHandler{

    void getName(Product p, HttpServletRequest request, ArrayList<String> errors);

    void getDescription(Product p, HttpServletRequest request, ArrayList<String> errors);

    void getPrice(Product p, HttpServletRequest request, ArrayList<String> errors);
}
