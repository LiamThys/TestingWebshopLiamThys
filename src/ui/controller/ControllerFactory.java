package ui.controller;
import domain.model.ShopService;
import ui.controller.handler.*;

public class ControllerFactory {
    public RequestHandler getHandler(String action, ShopService service){
        RequestHandler handler;

        switch(action){
            case "indexhandler":
                handler = new IndexHandler(service);
                break;
            case "personoverview":
                handler = new PersonOverviewHandler(service);
                break;
            case "productoverview":
                handler = new ProductOverviewHandler(service);
                break;
            case "signUp":
                handler = new SignUpHandler(service);
                break;
            case "addProduct":
                handler = new AddProductHandler(service);
                break;
            case "updateForm":
                handler = new UpdateFormHandler(service);
                break;
            case "updateProduct":
                handler = new UpdateProductHandler(service);
                break;
            case "deleteForm":
                handler = new DeleteFormHandler(service);
                break;
            case "deleteProduct":
                handler = new DeleteProductHandler(service);
                break;
            case "deleteFormPerson":
                handler = new DeleteFormPersonHandler(service);
                break;
            case "deletePerson":
                handler = new DeletePersonHandler(service);
                break;
            case "setChoice":
                handler = new IndexHandler(service);
                break;
            case "myCart":
                handler = new MyCartHandler(service);
                break;
            case "addToCart":
                handler = new AddToCartHandler(service);
                break;
            case "deleteFromCart":
                handler = new DeleteFromCartHandler(service);
                break;
            default:
                handler = new IndexHandler(service);
        }
        return handler;
    }
}