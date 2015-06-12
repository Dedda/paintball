/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.gui.model;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author phil
 */
public class myTable extends JTable{
    
    public TableCellRenderer getCellRenderer(int row, int column) {
        //
        TableCellRenderer rtcl = new RoomTableCellRenderer();
        return rtcl;
    }
    
}
