package resources;

import entities.CompanySystem;
import entities.Department;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TablePermSystemsAvailable extends AbstractTableModel {

    private List<CompanySystem> sys = new ArrayList();    
    private Column[] columns =
    {
        new Column("Nome", Object.class)
    };

    public TablePermSystemsAvailable(List<CompanySystem> sys) {
        this.sys = sys;
        this.refreshTable();
    }

    public TablePermSystemsAvailable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getRowCount() {
        return sys.size();
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

        if (sys.size() == 0) {
            return false;
        }

        CompanySystem s = sys.get(rowIndex);

        if (columnIndex == 0) {
            return s.getName();
        }

        return false;
    }

    @Override
    public void setValueAt(Object newName, int rowIndex, int columnIndex) {

    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void delete(int rows[]) {

        for (int i = 0; i < rows.length; i++) {
            CompanySystem csys = sys.get(rows[i]);
            csys.delete();
        }
        this.refreshTable();
    }
    
    public void add(String name) {

        Department newDepartment = new Department();
        
        newDepartment.setName(name);
        newDepartment.add();
        
        this.refreshTable();
    }
    
    public List<CompanySystem> remove(int rows[]){
        
        List<CompanySystem> systems = new ArrayList();
        
        for (int i=0;i<rows.length;i++){
            systems.add(sys.remove(rows[i]-i));
        }
        
        fireTableDataChanged();
        return systems;     
        
    }

    public void refreshTable() {
        this.sort();
        fireTableDataChanged();
    }

    public void sort() {
        Collections.sort(sys, new Comparator<CompanySystem>() {
            public int compare(CompanySystem arg0, CompanySystem arg1) {
                return arg0.getName().compareToIgnoreCase(arg1.getName());
            }
        });
    }
    
    public List<CompanySystem> remove(int[] rows, List<CompanySystem> syst) {
        for (int i=0;i<rows.length;i++){
            syst.add(sys.remove(rows[i]-i));
        }
        
        fireTableDataChanged();
        return syst; 
    }

    public void addToList(List<CompanySystem> systemArray) {
        
        System.out.println("Entrei");
        for (CompanySystem sys: systemArray){
            
            System.out.println("iterando " + sys.getName());
            this.sys.add(sys);
        }
        
        this.sort();
        fireTableDataChanged();
    }
}
