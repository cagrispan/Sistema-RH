/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import DAOs.CompanySystemDAO;
import java.util.List;

/**
 *
 * @author TUNTS
 */
public class CompanySystem {
    
    private int id;
    private String name;

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
    
    public static List<CompanySystem> getAll()
    {
        return CompanySystemDAO.loadAll();
    }

    public void add() {
        CompanySystemDAO.add(this); 
    }
    
    public void update()
    {
        CompanySystemDAO.update(this); 
    }

    public void delete() {
        CompanySystemDAO.delete(this);
    }
    
    
    
       
}
