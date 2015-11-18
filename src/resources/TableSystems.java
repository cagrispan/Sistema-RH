package resources;

import entities.CompanySystem;
import DAOs.CompanySystemDAO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TableSystems extends AbstractTableModel {

    private List<CompanySystem> systems = CompanySystem.getAll();
    private Column[] columns =
    {
        new Column("Nome", Object.class)
    };

    public TableSystems() {
        this.refreshTable();
    }

    @Override
    public int getRowCount() {
        return systems.size();
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

        if (systems.size() == 0) {
            return false;
        }

        CompanySystem s = systems.get(rowIndex);

        Object[] values =
        {
          s.getName()
        };

        return values[columnIndex];
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
