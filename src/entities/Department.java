package entities;

import DAOs.DepartmentDAO;
import java.util.List;

public class Department {
    
    private int id;
    private Director director;
    private Manager manager;
    private String name;

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
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
    
    public static Department getById(int id)
    {
        return DepartmentDAO.loadById(id);
    }
    
    @Override
    public String toString()
    {
        return this.getName();
    }
    
    
       
}
