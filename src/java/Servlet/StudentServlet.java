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
@WebServlet("/Student")
public class StudentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private StudentDAO studentDAO; 
    private CourseDAO courseDAO; 
    private int studentID; 
    @Override
    public void init() {
        studentDAO = new StudentDAO();
        courseDAO = new CourseDAO(); 
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
                    insertStudent(request, response);
                    break;
                case "delete":
                    deleteStudent(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case"search":
                case "view":
                    showViewForm(request, response);
                    break; 
                 case "addCourse":
                    addCourse(request, response);
                    break; 
                case "remove":
                    removeCourse(request, response);
                    break;  
                case "update":
                    updateStudent(request, response);
                    break;
                case "list":
                default:
                    listStudents(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
        
    }
    private void listStudents(HttpServletRequest request, HttpServletResponse response)
    throws  IOException, ServletException {
        List<Student> listStudent = studentDAO.selectAllStudents();
        request.setAttribute("listStudent", listStudent);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Student/index.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("Student/new.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        int ID = Integer.parseInt(request.getParameter("id"));
        Student existingUser = studentDAO.selectStudent(ID);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Student/update.jsp");
        request.setAttribute("student", existingUser);
        dispatcher.forward(request, response);

    }
    
    private void showViewForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        studentID = Integer.parseInt(request.getParameter("id"));
        Student existingUser = studentDAO.selectStudent(studentID);
        List<Course> listCourse = courseDAO.selectAllCourses();
        List<Course> listCourseAdded = courseDAO.selectAllCourses(studentID);
        request.setAttribute("listCourse", listCourse);
  
        
        request.setAttribute("listCourseAdded", listCourseAdded);

        request.setAttribute("student", existingUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Student/view.jsp"); 
        dispatcher.forward(request, response);

    }
    
    private void removeCourse(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int courseID = Integer.parseInt(request.getParameter("courseID"));
        courseDAO.removeCourse(courseID, studentID); 
        response.sendRedirect("Student?command=view&id=" +studentID);
        
    }
    
    private void addCourse(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
         int courseID = Integer.parseInt(request.getParameter("courseID"));
         courseDAO.addCourse(courseID, studentID);
          response.sendRedirect("Student?command=view&id=" +studentID);
    }
    
    
    private void insertStudent(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        String studentName = request.getParameter("studentName");
        Student newStudent = new Student(studentName);
        studentDAO.insertStudent(newStudent);
        response.sendRedirect("Student");
    }
    
    private void updateStudent(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String studentName = request.getParameter("studentName"); 
        Student student = new Student(id,studentName);
        studentDAO.updateStudent(student);
        response.sendRedirect("Student");
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        studentDAO.deleteStudent(id);
        response.sendRedirect("Student");

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
