package hotel.gui;

import hotel.db.provider.RoomProvider;
import hotel.entity.Reservation;
import hotel.entity.Room;
import hotel.gui.model.RoomTableCellRenderer;
import hotel.gui.model.myTable;
import java.awt.Color;
import java.awt.Component;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/*
 * @author phil
 */
public class RoomListFrame extends javax.swing.JFrame {

    private Room room;
//    private myTable myTable;
    private Calendar cal;
    private Reservation res;
    private RoomProvider roomProvider;
    private List<String[]> reservations;
    private RoomTableCellRenderer render;
    private int[] month = new int[12];
    private int currentMonth = 0;
    private int currentYear = 2015;
    Color colRes = new Color(0xFFE6E6);
    Color colFree = new Color(0xE5F9F4);

    public RoomListFrame() {
        initComponents();

//        roomTable.getColumnModel().getColumn(0).setCellRenderer(new RoomTableCellRenderer());
//        roomTable.getColumnModel().getColumn(1).setCellRenderer(new RoomTableCellRenderer());
//        roomTable.getColumnModel().getColumn(2).setCellRenderer(new RoomTableCellRenderer());
//        roomTable.getColumnModel().getColumn(3).setCellRenderer(new RoomTableCellRenderer());
//        roomTable.getColumnModel().getColumn(4).setCellRenderer(new RoomTableCellRenderer());
//        roomTable.getColumnModel().getColumn(5).setCellRenderer(new RoomTableCellRenderer());
//        roomTable.getColumnModel().getColumn(6).setCellRenderer(new RoomTableCellRenderer());
//        roomTable.setDefaultRenderer( Object.class, new DefaultTableCellRenderer() { 
//            @Override
//            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) { 
//                Component component = super.getTableCellRendererComponent( table, value, isSelected, hasFocus, row, column);
//                int select = (int)table.getModel().getValueAt(row, column );
// 
//                if( select == 1 ) {
//                    component.setBackground(Color.yellow);
//                } else {
//                    component.setBackground(Color.cyan);
//                } 
//                return component;
//            };
// 
//        });
        List<Integer> roomIDs = new RoomProvider().getAllIds();

        for (int i = 0; i < roomIDs.size(); i++) {
            roomBox.addItem(roomIDs.get(i));
        }

        monthLbl.setText(getMonth(currentMonth));
        for (int i = 0; i < month.length; i++) {
            month[i] = i;
        }
        this.fillTable(currentMonth, currentYear);
    }

    public String getReservationName(int day, int year, int month) {
        String dateString = "" + year + "-" + month + "-" + day;
        Date currentDay = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            currentDay = sdf.parse(dateString);
        } catch (ParseException ex) {
            System.out.println("Fehler beim currentDate parsen");
        }

