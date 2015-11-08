package resources;

import entities.CompanySystem;
import DAOs.CompanySystemDAO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TableSystems extends AbstractTableModel {

    private List<CompanySystem> systems = new ArrayList();
    private String[] columnsNames = {"Nome"};
    private Class[] columnsClass = {Object.class};

    public TableSystems() {
        this.refreshTable();
    }

    @Override
    public int getRowCount() {
        return systems.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public String getColumnName(int column) {
        return columnsNames[column];

    }

    @Override
    public Class getColumnClass(int column) {
        return columnsClass[column];

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        if (systems.size() == 0) {
            return false;
        }

        CompanySystem s = systems.get(rowIndex);

        if (columnIndex == 0) {
            return s.getName();
        }

        return false;
    }

    @Override
    public void setValueAt(Object newName, int rowIndex, int columnIndex) {
        CompanySystem sys = systems.get(rowIndex);
        sys.setName(newName.toString());
        sys.update();
        this.refreshTable();

    }

    public void delete(int rows[]) {

        for (int i = 0; i < rows.length; i++) {
            CompanySystem sys = systems.get(rows[i]);
            sys.delete();
        }
        this.refreshTable();
    }
    
    public void add(String name) {

        CompanySystem newCompanySystem = new CompanySystem();
        
        newCompanySystem.setName(name);
        newCompanySystem.add();
        
        this.refreshTable();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    public void refreshTable() {
        systems = CompanySystem.getAll();
        this.sort();
        fireTableDataChanged();
    }

    public void sort() {
        Collections.sort(systems, new Comparator<CompanySystem>() {
            public int compare(CompanySystem arg0, CompanySystem arg1) {
                return arg0.getName().compareToIgnoreCase(arg1.getName());
            }
        });
    }
}
