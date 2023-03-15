<%-- 
    Document   : index
    Created on : Mar 11, 2023, 2:01:49 PM
    Author     : Quan Truong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course List Page</title>
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
        <h1>Course List </h1>
        <table>
            <thead>
                <tr>
                    <th>Course ID</th>
                    <th>Course Name</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
            <!--   for (Todo todo: todos) {  -->
                <c:forEach var="course" items="${listCourse}">
                    <!-- Set up a link for each Student -->
                        <c:url var="editLink" value="Course">
                                                <c:param name="command" value="edit" />
						<c:param name="id" value="${course.ID}" />
			</c:url>
                        <c:url var="deleteLink" value="Course">
						<c:param name="command" value="delete" />
                                                <c:param name="id" value="${course.ID}" />
			</c:url>
                        <c:url var="viewLink" value="Course">
						<c:param name="command" value="view" />
                                                <c:param name="id" value="${course.ID}" />
			</c:url>
                        
                        <tr>
                                    <td>
                                        <c:out value="${course.ID}" />
                                    </td>
                                    <td>
                                        <a href="${viewLink}"><c:out value="${course.courseName}" />
                                    </td> 
                                    <td><a href="${editLink}">Edit </a> | <a href="${deleteLink}"
					onclick="if (!(confirm('Are you sure you want to delete this course?'))) return false"> Delete</a> </td>
                        </tr>
                </c:forEach>
            <!-- } -->
            </tbody>
        </table>
        <br>
        <br>
        <c:url var="newLink" value="Course">
                <c:param name="command" value="new" />
	</c:url>
        <a href="${newLink}"> Add Course </a> 
        <br>
        <br>
      <a href="index.jsp"> Homepage </a>
    </body>
</html>
