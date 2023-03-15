/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.Student;
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


public class StudentDAO {
       private static final String INSERT_STUDENT_SQL = "INSERT INTO student" + "  (StudentName) VALUES " +
        " (?);";
       private static final String SELECT_STUDENT_BY_ID = "select * from student where StudentID= ?"; 
       private static final String SELECT_ALL_STUDENT = "select * from student";
       private static final String DELETE_STUDENT_SQL ="delete from student where StudentID= ?"; 
       private static final String UPDATE_STUDENT_SQL = "update student set StudentName = ?  where StudentID = ?"; 
      
       private static final String SELECT_STUDENT_WITH_COURSE ="select distinct StudentID, StudentName from student, studentcourse where StudentID = Student_ID AND Course_ID = ?";
       private static final String DELETE_STUDENT_WITH_COURSE ="delete from studentcourse where Student_ID = ? AND Course_ID = ?"; 
       
        public StudentDAO(){}
        
        public void insertStudent(Student student) throws SQLException{
            System.out.println(INSERT_STUDENT_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = Database.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT_SQL);) {
            preparedStatement.setString(1, student.getStudentName());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
        }
        
        public Student selectStudent(int id) {
        Student student = null;
        // Step 1: Establishing a Connection
        try (Connection connection = Database.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int studentID = rs.getInt("StudentID");
                String studentName = rs.getString("StudentName");
                student = new Student(studentID,studentName); 
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return student;
        }
       
        public List <Student> selectAllStudents() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Student > students = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = Database.getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENT);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("StudentID");
                String StudentName = rs.getString("StudentName");
                students.add(new Student(id, StudentName));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return students;
    }

        public boolean deleteStudent(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = Database.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
        
        public boolean removeStudent(int id, int courseID) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = Database.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT_WITH_COURSE);) {
            statement.setInt(1, id);
             statement.setInt(2, courseID);
             rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
        }
        
        public boolean updateStudent(Student student) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = Database.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENT_SQL);) {
            statement.setString(1, student.getStudentName());
            statement.setInt(2, student.getID());
            
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
    
         public List <Student> selectAllStudents(int courseID) {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Student > students = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = Database.getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_WITH_COURSE);) {
            preparedStatement.setInt(1, courseID);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("StudentID");
                String StudentName = rs.getString("StudentName");
                students.add(new Student(id, StudentName));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return students;
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
