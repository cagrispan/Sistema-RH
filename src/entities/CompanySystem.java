package entities;

import DAOs.CompanySystemDAO;
import java.util.List;

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
    
    public static List<CompanySystem> getSystems(int id) {
        return CompanySystemDAO.getSystems(id);
    }
    
    public static List<CompanySystem> getAvailable(int id)
    {
        return CompanySystemDAO.getOutterSystems(id);
    }
    
    
       
}
