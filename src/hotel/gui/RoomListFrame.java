package hotel.gui;

import hotel.db.provider.RoomProvider;
import hotel.entity.Reservation;
import hotel.entity.Room;
import hotel.gui.model.RoomTableCellRenderer;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/*
 * @author phil
 */
public class RoomListFrame extends javax.swing.JFrame {

    private Room room;
    private Calendar cal;
    private Reservation res;
    private RoomTableCellRenderer render;
    private int[] month = new int[12];
    private int currentMonth = 0;
    private int currentYear = 2015;

    public RoomListFrame() {
        initComponents();
        List<Integer> roomIDs = new RoomProvider().getAllIds();
        for (int i = 0; i < roomIDs.size(); i++) {
            roomBox.addItem(roomIDs.get(i));
        }
        monthLbl.setText(getMonth(currentMonth));
        for (int i = 0; i < month.length; i++) {
            month[i] = i;
        }
        this.fillTable(currentMonth, currentYear);
        this.getResForMonth(currentYear, currentMonth, roomBox.getSelectedIndex());
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

    public void getResForMonth(int year, int month, int roomID) {
        String lastDay = "" + year + "-" + (month + 1) + "-" + this.getAmountOfDays();
        String firstDay = "" + year + "-" + (month + 1) + "-" + this.getFirstDay();

        String query = "SELECT * FROM reservation_dates WHERE room_id = " + roomID + " "
                + " AND ((start_date >= date '" + firstDay + "' AND start_date <= date '" + lastDay + "') "
                + " OR(end_date >= date '" + firstDay + "' AND end_date <= date '" + lastDay + "') "
                + " OR(start_date < date '" + firstDay + "' AND end_date > date '" + lastDay + "'))";
    }

    public void fillTable(int month, int year) {
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
        
        render = new RoomTableCellRenderer(roomTable);
        while (actDay < 41 - col) {
            incDate = sdf.format(cal.getTime());

            if (actDay < this.getAmountOfDays()) {
                roomTable.setValueAt("" + incDate, row, col);
            } else {
                //Falls Felder über sind clearThat
                roomTable.setValueAt("", row, col);
            }

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
        roomTable = new javax.swing.JTable();
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


    private void nextMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextMonthActionPerformed
        currentMonth += 1;
        if (currentMonth > 11) {
            currentMonth = 0;
            currentYear += 1;
            yearLbl.setText("" + currentYear);
        }
        monthLbl.setText(getMonth(currentMonth));
        this.getAmountOfDays();
        this.fillTable(currentMonth, currentYear);
        this.getResForMonth(currentYear, currentMonth, roomBox.getSelectedIndex());
    }//GEN-LAST:event_nextMonthActionPerformed

    private void prevMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevMonthActionPerformed
        currentMonth -= 1;
        if (currentMonth < 0) {
            currentMonth = 11;
            currentYear -= 1;
            yearLbl.setText("" + currentYear);
        }
        monthLbl.setText(getMonth(currentMonth));
        this.getAmountOfDays();
        this.fillTable(currentMonth, currentYear);
        this.getResForMonth(currentYear, currentMonth, roomBox.getSelectedIndex());
    }//GEN-LAST:event_prevMonthActionPerformed

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
}
