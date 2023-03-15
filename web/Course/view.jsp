<%-- 
    Document   : view
    Created on : Mar 11, 2023, 2:02:09 PM
    Author     : Quan Truong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course Page</title>
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
        <h2>Course's Details</h2>
        <p>Course ID:<span> <c:out value="${course.ID}" /></p> 
        <p>Course Name: <span> <c:out value="${course.courseName}" /> </p> 
        <h3>Student List</h3>
        <table>
            <thead>
                <tr>
                    <th>Student ID</th>
                    <th>Student Name</th>
                    <th>Action </th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="student" items="${listStudentAdded}">
                     <c:url var="removeLink" value="Course">
						<c:param name="command" value="remove" />
                                                <c:param name="studentID" value="${student.ID}" />
                    </c:url>
                     <tr>
                         <td>
                              <c:out value="${student.ID}" />
                         </td>
                          <td>
                           <a href="${viewLink}"><c:out value="${student.studentName}" />
                          </td> 
                          <td><a href="${removeLink}">Remove </a> </td>
                     </tr>
                </c:forEach>
            </tbody>
        </table>
        <br>
        <br>
      <a href="index.jsp"> Homepage </a>
    </body>
</html>
