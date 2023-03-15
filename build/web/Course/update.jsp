<%-- 
    Document   : update
    Created on : Mar 11, 2023, 2:02:03 PM
    Author     : Quan Truong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Course</title>
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
        <h1>Update Course Details </h1>
           <form action="Course" method="get">
            <input type="hidden" name="command" value="update" />
            <input type="hidden"  name="id" value="${course.ID}" />
            <table>
                    <tbody>
                        
			<tr>
				<td><label>Course Name:</label></td>
				<td><input type="text" name="courseName" value="${course.courseName}"></td>
			</tr>
                        <tr>
				<td><label>Course Credit :</label></td>
				<td><input type="number" name="credits" value="${course.credits}"></td>
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
