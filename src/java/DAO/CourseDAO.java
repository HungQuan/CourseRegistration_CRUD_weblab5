/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Quan Truong
 */
public class CourseDAO {
    private static final String INSERT_COURSE_SQL = "INSERT INTO course" + "  (CourseName,Credits) VALUES " +
        " (?, ?);";
    private static final String SELECT_ALL_COURSE = "SELECT * FROM course";
    private static final String SELECT_COURSE_BY_ID = "select * from course where CourseID= ?"; 
    private static final String DELETE_COURSE_SQL = "delete from course where CourseID= ?"; 
    private static final String UPDATE_COURSE_SQL = "update course set CourseName = ?, Credits = ?  where CourseID = ?"; 
    private static final String SELECT_COURSE_WITH_STUDENT = "select distinct CourseID, CourseName from course, studentcourse where Course_ID = CourseID AND Student_ID = ?";
     private static final String ADD_COURSE_WITH_STUDENT = "insert into studentcourse values (?,?)"; 
    private static final String DELETE_COURSE_WITH_STUDENT = "delete from studentcourse where Course_ID = ? AND Student_ID =?;"; 
     
    public CourseDAO(){}
    
      public void insertCourse(Course course) throws SQLException{
            System.out.println(INSERT_COURSE_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = Database.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COURSE_SQL);) {
            preparedStatement.setString(1, course.getCourseName());
            preparedStatement.setInt(2, course.getCredits());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
        }
     public Course selectCourse(int id) {
        Course course = null;
        // Step 1: Establishing a Connection
        try (Connection connection = Database.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COURSE_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int ID = rs.getInt("CourseID"); 
                String courseName = rs.getString("CourseName");
                int credits = rs.getInt("Credits");
                course = new Course(ID,courseName,credits);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return course;
        }
    public List <Course> selectAllCourses() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Course > courses = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = Database.getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COURSE);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("CourseID");
                String courseName = rs.getString("CourseName"); 
                courses.add(new Course(id, courseName));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return courses;
    }
    
     public List <Course> selectAllCourses(int studentID) {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Course > courses = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = Database.getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COURSE_WITH_STUDENT);) {
             preparedStatement.setInt(1, studentID);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            
           
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            
                int id = rs.getInt("CourseID");
                String courseName = rs.getString("CourseName"); 
                courses.add(new Course(id, courseName));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return courses;
    }
      
    public boolean deleteCourse(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = Database.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_COURSE_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    
     public boolean updateCourse(Course course) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = Database.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_COURSE_SQL);) {
            statement.setString(1, course.getCourseName());
            statement.setInt(2, course.getCredits());
            statement.setInt(3, course.getID());
            
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
     
     public boolean removeCourse(int id, int studentID) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = Database.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_COURSE_WITH_STUDENT);) {
            statement.setInt(1, id);
             statement.setInt(2, studentID);
             rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    } 
     
     
      public void addCourse(int courseID, int studentID) throws SQLException{
            System.out.println(ADD_COURSE_WITH_STUDENT);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = Database.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(ADD_COURSE_WITH_STUDENT);) {
            preparedStatement.setInt(1, studentID);
            preparedStatement.setInt(2,courseID);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
        }
    
      private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
