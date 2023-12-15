package sit.int202.classicmodels.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebFilter(filterName = "AuthenticationFilter", servletNames = {"AddToCartServlet","ViewCartServlet"}
)
public class AuthenticationFilter implements Filter {
    private FilterConfig filterConfig;
    public void init(FilterConfig config) throws ServletException {
        this.filterConfig = config;
    }
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException,IOException{
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession(false);
        if (session==null ||session.getAttribute("user")==null) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            chain.doFilter(request, response);
        }
    }

    public void destroy() {

    }
}
 
