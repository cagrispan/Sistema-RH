
package entities;

import DAOs.SalaryDAO;
import java.util.List;

public class Salary {
    private int idOffice;
    private int level;
    private float value;

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
    
    
}
