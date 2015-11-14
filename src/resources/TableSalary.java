package resources;

import DAOs.EmployeeDAO;
import entities.Employee;
import entities.Salary;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TableSalary extends AbstractTableModel
{
    private List<Salary> salarys;
    private Column[] columns = 
    {
        new Column("Cargo", String.class),
        new Column("Nivel", String.class),
        new Column("Salário", Float.class)
    };
    
    public TableSalary()
    {
        this.refreshTable();
    }
    
    public void refreshTable() {
        salarys = Salary.getAll();
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return salarys.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns [column].columnName;

    }

    @Override
    public Class getColumnClass(int column) {
        return columns [column].columnClass;

    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex==2;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (salarys.isEmpty()) {
            return false;
        }

        Salary s = salarys.get(rowIndex);

        if(columnIndex == 0) return s.getOfficeName();
        if(columnIndex == 1) return s.getLevel();
        if(columnIndex == 2) return s.getValue();
                
        return false;
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Salary s = salarys.get(rowIndex);
        
        if(columnIndex == 2) s.setValue(Float.parseFloat(aValue.toString()));
        
        s.update();
        this.refreshTable();
    }
}
