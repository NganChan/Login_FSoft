
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Login nè</title>");
        out.println("<link rel=\"stylesheet\" href=\"style.css\">");
        out.println("<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">");
        out.println("<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>");
        out.println("<link href=\"https://fonts.googleapis.com/css2?family=Dongle&family=Montserrat&family=Raleway:wght@600;700&display=swap\" rel=\"stylesheet\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"form\">");
        out.println("<h3>Please login your account</h3>");
        out.println("<form method='post' action='login'>");
        out.println("<input type=\"text\" id=\"username\" name=\"username\" placeholder=\"Username \"><br><br>");
        out.println("<input type=\"password\" id=\"password\" name=\"password\" required placeholder=\"Password\"><br><br>");
        out.println("<button type=\"submit\" value=\"Login\">Login</button>");
        out.println("</form>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean loginSuccess = checkLogin(username, password);
        request.getSession().setAttribute("loginSuccess", loginSuccess);

        System.out.println("CHECK");
        System.out.println((Boolean) request.getSession().getAttribute("loginSuccess"));
        response.sendRedirect("check-login");

    }

    private boolean checkLogin(String username, String password) {

        JSONParser parser = new JSONParser();
        try ( FileReader reader = new FileReader("C:\\Users\\trant\\OneDrive\\Desktop\\WebApplication2\\web\\users.json")) {
            JSONArray userList = (JSONArray) parser.parse(reader);
            for (Object obj : userList) {
                JSONObject user = (JSONObject) obj;
                String storedUsername = (String) user.get("username");
                String storedPassword = (String) user.get("password");
                if (username.equals(storedUsername) && password.equals(storedPassword)) {
                    return true;
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

}
