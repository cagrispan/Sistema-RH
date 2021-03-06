/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import DAOs.EmployeeDAO;
import entities.Department;
import java.util.List;

/**
 *
 * @author TUNTS
 */
public class Manager extends Employee {


    private int office = 3;
    private int idManager;
    private Department dep;

    public Department getDep() {
        if(dep==null){
            dep =EmployeeDAO.getDepMan(this);
        }
        return dep;
    }

    public void setDep(Department dep) {
        this.dep = dep;
    }

    public int getIdManager() {
        return idManager;
    }

    public void setIdManager(int idManager) {
        this.idManager = idManager;
    }

    public int getOffice() {
        return office;
    }

    public void setOffice(int office) {
        this.office = office;
    }

    public void add() {
        EmployeeDAO.add(this);
        EmployeeDAO.addManager(this);
        dep.setManager(this);
        dep.addManager();

    }
    
    public static Manager get(int id)
    {
        return EmployeeDAO.getManager(id);
    }

    public float calcBonus(float salary) {
        return salary * 2 + 100 * this.depSize();
    }

    private int depSize() {
        if(this.getDep()==null){return 0;}
        return EmployeeDAO.getDepartmentSize(this);
    }
}
