package hotel.gui;

import hotel.entity.Reservation;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.joda.time.LocalDate;

/**
 *
 * @author phil
 */
public class RoomListFrame extends javax.swing.JFrame {

    private Calendar cal;
    private Reservation res;
    private int[] month = new int[12];
    private int currentMonth = 0;
    private int currentYear = 2015;
    /*
     public class Reservation {
     private Date start;
     private Date end;
     private Guest guest;
     private int people;
     */

    public RoomListFrame() {
        initComponents();
        monthLbl.setText("" + 1);
        for (int i = 0; i < month.length; i++) {
            month[i] = i;
        }
        getResDays(currentYear, currentMonth);
    }

    public void getResDays(int year, int month) {        
//        String[] monthNames = new String[12];
//        String name = monthNames[cal.get(Calendar.MONTH)];
        
        int iYear = 1999;
        int iMonth = Calendar.FEBRUARY;
        int iDay = 1;
        
        
        Calendar mycal = new GregorianCalendar(iYear, iMonth, iDay);

        //Anzahl der Tage des Monats bekommen
        int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH); // 28
        System.out.println(daysInMonth);

        // 2015-03-01
        //String dateString = ""+year+"-"+month+"-";
        /*
         public abstract int getLeastMaximum(int field)
         Returns the lowest maximum value for the given calendar field of this Calendar instance. 
         The lowest maximum value is defined as the smallest value returned by getActualMaximum(int) for any possible time value. 
         The least maximum value depends on calendar system specific parameters of the instance. For example, a Calendar for 
         the Gregorian calendar system returns 28 for the DAY_OF_MONTH field, because the 28th is the last day of the shortest 
         month of this calendar, February in a common year.
         */
        String query = "SELECT * FROM reservation_dates WHERE room_id = 1 "
                + " AND ((start_date >= date '2015-03-01' AND start_date <= date '2015-03-31') "
                + " OR(end_date >= date '2015-03-01' AND end_date <= date '2015-03-31') "
                + " OR(start_date < date '2015-03-01' AND end_date > date '2015-03-31'))";
    }

    public void fillTable() {
        //Obere Row - bspw 01. Jan (also die geraden 0,2,4
        //Untere Row - Reserviert (rot) + GuestName
        for (int r = 0; r < roomTable.getRowCount(); r++) {
            for (int c = 0; c < roomTable.getColumnCount(); c++) {
                switch (c) {
//                    case 0:
//                        roomTable.setValueAt( , r, c);
//                        break;
//                    case 1:
//                        roomTable.setValueAt( , r, c);
//                        break;
//                    case 2:
//                        roomTable.setValueAt( , r, c);
//                        break;
//                    case 3:
//                        roomTable.setValueAt( , r, c);
//                        break;
//                    case 4:
//                        roomTable.setValueAt( , r, c);
//                        break;
//                    case 5:
//                        roomTable.setValueAt( , r, c);
//                        break;
//                    case 6:
//                        roomTable.setValueAt( , r, c);
//                        break;
                }
            }
        }
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
        roomBox = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 305));
        setName("RoomFrame"); // NOI18N
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

        yearLbl.setText("2015");

        prevMonth.setText("<");
        prevMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevMonthActionPerformed(evt);
            }
        });

        nextMonth.setText(">");
        nextMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextMonthActionPerformed(evt);
            }
        });

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roomBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(nextMonth)
                    .addComponent(roomBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void nextMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextMonthActionPerformed
        currentMonth += 1;
        if (currentMonth > 11) {
            currentMonth = 0;
            currentYear += 1;
            yearLbl.setText("" + currentYear);
        }
        monthLbl.setText("" + (month[currentMonth]+1));
    }//GEN-LAST:event_nextMonthActionPerformed

    private void prevMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevMonthActionPerformed
        currentMonth -= 1;
        if (currentMonth < 0) {
            currentMonth = 11;
            currentYear -= 1;
            yearLbl.setText("" + currentYear);
        }
        monthLbl.setText("" + (month[currentMonth]+1));
    }//GEN-LAST:event_prevMonthActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel monthLbl;
    private javax.swing.JButton nextMonth;
    private javax.swing.JButton prevMonth;
    private javax.swing.JComboBox roomBox;
    private javax.swing.JTable roomTable;
    private javax.swing.JLabel yearLbl;
    // End of variables declaration//GEN-END:variables
}
