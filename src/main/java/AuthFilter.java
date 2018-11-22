import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            HttpSession session = request.getSession();
            String loginURI = request.getContextPath() + "/";

            if (session.getAttribute("user") == null) {
               //request.getRequestDispatcher( loginURI ).forward( request, response );
                response.sendRedirect("/");

            } else {
                filterChain.doFilter(request, response);
            }
        } finally {

        }
    }



    @Override
    public void destroy() {

    }
}
