
package entities;

import DAOs.SalaryDAO;
import java.util.List;

public class Salary {
    private int id;
    private int idOffice;
    private int level;
    private float value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getIdOffice() {
        return idOffice;
    }

    public void setIdOffice(int idOffice) {
        this.idOffice = idOffice;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
    
    public static List<Salary> getAll()
    {
        return SalaryDAO.loadAll();
    }
    
    public String getOfficeName ()
    {
        return Employee.getOfficeNameById(this.getIdOffice());
    }
    
    public void update()
    {
        SalaryDAO.update(this);
    }
    
    
}
