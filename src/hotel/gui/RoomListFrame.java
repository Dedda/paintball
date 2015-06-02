package hotel.gui;

import hotel.entity.Reservation;
import java.util.Calendar;

/**
 *
 * @author phil
 */
public class RoomListFrame extends javax.swing.JFrame {

    private Calendar cal;
    private Reservation res;

    /*
     public class Reservation {
     private Date start;
     private Date end;
     private Guest guest;
     private int people;
     */
    public RoomListFrame() {
        initComponents();
    }

    public void getDays() {
        res.getStart();
        res.getEnd();
        res.getGuest().getFullName();
        
        /*
         test
         public abstract int getLeastMaximum(int field)
         Returns the lowest maximum value for the given calendar field of this Calendar instance. 
         The lowest maximum value is defined as the smallest value returned by getActualMaximum(int) for any possible time value. 
         The least maximum value depends on calendar system specific parameters of the instance. For example, a Calendar for 
         the Gregorian calendar system returns 28 for the DAY_OF_MONTH field, because the 28th is the last day of the shortest 
         month of this calendar, February in a common year.
         */
    }

    public void fillTable() {

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        roomTable = new javax.swing.JTable();
        monthLbl = new javax.swing.JLabel();
        yearLbl = new javax.swing.JLabel();
        prevMonth = new javax.swing.JButton();
        nextMonth = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 800));
        setName("RoomFrame"); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 800));
        setResizable(false);

        roomTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag"
            }
        ));
        roomTable.setAlignmentX(1.0F);
        roomTable.setAlignmentY(1.0F);
        roomTable.setRowHeight(20);
        jScrollPane1.setViewportView(roomTable);

        monthLbl.setText("Januar");

        yearLbl.setText("2015");

        prevMonth.setText("<");

        nextMonth.setText(">");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(prevMonth)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nextMonth)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(monthLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(yearLbl)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(monthLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(prevMonth)
                    .addComponent(nextMonth))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel monthLbl;
    private javax.swing.JButton nextMonth;
    private javax.swing.JButton prevMonth;
    private javax.swing.JTable roomTable;
    private javax.swing.JLabel yearLbl;
    // End of variables declaration//GEN-END:variables
}
