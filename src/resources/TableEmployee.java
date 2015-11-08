package resources;

import entities.Department;
import entities.Employee;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class TableEmployee extends AbstractTableModel{
    
    private List<Employee> employees = new ArrayList();
    private final Column[] columns = 
    {
        new Column("Nome",          Object.class),
        new Column("Sobrenome",     String.class),
        new Column("RG",            String.class),
        new Column("CPF",           String.class),
        new Column("Cargo",         String.class),
        new Column("Nível",         String.class),
        new Column("Departamento",  String.class)
    };
    
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
    public Object getValueAt(int rowIndex, int columnIndex) {

        if (employees.size() == 0) {
            return false;
        }

        Employee e = employees.get(rowIndex);

        if(columnIndex == 0) return e.getName();
        if(columnIndex == 1) return e.getSurname(); 
        if(columnIndex == 2) return e.getRG();
        if(columnIndex == 3) return e.getCPF();
        if(columnIndex == 4) return e.getOffice();
        if(columnIndex == 5) return e.getLevel();
        if(columnIndex == 6) return e.getDepartment().getName();
                
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        
        Employee e = employees.get(rowIndex);
        
             if(columnIndex == 0) e.setName(aValue);
        else if(columnIndex == 1) e.setSurname(aValue); 
        else if(columnIndex == 2) e.setRG(aValue);
        else if(columnIndex == 3) e.setCPF(aValue);
        else if(columnIndex == 4) e.setOffice(aValue);
        else if(columnIndex == 5) e.setLevel(aValue);
        else if(columnIndex == 6) e.setDepartment(aValue).getName();
        
        e.update();
        this.refreshTable();
    }
    
    public void sort(String field) {
        
        field = field.toLowerCase();
        
        Collections.sort(employees, new Comparator<Employee>() {
            
            public int compare(Employee arg0, Employee arg1) {
                if(field == "nome") return arg0.getName().compareToIgnoreCase(arg1.getName());
                if(field == "cpf" ) return arg0.getCPF().compareToIgnoreCase(arg1.getCPF());
                if(field)
            }
        });
    }
    
    public void refreshTable() {
        employees = Employee.getAll();
        this.sort();
        fireTableDataChanged();
    }
}
