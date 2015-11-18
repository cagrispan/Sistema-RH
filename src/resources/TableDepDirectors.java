package resources;

import entities.Department;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TableDepDirectors extends AbstractTableModel {

    private List<Department> deps = new ArrayList();    
    private Column[] columns =
    {
        new Column("Nome", Object.class)
    };

    public TableDepDirectors(List<Department> deps) {
        this.deps = deps;
        this.refreshTable();
    }

    @Override
    public int getRowCount() {
        return deps.size();
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

        if (deps.size() == 0) {
            return false;
        }

        Department d = deps.get(rowIndex);

        if (columnIndex == 0) {
            return d.getName();
        }

        return false;
    }

    @Override
    public void setValueAt(Object newName, int rowIndex, int columnIndex) {
//        Department dep = deps.get(rowIndex);
//        dep.setName(newName.toString());
//        dep.update();
//        this.refreshTable();

    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void delete(int rows[]) {

        for (int i = 0; i < rows.length; i++) {
            Department dep = deps.get(rows[i]);
            dep.delete();
        }
        this.refreshTable();
    }
    
    public void add(String name) {

        Department newDepartment = new Department();
        
        newDepartment.setName(name);
        newDepartment.add();
        
        this.refreshTable();
    }
    
    public List<Department> remove(int rows[]){
        
        List<Department> departments = new ArrayList();
        
        for (int i=0;i<rows.length;i++){
            departments.add(deps.remove(rows[i]-i));
        }
        
        fireTableDataChanged();
        return departments;     
        
    }

    public void refreshTable() {
        this.sort();
        fireTableDataChanged();
    }

    public void sort() {
        Collections.sort(deps, new Comparator<Department>() {
            public int compare(Department arg0, Department arg1) {
                return arg0.getName().compareToIgnoreCase(arg1.getName());
            }
        });
    }
}
