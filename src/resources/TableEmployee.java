package resources;

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
        new Column("Telefone",      String.class)
    };
    
            
    public JComboBox offices = new JComboBox();
        
    
//    ,
//        new Column("Cargo",         String.class),
//        new Column("Nível",         String.class),
//        new Column("Departamento",  String.class)
    
    
    public TableEmployee()
    {
        offices.addItem("Diretor");
        offices.addItem("Gerente");
        offices.addItem("Programador");
        offices.addItem("Analista");
        offices.addItem("Auxiliar de Limpeza");
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
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        if (employees.isEmpty()) {
            return false;
        }

        Employee e = employees.get(rowIndex);

        if(columnIndex == 0) return e.getName();
        if(columnIndex == 1) return e.getSurname(); 
        if(columnIndex == 2) return e.getRG();
        if(columnIndex == 3) return e.getCPF();
        if(columnIndex == 4) return e.getPhone();
//        if(columnIndex == 5) return e.getOffice();
        //if(columnIndex == 6) return e.getLevel();
        //if(columnIndex == 7) return e.getDepartment().getName();
                
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        
        Employee e = employees.get(rowIndex);
        
             if(columnIndex == 0) e.setName(aValue.toString());
        else if(columnIndex == 1) e.setSurname(aValue.toString()); 
        else if(columnIndex == 2)
        {
            e.setRG(aValue.toString());
            System.out.println(offices.getSelectedIndex());
        }
        else if(columnIndex == 3) e.setCPF(aValue.toString());
//        else if(columnIndex == 4) e.setOffice(aValue);
        //else if(columnIndex == 5) e.setLevel(aValue);
        //else if(columnIndex == 6) e.setDepartment(aValue.toString()).getName();
        
        e.update();
        this.refreshTable();
    }
    
    public void sort(String field) {
    }
    
    public void refreshTable() {
        employees = Employee.getAll();
//        this.sort();
        fireTableDataChanged();
    }
}
