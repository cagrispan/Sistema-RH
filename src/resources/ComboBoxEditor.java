package resources;

import java.awt.Component;  
import javax.swing.AbstractCellEditor;  
import javax.swing.JComboBox;  
import javax.swing.JTable;  
import javax.swing.table.TableCellEditor; 

public class ComboBoxEditor extends AbstractCellEditor  
        implements TableCellEditor {  
  
    private JComboBox field;  
    private String[] values = {"1", "2"};  
  
    @Override  
    public Component getTableCellEditorComponent(JTable table,  
            Object value, boolean isSelected, int row, int column) {  
        field = new JComboBox(getValues());  
        field.setSelectedItem(value); 
        return field;  
    }  
  
    @Override  
    public Object getCellEditorValue() {  
        return field.getSelectedItem().toString();  
    }  

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }
}  