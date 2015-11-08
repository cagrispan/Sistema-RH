/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import DAOs.EmployeeDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TUNTS
 */
public class Director extends Employee{
    
   private int office = 0;
   private List<Department> deps = new ArrayList();
   private int idDirector;

    public int getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(int idDirector) {
        this.idDirector = idDirector;
    }

    public List<Department> getDeps() {
        return deps;
    }

    public void setDeps(List<Department> deps) {
        this.deps = deps;
    }

    public int getOffice() {
        return office;
    }

    public void setOffice(int office) {
        this.office = office;
    }
    
    public void add() {
        this.office += Integer.parseInt(super.getLevel());
        EmployeeDAO.add(this);
        EmployeeDAO.addDirector(this);
        for (Department dep: deps){
            dep.setIdDirector(idDirector);
            dep.addDirector();
        }
        
    }
}
