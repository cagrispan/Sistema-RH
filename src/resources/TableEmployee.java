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
        new Column("Departamento",  String.class),
        new Column("Salário",       Float.class)
    };
    
            
    public JComboBox offices = new JComboBox();
    public JComboBox levels = new JComboBox();
    public JComboBox departments = new JComboBox();
    
    private List<Department> departmentsList = Department.getAll();
        
       
    
    public TableEmployee()
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
        Employee e = employees.get(rowIndex);
        
        if(columnIndex == 6 && e.getSalary().getIdOffice()== 4)
        {
            e.getSalary().setLevel(0);
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
            offices.getItemAt(e.getSalary().getIdOffice()),
            levels.getItemAt(e.getSalary().getLevel()),
            e.getDepartment().getName(),
            e.getSalary().getValue()
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
            e.getSalary().setIdOffice(offices.getSelectedIndex());
            
            if(e.getSalary().getIdOffice()==4)
                e.getSalary().setLevel(0);
        }
        else if(columnIndex == 6) e.getSalary().setLevel(levels.getSelectedIndex());
        else if(columnIndex == 7)
        {
            Department dep = departmentsList.get(departments.getSelectedIndex());
            
            e.setDepartment(dep);
            
        }
        
        System.out.println();   
             
        e.update();
        this.refreshTable();
    }
    
    
    public void refreshTable() {
        
        this.departmentsList = Department.getAll();
        
        employees = Employee.getAll();
        
        offices = new JComboBox();
        offices.addItem("Diretor");
        offices.addItem("Gerente");
        offices.addItem("Analista");        
        offices.addItem("Programador");
        offices.addItem("Auxiliar de Limpeza");
        
        levels = new JComboBox();
        levels.addItem("1");
        levels.addItem("2");
        levels.addItem("3");
        
        this.departments = new JComboBox();
        for (Department dep : this.departmentsList){
            System.out.println(dep.getName());
            departments.addItem(dep.getName());
        }
        
        fireTableDataChanged();
    }
}
