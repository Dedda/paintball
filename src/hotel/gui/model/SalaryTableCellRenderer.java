package hotel.gui.model;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author phil
 */
public class SalaryTableCellRenderer extends DefaultTableCellRenderer {
    //Farben initalisieren
    Color colOdd = new Color(0xF2F2F2);
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(row%2==1){
            setBackground(colOdd);
        }
        return comp;
    }

}