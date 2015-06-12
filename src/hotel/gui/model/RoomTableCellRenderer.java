package hotel.gui.model;

import hotel.db.provider.RoomProvider;
import hotel.gui.RoomListFrame;
import java.awt.Color;
import java.awt.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author phil
 */
public class RoomTableCellRenderer extends DefaultTableCellRenderer {

    private int column;
    private RoomProvider roomProvider;
    private List<String[]> reservations;
    private RoomTableCellRenderer render;
    Color colRes = new Color(0xFFE6E6);
    Color colFree = new Color(0xE5F9F4);

    public RoomTableCellRenderer(int col) {
        this.column = col;
        setOpaque(true);
    }

    public void getcell(JTable table, int day, int month, int year) {
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus,
                    int row, int column) {

                super.getTableCellRendererComponent(table, value, isSelected,
                        hasFocus, row, column);

                String dateString = "" + month + "-" + year + "-" + day;
                Date resStart;
                Date resEnd;
                Date currentDay = null;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                try {
                    currentDay = sdf.parse(dateString);

                } catch (ParseException ex) {
                    System.out.println("Date parse error RoomTableCellRenderer");
                }

                for (int i = 0; i < reservations.size(); i++) {
                    String[] resRow = reservations.get(i);
                    String guestSurname = resRow[2];
                    try {
                System.out.println("DayDate: " + currentDay);
                        resStart = sdf.parse(resRow[0]);
                        resEnd = sdf.parse(resRow[1]);
                System.out.println("Start: " + resStart);
                System.out.println("End: " + resEnd);
                        if (currentDay.before(resStart)) {
                            setBackground(colFree);
                        }
                        if (currentDay.after(resStart) && currentDay.before(resEnd) || currentDay.equals(resEnd) || currentDay.equals(resStart)) {
                            setBackground(colRes);
                        }
                        if (currentDay.after(resEnd)) {
                            setBackground(colFree);
                        }
                    } catch (ParseException ex) {
                        System.out.println("Date parse error at setBackgroundColor()");
                    }
                    return this;
                }
                return this;
            }
        });
    }
}
