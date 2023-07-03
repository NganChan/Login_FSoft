
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckLoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Boolean loginSuccess = (Boolean) request.getSession().getAttribute("loginSuccess");
        if (loginSuccess == null) {
            loginSuccess = false;
        }
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Login Result</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Login Result</h1>");
            if (request.getSession().getAttribute("loginSuccess") == null) {
                out.println("<p>Đăng nhập thất bại. </p>");
            } else {

                out.println("<p>Đăng nhập thành công. </p>");
            }
            out.println("</body>");
            out.println("</html>");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
