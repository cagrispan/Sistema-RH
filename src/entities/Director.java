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

   
    public void add() {
        EmployeeDAO.add(this);
        EmployeeDAO.addDirector(this);
        for (Department dep: deps){
            dep.setIdDirector(idDirector);
            dep.addDirector();
        }
        
    }
    
    public float calcBonus(float salary) {
//        System.out.println("DepSize" + deps.size());
        return (salary*4)+(3000*deps.size());
    }
}
