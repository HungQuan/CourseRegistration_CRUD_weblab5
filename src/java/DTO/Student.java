/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Quan Truong
 */
public class Student {
    protected int ID; 
    protected String  studentName; 
    
    public Student(){}

    public Student(String studentName) {
        this.studentName = studentName;
    }
    
    public Student(int ID, String studentName) {
        this.ID = ID;
        this.studentName = studentName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    

    
    
    
    
}