        for (int i = 0; i < reservations.size(); i++) {
            String[] row = reservations.get(i);
            String guestSurname = row[2];
            Date resStart = null;
            Date resEnd = null;
            try {
                resStart = sdf.parse(row[0]);
                resEnd = sdf.parse(row[1]);
            } catch (ParseException ex) {
                System.out.println("Date parse error at setBackgroundColor()");
            }

            boolean found = false;

            if (currentDay.before(resStart)) {
                //flag bleibt auf false
                System.out.println("CurrD: (" + currentDay + ") Before start (" + resStart + ")");

            } else if (currentDay.after(resEnd)) {
                //flag bleibt auf false
            } else {
                found = true;
            }

            if (found == true) {
                //den Namen des Gastes zurückgeben
                return day + ": " + guestSurname;
            }
        }
        //nichts gefunden
        return "" + day;
    }

    public String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month];
    }

    public int getFirstDay() {
        int iYear = Integer.parseInt(yearLbl.getText());
        int iMonth = currentMonth;
        int iDay = 1;
        Calendar cal = new GregorianCalendar(iYear, iMonth, iDay);
        return cal.getActualMinimum(Calendar.DAY_OF_MONTH);
    }

    public int getAmountOfDays() {
        int iYear = Integer.parseInt(yearLbl.getText());
        int iMonth = currentMonth;
        int iDay = 1;
        Calendar cal = new GregorianCalendar(iYear, iMonth, iDay);
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public void fillTable(int month, int year) {
        String lastDay = "" + currentYear + "-" + (currentMonth + 1) + "-" + this.getAmountOfDays();
        String firstDay = "" + currentYear + "-" + (currentMonth + 1) + "-" + this.getFirstDay();
        roomProvider = new RoomProvider();
        this.reservations = roomProvider.getResDays((int) roomBox.getSelectedItem(), firstDay, lastDay);

        int day = 1;
        String date = "" + day + "/" + (month + 1) + "/" + year;
        String incDate;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(date));
        } catch (ParseException ex) {
            System.out.println("Date parse Error");
        }

        //get FirstDayOfMonth
        Date firstDayOfMonth = cal.getTime();
        DateFormat sdf2 = new SimpleDateFormat("EEEEEEEE");
        //System.out.println("First Day of Month: " + sdf2.format(firstDayOfMonth));

        int col = 0;
        int row = 0;
        int actDay = 0;

        String tag = sdf2.format(firstDayOfMonth);
        if (tag.equals("Montag")) {
            col = 0;
        }
        if (tag.equals("Dienstag")) {
            col = 1;
        }
        if (tag.equals("Mittwoch")) {
            col = 2;
        }
        if (tag.equals("Donnerstag")) {
            col = 3;
        }
        if (tag.equals("Freitag")) {
            col = 4;
        }
        if (tag.equals("Samstag")) {
            col = 5;
        }
        if (tag.equals("Sonntag")) {
            col = 6;
        }
        for (int i = 0; i < col; i++) {
            roomTable.setValueAt("", row, i);
        }
        SimpleDateFormat sdfDay = new SimpleDateFormat("dd");

        while (actDay < 41 - col) {
            incDate = sdfDay.format(cal.getTime());
            if (actDay < this.getAmountOfDays()) {
                String reservationName = this.getReservationName((actDay + 1), currentYear, (currentMonth + 1));
//                System.out.println(reservationName);
                roomTable.setValueAt("" + reservationName, row, col);

            } else {
                //Falls Felder über sind clearThat
                roomTable.setValueAt("", row, col);
            }
            
//            roomTable.getColumnModel().getColumn(col).setCellRenderer(new RoomTableCellRenderer());
            
            col++;
            if (col > 6) {
                col = 0;
                row += 1;
            }
            actDay++;
            cal.add(Calendar.DATE, 1);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        roomTable = new myTable();
        monthLbl = new javax.swing.JLabel();
        yearLbl = new javax.swing.JLabel();
        prevMonth = new javax.swing.JButton();
        nextMonth = new javax.swing.JButton();
        roomBox = new javax.swing.JComboBox();
        roomLbl = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 350));
        setName("RoomFrame"); // NOI18N
        setResizable(false);

        roomTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
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
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        roomTable.setToolTipText("");
        roomTable.setAlignmentX(1.0F);
        roomTable.setAlignmentY(1.0F);
        roomTable.setRowHeight(40);
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

        roomBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roomBoxActionPerformed(evt);
            }
        });

        roomLbl.setText("Raum:");

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
                        .addGap(141, 141, 141)
                        .addComponent(roomLbl)
                        .addGap(18, 18, 18)
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
                    .addComponent(roomBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roomLbl))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void actFrame(int month) {
        if (currentMonth > 11) {
            currentMonth = 0;
            currentYear += 1;
            yearLbl.setText("" + currentYear);
        }
        monthLbl.setText(getMonth(currentMonth));
        this.getAmountOfDays();
        this.fillTable(currentMonth, currentYear);
    }

    private void nextMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextMonthActionPerformed
        currentMonth += 1;
        this.actFrame(currentMonth);
    }//GEN-LAST:event_nextMonthActionPerformed

    private void prevMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevMonthActionPerformed
        currentMonth -= 1;
        this.actFrame(currentMonth);
    }//GEN-LAST:event_prevMonthActionPerformed

    private void roomBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomBoxActionPerformed
        this.actFrame(currentMonth);
    }//GEN-LAST:event_roomBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel monthLbl;
    private javax.swing.JButton nextMonth;
    private javax.swing.JButton prevMonth;
    private javax.swing.JComboBox roomBox;
    private javax.swing.JLabel roomLbl;
    private javax.swing.JTable roomTable;
    private javax.swing.JLabel yearLbl;
    // End of variables declaration//GEN-END:variables

    private Object getColumnModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
