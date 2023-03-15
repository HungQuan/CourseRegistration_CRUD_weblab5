<%-- 
    Document   : update
    Created on : Mar 11, 2023, 2:02:50 PM
    Author     : Quan Truong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Student </title>
         <link rel="stylesheet" href="../style.css">
    </head>
    <body>
        <h1>Update Student Details </h1>
        <form action="Student" method="post">
            <input type="hidden" name="command" value="update" />
            <input type="hidden" name="id" value="${student.ID}" />
            <table>
                    <tbody>
			<tr>
				<td><label>Student Name:</label></td>
				<td><input type="text" name="studentName" value="${student.studentName}"></td>
			</tr>
			<tr>
				<td><label></label></td>
                                <td><input type="submit" value="Save" class="save"></td>
			</tr>
                    </tbody>
            </table>
        </form>
    </body>
</html>
