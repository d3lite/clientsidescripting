<%-- 
    Document   : getUserListAPI
    Created on : Mar 29, 2017, 1:29:04 AM
    Author     : David
--%>
<%@page import="view.PersonView"%>
<%@page contentType="application/json" pageEncoding="UTF-8"%> 
<%@page language="java" import="dbUtils.*" %>
<%@page language="java" import="model.Person.*" %>
<%@page language="java" import="com.google.gson.*" %>

<%

    StringDataList list = new StringDataList();

    DbConn dbc = new DbConn();
    list.dbError = dbc.getErr(); // returns "" if connection is good, else db error msg.

    if (list.dbError.length() == 0) { // got open connection
        list = PersonView.buildPersonList(dbc);
    } 
    //Check if the session isn't null [after buildPersonList() because 'list =' reinitializes & overwrites]
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