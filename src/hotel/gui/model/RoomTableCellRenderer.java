/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.gui.model;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author phil
 */
public class RoomTableCellRenderer {

    private int colorClm = -1;
    private int colorRow = -1;
    Color colRes = new Color(0xFFE6E6);
    Color colFree = new Color(0xE5F9F4);

    /*
    Row und Cols müssen noch übergeben werden
    */
    
    public RoomTableCellRenderer(JTable table) {
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus,
                    int row, int col) {
                System.out.print("Row: "+row);
                System.out.println(" Col: "+col);
                
                

                Component c = super.getTableCellRendererComponent(table, value,
                        isSelected, hasFocus, row, col);

                setBackground(colFree);

                return this;
            }
        });
    }

}
