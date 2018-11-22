package ui.controller.handler;

import domain.model.Person;
import domain.model.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class SignUpHandler extends RequestHandler{

    public SignUpHandler(ShopService service){
        setService(service);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<String>();

        Person p = new Person();
        getUserId(p, request, errors);
        getFirstName(p, request, errors);
        getLastName(p, request, errors);
        getMail(p, request, errors);
        getPassword(p, request, errors);

        if (errors.size() == 0) {
            try {
                getService().addPerson(p);
                PersonOverviewHandler handler = new PersonOverviewHandler(getService());
                return handler.handleRequest(request, response);
            } catch (IllegalArgumentException exc) {
                request.setAttribute("errors", exc.getMessage());
                return "personoverview.jsp";
            }
        } else {
            request.setAttribute("errors", errors);
            return "signUp.jsp";
        }
    }
    private void getUserId(Person p, HttpServletRequest request, ArrayList<String> errors) {
        try {
            String userid = request.getParameter("userid");
            p.setUserid(userid);
        } catch (NumberFormatException exc){
            errors.add("No valid value for User id (ex. 0703601)");
        } catch (IllegalArgumentException exc) {
            errors.add(exc.getMessage());
        }
    }

    private void getFirstName(Person p, HttpServletRequest request, ArrayList<String> errors) {
        try {
            String firstName = request.getParameter("firstName");
            p.setFirstName(firstName);
        } catch (IllegalArgumentException exc) {
            errors.add(exc.getMessage());
        }
    }

    private void getLastName(Person p, HttpServletRequest request, ArrayList<String> errors) {
        try {
            String lastName = request.getParameter("lastName");
            p.setLastName(lastName);
        } catch (IllegalArgumentException exc) {
            errors.add(exc.getMessage());
        }
    }

    private void getMail(Person p, HttpServletRequest request, ArrayList<String> errors) {
        try {
            String mail = request.getParameter("email");
            p.setEmail(mail);
        } catch (IllegalArgumentException exc) {
            errors.add(exc.getMessage());
        }
    }

    private void getPassword(Person p, HttpServletRequest request, ArrayList<String> errors) {
        try {
            String passwd = request.getParameter("password");
            p.setPassword(passwd);
        } catch (IllegalArgumentException exc) {
            errors.add(exc.getMessage());
        }
    }
}
