package hotel.gui.model;

import hotel.db.provider.RoomProvider;
import hotel.entity.Reservation;
import hotel.gui.RoomListFrame;
import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author phil
 */
public class RoomTableCellRenderer extends DefaultTableCellRenderer {

    Color colRes = new Color(0xFFE6E6);
    Color colFree = new Color(0xE5F9F4);
    Color colResWe = new Color(0xFAE1E1);
    Color colFreeWe = new Color(0xE0F4E9);

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (getText().length() > 3) {
            if (column >= 5) {
                this.setBackground(colResWe);
            } else {
                this.setBackground(colRes);
            }
        } else if (getText().length() > 0) {
            if (column >= 5) {
                this.setBackground(colFreeWe);
            } else {
                this.setBackground(colFree);
            }
        }
        return comp;
    }

}
