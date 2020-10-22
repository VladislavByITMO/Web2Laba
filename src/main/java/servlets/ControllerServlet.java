package servlets;

import model.Model;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/")
public class ControllerServlet extends HttpServlet {

    private Model model;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        this.model = (Model)req.getServletContext().getAttribute("dots");
        if(model==null){
            model = new Model();
        }

        try {

            if (tryToParse(req.getParameter("Y")) &&
                    tryToParse(req.getParameter("R"))) {

                getServletContext().getRequestDispatcher("/areaCheckServlet").forward(req, resp);

            } else {
                    getServletContext().getRequestDispatcher("/main_page.jsp").forward(req, resp);

            }

        } catch (Exception e) {
            PrintWriter writer = resp.getWriter();
            e.printStackTrace();
            writer.write("Server wanna cry: " + e.toString() );
            writer.close();
        }
    }



    private boolean tryToParse(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException | NullPointerException ex) {
            return false;
        }
    }
}


