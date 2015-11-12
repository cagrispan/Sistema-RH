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
    private int idOffice;
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
    
    public void setOffice(String office)
    {
        office = office.toLowerCase();
        switch (office) {
            case "diretor":
                idOffice = 0;
                break;
            case "gerente":
                idOffice = 3;
                break;
            case "analista":
                idOffice = 6;
                break;
            case "programador":
                idOffice = 9;
                break;
            case "auxiliar de limpeza":
                idOffice = 12;
                break;
            default:
                throw new AssertionError();
        }
    }
    
    public void setOffice(int id)
    {
        this.idOffice = id;
    }
    
    public int getOffice()
    {
        return this.idOffice;
    }
    
    public static List<Employee> getAll()
    {
        return EmployeeDAO.loadAll();
    }


    public void setDeps(List<Department> deps) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setDep(Department get) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
