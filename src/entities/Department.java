/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import DAOs.DepartmentDAO;
import java.util.List;

/**
 *
 * @author TUNTS
 */
public class Department {
    
    private int id;
    private int idDirector;
    private int idManager;
    private String name;

    public int getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(int idDirector) {
        this.idDirector = idDirector;
    }

    public int getIdManager() {
        return idManager;
    }

    public void setIdManager(int idManager) {
        this.idManager = idManager;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim();
    }
    
    public static List<Department> getAll()
    {
        return DepartmentDAO.loadAll();
    }

    public void add() {
        DepartmentDAO.add(this); 
    }
    
    public void update()
    {
        DepartmentDAO.update(this); 
    }

    public void delete() {
        DepartmentDAO.delete(this);
    }
    
    public void addDirector(){
        DepartmentDAO.addDirector(this);
    }
    
    public void addManager(){
        DepartmentDAO.addManager(this);
    }
    
    
       
}
