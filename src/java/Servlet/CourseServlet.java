/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import DAO.CourseDAO;
import DAO.StudentDAO;
import DTO.Course;
import DTO.Student;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Quan Truong
 */
@WebServlet("/Course")
public class CourseServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private CourseDAO courseDAO; 
    private StudentDAO studentDAO; 
    private int courseID; 
    
    @Override
    public void init(){
        courseDAO = new CourseDAO();
        studentDAO = new StudentDAO(); 
    }
   

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html");
            String action = request.getParameter("command");
            
           if (action == null) {
		action = "list";
            }
            try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                 case "add":
                    insertCourse(request, response);
                    break;
                case "delete":
                    deleteCourse(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateCourse(request, response);
                    break;  
                case "view":
                    showViewForm(request, response);
                    break;
                case "remove":
                    removeStudent(request, response);
                    break; 
                case "list":
                default:
                    listCourses(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("Course/new.jsp");
        dispatcher.forward(request, response);
    }
    
     private void insertCourse(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        String courseName = request.getParameter("courseName");
        int credits = Integer.parseInt(request.getParameter("credits"));
        Course newCourse = new Course(courseName,credits);
        courseDAO.insertCourse(newCourse);
        response.sendRedirect("Course");
    }
    
     private void listCourses(HttpServletRequest request, HttpServletResponse response)
    throws  IOException, ServletException {
        List<Course> listCourse = courseDAO.selectAllCourses();
        request.setAttribute("listCourse", listCourse);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Course/index.jsp");
        dispatcher.forward(request, response);
    }
     
     private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        int ID = Integer.parseInt(request.getParameter("id"));
        Course existingUser = courseDAO.selectCourse(ID);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("Course/update.jsp");
        request.setAttribute("course", existingUser);
        dispatcher.forward(request, response);

    }
     
    private void showViewForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        
        courseID = Integer.parseInt(request.getParameter("id"));
        Course existingUser = courseDAO.selectCourse(courseID);
        List<Student> listStudentAdded = studentDAO.selectAllStudents(courseID); 
        request.setAttribute("listStudentAdded", listStudentAdded);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Course/view.jsp");
        request.setAttribute("course", existingUser);
        dispatcher.forward(request, response);

    }
    
     private void updateCourse(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String courseName = request.getParameter("courseName");
        int credits = Integer.parseInt(request.getParameter("credits")); 
//        PrintWriter out = response.getWriter(); 
//        out.print(out);
        Course course = new Course(id,courseName,credits);
        courseDAO.updateCourse(course);
        response.sendRedirect("Course");
    }

     private void deleteCourse(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        courseDAO.deleteCourse(id);
        response.sendRedirect("Course");

    }
     private void removeStudent(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int studentID = Integer.parseInt(request.getParameter("studentID"));
        studentDAO.removeStudent(studentID, courseID); 
        response.sendRedirect("Course?command=view&id=" +courseID);
           
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         doGet(request,response); 
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
