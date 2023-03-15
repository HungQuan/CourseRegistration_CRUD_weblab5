<%-- 
    Document   : new
    Created on : Mar 11, 2023, 2:01:56 PM
    Author     : Quan Truong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New Courses</title>
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
        <h1>New Course</h1>
        <form method="get" action="">
          <input type="hidden" name="command" value="add" />
            <table>
                <tbody>
                       
                        <tr>
                            <td><label>Course Name :</label></td>
                            <td><input type="text" name="courseName"></td>
                        </tr>
                        <tr>
                            <td><label>Number of Credits:</label></td>
                            <td><input type="number" name="credits"></td>
			</tr>
                        <tr>
                            <td><label></label></td>
                            <td><input type="submit" value="Save" ></td>
                        </tr>
                </tbody>
            </table>
        </form>
        <br><br>
        <a href="../index.jsp">Home </a>
    </body>
</html>
