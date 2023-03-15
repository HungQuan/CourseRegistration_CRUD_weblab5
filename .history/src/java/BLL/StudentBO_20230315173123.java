/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;
import DTO.Student;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Quan Truong
 */
public class StudentBO {
    private static List<Integer> availID; 
    
    public static boolean checkID(int studentID, List<Student> listStudents){
        availID = new ArrayList<Integer>();
        
        for(int i =0; i < listStudents.size();i++){
            availID.add(listStudents.get(i).getID()); 
        }
        return availID.contains(studentID);
     
    }
}
