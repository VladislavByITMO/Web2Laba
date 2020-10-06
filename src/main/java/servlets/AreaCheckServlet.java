package servlets;

import model.Model;
import point.Dot;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

public class AreaCheckServlet extends HttpServlet {
    public Model model = new Model();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        resp.setContentType("text/html");

        try {
            if (!(tryToParse(req.getParameter("x")) && (tryToParse(req.getParameter("Xgr"))))) {
                if (tryToParse(req.getParameter("x"))) {
                    //Если нажата кнопка
                   // createErrorPage(resp, "button true");
                    checkbutton(req,resp);
                } else if (tryToParse(req.getParameter("Xgr"))) {
                    //если пришло по нажатию на график
                //    createErrorPage(resp, "grafic true");
                    checkGrafic(req,resp);
                } else {
                    createErrorPage(resp, "Please no...oooh_ i can't find X or Xgr");
                }

            } else {
                createErrorPage(resp, "Please no...oooh");
            }

        } catch (Exception e) {

        }

    }


    public void checkbutton(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        double scale = Math.pow(10, 4);
        String res = "";
        Double x = Math.ceil( Double.parseDouble(req.getParameter("x")) * scale) / scale;
        Double y = Math.ceil(  Double.parseDouble(req.getParameter("Y")) * scale) / scale;
        Double r =  Math.ceil(  Double.parseDouble(req.getParameter("R")) * scale) / scale;

        if(((x==-2)||(x==-1.5)||(x==-1)||(x==-0.5)||(x==0)||(x==0.5)||(x==1)||(x==1.5)||(x==2))&&(y>=-3)&&(y<=5)&&(r>=2)&&(r<=5)){
            if(zona(x,y,r)){
                res = "odaaa, ti popal";
                model.setDot(new Dot(x,y,r,true));
                drawTable(resp,x.toString(),y.toString(),r.toString(),res);

            }else {
                res = "noo, ti loh";
                model.setDot(new Dot(x,y,r,false));
                drawTable(resp,x.toString(),y.toString(),r.toString(),res);
            }
        }else {
          createErrorPage(resp,"error!");
        }




    }

    public void checkGrafic(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        double scale = Math.pow(10, 4);
        String res = "";
        Double x = Math.ceil( Double.parseDouble(req.getParameter("Xgr")) * scale) / scale;
        Double y = Math.ceil(  Double.parseDouble(req.getParameter("Y")) * scale) / scale;
        Double r =  Math.ceil(  Double.parseDouble(req.getParameter("R")) * scale) / scale;

        if((r>=2)&&(r<=5)){
            if(zona(x,y,r)){
                res = "odaaa, ti popal";
                model.setDot(new Dot(x,y,r,true));

                try {
                    for (int i = 0; i < model.Dots.size(); i++) {
                        res+= (model.Dots.get(i).toString());

                    }
                    drawTable(resp,x.toString(),y.toString(),r.toString(),res);
                }catch (Exception e){
                    drawTable(resp,x.toString(),y.toString(),r.toString(),res);
                }
              //  drawTable(resp,x.toString(),y.toString(),r.toString(),res);

            }else {
                res = "noo, ti loh";
                model.setDot(new Dot(x,y,r,false));
                drawTable(resp,x.toString(),y.toString(),r.toString(),res);

            }

        }else {
            createErrorPage(resp,"error!");
        }


    }

    public void drawTable(HttpServletResponse resp, String x, String y, String r, String otv) throws IOException {

        PrintWriter writer = resp.getWriter();
        String answer = "<html>\n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\" /> " +
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/error.css\">" +
                "  </head>" +
                "<body>" +
                "<div id = \"error\"> X =  " + x + "</div>" +
                "<div id = \"error2\"> Y =  " + y + "</div>" +
                "<div id = \"error3\"> R =  " + r + "</div>" +
                "<div id = \"error3\"> Result ->  " + otv + "</div>" +
                "<a href = \"http://localhost:1680/WebLab2\">Go back</a>" +
                "</body></html>";
        writer.write(answer);
        writer.close();
    }


    public void createErrorPage(HttpServletResponse resp, String text) throws IOException {
        PrintWriter writer = resp.getWriter();
        String answer = "<html>\n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\" /> " +
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/error.css\">" +
                "  </head>" +
                "<body>" +
                "<div id = \"error\">Error " + text + "</div>" +
                "<a href = \"http://localhost:1680/WebLab2\">Go back</a>" +
                "</body></html>";
        writer.write(answer);
        writer.close();
    }

    private boolean tryToParse(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException | NullPointerException ex) {
            return false;
        }
    }



    public boolean zona(Double x, Double y, Double r){
        boolean res = false;
        if ((x<0)&&(y>=0)&&(x>(-r)&&(y<r/2)) ||
                (((x*x + y*y)<=r*r)&&(y<=0)&&(x<=0))||((y>(x/2-r/2))&&(y<=0)&&(x>0)&&x<=r)
        ){
            res = true;
        }
        return res;
    }
}
