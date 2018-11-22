package ui.controller.handler;

import domain.model.ShopService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexHandler extends RequestHandler {
    public IndexHandler(ShopService service){
        setService(service);
    }
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        this.setChoice(request, response);

        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies) {
                if (cookie.getName().equals("choice")) {
                    request.setAttribute("choice", cookie);
                    if(cookie.getValue().equals("yes")){
                        request.setAttribute("quoteList","Quotes");
                    }else{
                        request.setAttribute("quoteList","no Quotes");
                    }
                }
            }
        }
        return "index.jsp";
    }

    public void setChoice(HttpServletRequest request, HttpServletResponse response){
        String choice = request.getParameter("choice");
        if(choice != null){
            Cookie cookie;
            switch(choice){
                case "yes":
                    cookie = new Cookie("choice","yes");
                    break;
                default:
                    cookie = new Cookie("choice","no");
            }
            response.addCookie(cookie);
        }
    }
}
