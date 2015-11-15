package resources;

import DAOs.DepartmentDAO;
import entities.Department;
import entities.Employee;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.table.AbstractTableModel;


public class TableEmployee extends AbstractTableModel{
    
    private List<Employee> employees = new ArrayList();
    private final Column[] columns = 
    {
        new Column("Nome",          Object.class),
        new Column("Sobrenome",     String.class),
        new Column("RG",            String.class),
        new Column("CPF",           String.class),
        new Column("Telefone",      String.class),
        new Column("Cargo",         String.class),
        new Column("Nível",         String.class),
        new Column("Departamento",  Department.class)
    };
    
            
    public JComboBox offices = new JComboBox();
    public JComboBox levels = new JComboBox();
    public JComboBox departments = new JComboBox();
        
    
//        new Column("Departamento",  Object.class)
    
    
    public TableEmployee()
    {
        offices.addItem("Diretor");
        offices.addItem("Gerente");
        offices.addItem("Analista");        
        offices.addItem("Programador");
        offices.addItem("Auxiliar de Limpeza");
        
        
        levels.addItem("1");
        levels.addItem("2");
        levels.addItem("3");
        
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
        Employee e = employees.get(rowIndex);
        
        if(columnIndex == 6 && e.getOffice()== 4)
        {
            e.setLevel(0);
            return false;
        }
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        if (employees.isEmpty()) {
            return false;
        }

        Employee e = employees.get(rowIndex);
        Object[] values = 
        {
            e.getName(),
            e.getSurname(),
            e.getRG(),
            e.getCPF(),
            e.getPhone(),
            offices.getItemAt(e.getOffice()),
            levels.getItemAt(e.getLevel()),
            e.getSalary().getOfficeName()
        };
                
        return values[columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        
        Employee e = employees.get(rowIndex);
        
             if(columnIndex == 0) e.setName(aValue.toString());
        else if(columnIndex == 1) e.setSurname(aValue.toString()); 
        else if(columnIndex == 2) e.setRG(aValue.toString());
        else if(columnIndex == 3) e.setCPF(aValue.toString());
        else if(columnIndex == 4) e.setPhone(aValue.toString());
        else if(columnIndex == 5)
        {
            e.setOffice(offices.getSelectedIndex());
            
            if(e.getOffice()==4)
                e.setLevel(0);
        }
        else if(columnIndex == 6) e.setLevel(levels.getSelectedIndex());
       // else if(columnIndex == 7) e.setDepartment(departments.getSelectedIndex());
        
        System.out.println();   
             
        e.update();
        this.refreshTable();
    }
    
    
    public void refreshTable() {
        employees = Employee.getAll();
        fireTableDataChanged();
    }
}
