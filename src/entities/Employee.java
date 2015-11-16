
package entities;

import DAOs.EmployeeDAO;
import interfaces.Authenticatable;
import java.util.List;

public class Employee extends Person implements Authenticatable{
    
    private int     id;    
    private float   bonus;
    private String  password;
    private List<String>  systems;
    
    private Department  department;
    private Salary      salary;

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters"> 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public float getBonus() {
        return bonus;
    }

    public void setBonus() {
        this.bonus = this.calcBonus(this.salary.getValue());
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    
    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }  
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Database Access">
    public  void add() {
        EmployeeDAO.add(this);
    }
    
    public void update()
    {
        EmployeeDAO.update(this);
    }
    
    public void delete()
    {
        EmployeeDAO.delete(this);
    }
    
    public static List<Employee> getAll()
    {
        return EmployeeDAO.loadAll();
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Overrided methods">
    public boolean authenticate(String systemName, String user, String password){
        if(user.equals(this.getCPF()) && password.equals(this.getPassword())){
            if(this.systems.contains(systemName)){
                return true;
            }
        }
        return false;  
    }
    
    public float calcBonus(float salary) {
        //Function overrided by children classes
        return 0;
    }

    public void setDeps(List<Department> deps) {
        //Function overrided by Director and Manager class
    }

    public void setDep(Department get) {
        //Function overrided by Director and Manager class
    }
    // </editor-fold>
}
