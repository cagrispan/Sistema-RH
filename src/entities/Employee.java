/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import DAOs.EmployeeDAO;
import java.util.List;

/**
 *
 * @author TUNTS
 */
public class Employee extends Person{
    
    private String password;
    private String level;
    private Department department;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Employee() {
    }

    public  void add() {
        EmployeeDAO.add(this);
    }
    
    public void update()
    {
        EmployeeDAO.update(this);
    }
    
    public void setOffice()
    {
        
    }
    
    public static List<Employee> getAll()
    {
        return EmployeeDAO.loadAll();
    }

    public int getOffice() {
        return 0;
    }

}
