import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;


public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = "";
        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("Username")) {

                userName = cookie.getValue();
                req.setAttribute("usernameAttr", userName);
                //System.out.println(userName);
            }
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember");
        Cookie cookie = new Cookie("Username","");
        if(remember != null) {
            cookie = new Cookie("Username", username);
            cookie.setMaxAge(3600); //in seconds
            resp.addCookie(cookie);
        } else {
            cookie.setMaxAge(-1);
            resp.addCookie(cookie);
        }
        UserData users = new UserData();
        UserEntity user = users.getUsers().get(username);

        if(username.equals(user.getUsername()) && password.equals(user.getPassword())) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            resp.sendRedirect("/welcome.jsp");
        } else {
            resp.sendRedirect("/");
        }

    }
}
