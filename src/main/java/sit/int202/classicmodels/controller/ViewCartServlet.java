package sit.int202.classicmodels.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ViewCartServlet", value = "/view-cart")
public class ViewCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null||session.getAttribute("cart") == null){
//            response.getWriter().write("Your cart not available");
            String errorMsg = "Your cart not available";
            response.setHeader("error",errorMsg);
            response.sendError(HttpServletResponse.SC_BAD_GATEWAY, errorMsg);
        }else {
            request.getRequestDispatcher("/view-cart.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
 
