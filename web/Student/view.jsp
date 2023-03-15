<%-- 
    Document   : view
    Created on : Mar 11, 2023, 2:02:58 PM
    Author     : Quan Truong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Page</title>
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
        <h2>Student's Details</h2>
        <p>Student ID:<span> <c:out value="${student.ID}" /></p> 
        <p>Student Name: <span> <c:out value="${student.studentName}" /> </p> 
        <br>
        <h3>Select Course</h3>
        <form >
          <input type="hidden" name="command" value="addCourse" />    
            <label for="dropdown">Course:</label>
              <select name="courseID">
                  <c:forEach var="course" items="${listCourse}">
                   <option value="${course.ID}"><c:out value="${course.courseName}" /></option>    
                  </c:forEach> 
              </select>
            <button type="submit" >Add</button>
        </form>
        <br>
        <h3>Registered Course</h3>
        <table>
            <thead>
                <tr>
                    <th>Course ID</th>
                    <th>Course Name</th>
                    <th>Action</th>
                </tr>
             </thead>
             <tbody>
                 <c:forEach var="course" items="${listCourseAdded}">
                      <c:url var="viewLink" value="Course">
						<c:param name="command" value="view" />
                                                <c:param name="id" value="${course.ID}" />
			</c:url>
                     <c:url var="removeLink" value="Student">
						<c:param name="command" value="remove" />
                                                <c:param name="courseID" value="${course.ID}" />
			</c:url>
                        
                     <tr>
                                    <td>
                                        <c:out value="${course.ID}" />
                                    </td>
                                    <td>
                                        <a href="${viewLink}"><c:out value="${course.courseName}" />
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
