<%-- 
    Document   : webApi
    Created on : Mar 1, 2017, 11:57:11 PM
    Author     : David
--%>

<%@page import="view.CatView"%>
<%@page contentType="application/json" pageEncoding="UTF-8"%> 
<%@page language="java" import="dbUtils.*" %>
<%@page language="java" import="model.Cat.*" %>
<%@page language="java" import="com.google.gson.*" %>

<%

    StringDataList list = new StringDataList();

    DbConn dbc = new DbConn();
    list.dbError = dbc.getErr(); // returns "" if connection is good, else db error msg.

    
    if (list.dbError.length() == 0) { // got open connection
        list = CatView.buildCatlist(dbc);
    } 
    //Check if the session isn't null [after buildPersonList because 'list =' reinitializes & overwrites]
    if(session.getAttribute("user") != null){
        //StringData loggedOnUserName = (StringData) session.getAttribute("user");
        list.loggedOn = "true";
    } else {
        list.loggedOn = "false";
    }
    // PREVENT DB connection leaks:
    dbc.close(); // EVERY code path that opens a db connection, must also close it.

    Gson gson = new Gson();
    out.print(gson.toJson(list).trim()); 
%>