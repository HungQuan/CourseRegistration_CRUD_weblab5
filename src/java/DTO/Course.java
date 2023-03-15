/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Quan Truong
 */
public class Course {
    protected int ID; 
    protected String courseName; 
    protected int credits; 
    
    public Course(){}

    public Course(int ID, String courseName){
        this.ID = ID; 
        this.courseName = courseName; 
    }

    public Course(String courseName, int credits) {
        this.courseName = courseName;
        this.credits = credits;
    }
   
    public Course(int ID, String courseName, int credits) {
        this.ID = ID;
        this.courseName = courseName;
        this.credits = credits;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
    
    
}
