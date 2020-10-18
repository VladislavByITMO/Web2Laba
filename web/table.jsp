<%--
  Created by IntelliJ IDEA.
  User: Taras
  Date: 04.10.2020
  Time: 22:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Dot" %>
<%@ page import="model.Model" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Dot dot;%>
<%
    Model model = (Model) request.getServletContext().getAttribute("dots");
    if (!(model == null)){
        ArrayList<Dot> dots = (ArrayList<Dot>) model.getDotsList();
        if (!dots.isEmpty()) {
            dot = dots.get(dots.size()-1);
            %>
<circle id="point2" r="3" cx="<%= dot.getX() %>" cy="<%= dot.getY() %>" fill="red" stroke="green" visibility="visible"/>
            <%
        }
    }
%>
