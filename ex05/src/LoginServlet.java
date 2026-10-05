import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

    // Handle GET request - Display login page
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><title>Login Page</title></head>");
        out.println("<body>");

        out.println("<h2>Login Page</h2>");

        out.println("<form method='post' action='LoginServlet'>");
        out.println("Username: <input type='text' name='username'><br><br>");
        out.println("Password: <input type='password' name='password'><br><br>");
        out.println("<input type='submit' value='Login'>");
        out.println("</form>");

        out.println("</body>");
        out.println("</html>");
    }

    // Handle POST request - Authentication
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        String uname = request.getParameter("username");
        String pwd = request.getParameter("password");

        if ("admin".equals(uname) && "12345".equals(pwd)) {
            out.println("<h3>Login Successful! Welcome, " + uname + ".</h3>");
        } else {
            out.println("<h3>Login Failed! Invalid username or password.</h3>");
            out.println("<a href='LoginServlet'>Try Again</a>");
        }
    }
}
