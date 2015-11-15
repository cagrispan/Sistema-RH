package resources;

import entities.Employee;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TableReport extends AbstractTableModel{
    private List<Employee> employees = new ArrayList();
    private final Column[] columns = 
    {
        new Column("Nome",          Object.class),
        new Column("Departamento",  String.class),
        new Column("Cargo",         String.class),
        new Column("Nível",         String.class),
        new Column("Salário",       Float.class),
        new Column("Bônus Anual",   Float.class),
    };
    
    public TableReport()
    {
        this.refreshTable();
    }
    
    @Override
    public int getRowCount() {
        return employees.size();
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
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Employee e = employees.get(rowIndex);
        
        if (employees.isEmpty()) {
            return false;
        }
        
        Object[] values = 
        {
            e.getName(),
            e.getDepartment().getName(),
            e.getSalary().getOfficeName(),
            e.getSalary().getLevel(),
            e.getSalary().getValue(),
            e.getBonus()
        };
        
        return values[columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        // nothing to do here
    }
    
    
    public void refreshTable() {
        employees = Employee.getAll();
        fireTableDataChanged();
    }
}
