<%-- 
    Document   : logoffAPI
    Created on : Apr 13, 2017, 8:12:24 PM
    Author     : David
--%>

<%@page contentType="application/json" pageEncoding="UTF-8"%> 
<%@page language="java" import="dbUtils.*" %>
<%@page language="java" import="model.Person.*" %>
<%@page language="java" import="com.google.gson.*" %>


<%
    
    /*
    StringData errMsg = new StringData();
   Gson gson = new Gson();
    if(session != null){
        session.invalidate();
        errMsg.logMsg = "true";
    } else {
        errMsg.logMsg = "false";
    }
    
    out.print(gson.toJson(errMsg).trim());*/
    session.invalidate();
    
    //response.sendRedirect("index.html");
    
%>
        