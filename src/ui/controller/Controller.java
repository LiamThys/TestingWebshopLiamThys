package ui.controller;
import domain.model.*;
import ui.controller.handler.RequestHandler;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private ShopService service;
    private ControllerFactory handlerFactory = new ControllerFactory();

    @Override
    public void init()throws ServletException{
        super.init();

        ServletContext context = this.getServletContext();

        Properties properties = new Properties();
        Enumeration<String> parameterNames = context.getInitParameterNames();
        while(parameterNames.hasMoreElements()){
            String propertyName = parameterNames.nextElement();
            properties.setProperty(propertyName, context.getInitParameter(propertyName));
        }

        service = new ShopService(properties);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            String action = request.getParameter("action");
            RequestHandler handler = handlerFactory.getHandler(action,service);
            
            String destination = handler.handleRequest(request, response);
            request.getRequestDispatcher(destination).forward(request, response);
        } catch (Exception e){
            throw new ServletException(e.getMessage(),e);
        }
    }
}

