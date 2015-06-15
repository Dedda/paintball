package hotel.gui.model;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author phil
 */
public class RoomTableCellRenderer extends DefaultTableCellRenderer {
    //Farben initalisieren
    Color colRes = new Color(0xFFE6E6);
    Color colResWe = new Color(0xFAE1E1);
    Color colFree = new Color(0xE5F9F4);
    Color colFreeWe = new Color(0xE0F4E9);

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        //Wenn der Text in der Zelle mehr als 3 Stellen hat -> Reserviert
        //Weil das Datum 2 stellig ist bspw 01-28
        if (getText().length() > 3) {
            if (column >= 5) {
                // Wenn die "column" >5 ist dann ist wochenende
                this.setBackground(colResWe);
            } else {
                // sonst Mo-Fr
                this.setBackground(colRes);
            }
        //Wenn der Text in der Zelle mehr als 0 Stellen hat -> Frei
        //Funktioniert weil zuerst auf Reserviert geprüft wird
        } else if (getText().length() > 0) {
            if (column >= 5) {
                // Wenn die "column" >5 ist dann ist wochenende
                this.setBackground(colFreeWe);
            } else {
                // sonst Mo-Fr
                this.setBackground(colFree);
            }
        }
        // Wenn der Text 0 Stellen hat ist kein Tag des Monats und es wird nichts gefärbt
        return comp;
    }

}