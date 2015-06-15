package hotel.gui.model;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author phil
 */
public class myTable extends JTable {

    public TableCellRenderer getCellRenderer(int row, int column) {
        TableCellRenderer rtcl;
        switch (getName()) {
            case "RoomTable":
                rtcl = new RoomTableCellRenderer();
//                System.out.println("beim Raum");
                break;
            case "SalaryTable":
                rtcl = new SalaryTableCellRenderer();
//                System.out.println("beim Mitarbeiter");
                break;
            default:
                rtcl = new DefaultTableCellRenderer();
//                System.out.println("Default");
                break;
        }
//        System.out.println(rtcl.getClass());
//        System.out.println(getName());
        return rtcl;
    }

}
