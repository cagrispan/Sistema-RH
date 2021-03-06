package resources;

import entities.Department;
import entities.Director;
import entities.Manager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.table.AbstractTableModel;

public class TableDepartments extends AbstractTableModel {

    private List<Department> departments = Department.getAll();
    private Column[] columns
            = {
                new Column("Nome", Object.class),
                new Column("Diretor", Director.class),
                new Column("Gerente", Manager.class)
            };

    public JComboBox directors = new JComboBox();
    public JComboBox managers = new JComboBox();

    public TableDepartments() {
        this.refreshTable();

        directors.addItem(new Director());
        managers.addItem(new Manager());

//        for(Manager manager : Manager.getAvailables())
//        {
//            managers.addItem(manager);
//        }
    }

    @Override
    public int getRowCount() {
        return departments.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column].columnName;

    }

    @Override
    public Class getColumnClass(int column) {
        return columns[column].columnClass;

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        if (departments.size() == 0) {
            return false;
        }

        Department d = departments.get(rowIndex);

        Object[] values
                = {
                    d.getName(),
                    "",
                    ""
                };
        if (d.getDirector() != null) {
            values[1] = d.getDirector().getName();
        }

        if (d.getManager() != null) {
            values[2] = d.getManager().getName();
        }

        return values[columnIndex];

    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Department dep = departments.get(rowIndex);

        if (columnIndex == 0 && !Validation.validateEmpty(value.toString()) && Validation.validateText(value.toString())) {
            dep.setName(value.toString());
        }

        dep.update();
        this.refreshTable();

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;

    }

    public void delete(int rows[]) {

        for (int i = 0; i < rows.length; i++) {
            Department dep = departments.get(rows[i]);
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

    public List<Department> remove(int rows[], List<Department> deps) {

        for (int i = 0; i < rows.length; i++) {
            deps.add(departments.remove(rows[i] - i));
        }

        fireTableDataChanged();
        return deps;

    }

    public void refreshTable() {
        departments = Department.getAll();
        this.sort();
        fireTableDataChanged();
    }

    public void sort() {
        Collections.sort(departments, new Comparator<Department>() {
            public int compare(Department arg0, Department arg1) {
                return arg0.getName().compareToIgnoreCase(arg1.getName());
            }
        });
    }

    public void addToList(List<Department> deps) {

        for (Department dep : deps) {
            departments.add(dep);
        }
        this.sort();
        fireTableDataChanged();
    }
}
