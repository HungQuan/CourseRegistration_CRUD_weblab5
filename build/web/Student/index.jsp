<%-- 
    Document   : index
    Created on : Mar 11, 2023, 2:02:37 PM
    Author     : Quan Truong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student List Page</title>
        <style>
           table {
            border-collapse: separate;
            border-spacing: 50px 0;
            }   

            td {
            padding: 10px 0;
            }
        </style>
    </head>
    <body>
        <h1>Student List </h1>
        <table>
            <thead>
                <tr>
                    <th>Student ID</th>
                    <th>Student Name</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
            <!--   for (Todo todo: todos) {  -->
                <c:forEach var="student" items="${listStudent}">
                    <!-- Set up a link for each Student -->
                        <c:url var="editLink" value="Student">
                                                <c:param name="command" value="edit" />
						<c:param name="id" value="${student.ID}" />
			</c:url>
                        <c:url var="deleteLink" value="Student">
						<c:param name="command" value="delete" />
                                                <c:param name="id" value="${student.ID}" />
			</c:url>
                         <c:url var="viewLink" value="Student">
						<c:param name="command" value="view" />
                                                <c:param name="id" value="${student.ID}" />
			</c:url>
                        <tr>
                                    <td>
                                        <c:out value="${student.ID}" />
                                    </td>
                                    <td>
                                         <a href="${viewLink}"><c:out value="${student.studentName}" />
                                    </td> 
                                    <td><a href="${editLink}">Edit </a> | <a href="${deleteLink}"
					onclick="if (!(confirm('Are you sure you want to delete this student?'))) return false"> Delete</a> </td>
                        </tr>
                </c:forEach>
            <!-- } -->
            </tbody>
        </table>
        <br>
        <br>
        <c:url var="newLink" value="Student">
                <c:param name="command" value="new" />
	</c:url>
        <a href="${newLink}"> Add Student </a> 
        <br>
        <br>
        <a href="index.jsp"> Homepage </a>
    </body>
    
    
</html>
