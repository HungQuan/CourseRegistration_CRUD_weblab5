<%-- 
    Document   : index.jsp
    Created on : Mar 11, 2023, 2:03:30 PM
    Author     : Quan Truong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Course Registration Demo</h1>
        <form action="Student">
            <input type="hidden" name="command" value="search" /> 
            Student ID: <input type="view" name="id"/>
            <input type="submit" value="Submit"/> 
        </form>
        <h2>Manager</h2>
        <a href="Student">Students Manager </a><br> 
        <a href="Course">Course Manager </a>
    </body>
</html>
