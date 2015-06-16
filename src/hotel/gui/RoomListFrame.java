package hotel.gui;

import hotel.db.provider.RoomProvider;
import hotel.entity.Reservation;
import hotel.entity.Room;
import hotel.gui.model.myTable;
import java.awt.Color;
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
 * complete class by phil
 */
public class RoomListFrame extends javax.swing.JFrame {

    private Room room;
//    private myTable myTable;
    private Calendar cal;
    private Reservation res;
    private RoomProvider roomProvider;
    private List<String[]> reservations;
    private int[] month = new int[12];
    private int currentMonth = 0;
    private int currentYear = 2015;
    Color colRes = new Color(0xFFE6E6);
    Color colFree = new Color(0xE5F9F4);

    public RoomListFrame() {
        initComponents();
        setTitle("Zimmerübersicht");
        setLocationRelativeTo(null);
        //Alle roomIDs abfragen und speichern
        List<Integer> roomIDs = new RoomProvider().getAllIds();
        //roomBox die Zimmer geben
        for (int i = 0; i < roomIDs.size(); i++) {
            roomBox.addItem(roomIDs.get(i));
        }
        // Dem label den ersten Monatsnamen geben
        monthLbl.setText(getMonth(currentMonth));
        for (int i = 0; i < month.length; i++) {
            month[i] = i;
        }
        // Den Table das erste mal aufbauen
        this.fillTable(currentMonth, currentYear);
    }

    public String getReservationName(int day, int year, int month) {
        String dateString = "" + year + "-" + month + "-" + day;
        Date currentDay = null;
        // Gebraucht um String in ein Datum umzuwandeln
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            currentDay = sdf.parse(dateString);
        } catch (ParseException ex) {
            System.out.println("Error at parsing the currentDate");
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
                System.out.println("Date parse error at getReservationName");
            }

            boolean found = false;

            if (currentDay.before(resStart)) {
                //flag bleibt auf false

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
        // Den namen des Monats ermitteln
        return new DateFormatSymbols().getMonths()[month];
    }

    public int getFirstDay() {
        int iYear = Integer.parseInt(yearLbl.getText());
        int iMonth = currentMonth;
        int iDay = 1;
        // Den ersten tag des Monats ermitteln
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
        // String bauen mit dem Daten für den ersten und den letzten tag im Monat
        String lastDay = "" + currentYear + "-" + (currentMonth + 1) + "-" + this.getAmountOfDays();
        String firstDay = "" + currentYear + "-" + (currentMonth + 1) + "-" + this.getFirstDay();
        roomProvider = new RoomProvider();
        // Reservierte Tage aus der Datenbank abfragen mit den Daten aus dem GUI
        this.reservations = roomProvider.getResDays((int) roomBox.getSelectedItem(), firstDay, lastDay);
        
        int day = 1;
        // Datums-String bauen
        String date = "" + day + "/" + (month + 1) + "/" + year;
        String incDate;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(date));
        } catch (ParseException ex) {
            System.out.println("Date parse Error");
        }

        /*
        Dateobjekt mit Calendar erzeugen um mit SimpleDateFormat den NAMEN
        des ersten Tages in Monat ermitteln zu können
        */
        Date firstDayOfMonth = cal.getTime();
        // Das sdf 'EEEEEEEE' wird benötigt um den Namen des Wochentags zu "parsen"
        DateFormat sdf2 = new SimpleDateFormat("EEEEEEEE");
        //Variablen für den Table initalisieren
        int col = 0;
        int row = 0;
        int actDay = 0;
        //Prüfen ab welcher "col" der Monat beginnt durch den Namen des wochentags
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
        //Die Felder vor dem 1. Tag leeren
        for (int i = 0; i < col; i++) {
            roomTable.setValueAt("", row, i);
        }
        // wird benötigt um den Tag in einer 2-stelligen Zahl darzustellen
        SimpleDateFormat sdfDay = new SimpleDateFormat("dd");
        // 6 Zeilen * 7 Felder = 42
        // - col da die erste zeile nicht voll genutzt wird
        while (actDay < 41 - col) {
            // Den tag als 2 stellige zahl 
            incDate = sdfDay.format(cal.getTime());
            //actDay (=1) < Anzahl der Tage
            if (actDay < this.getAmountOfDays(
            )) {
                // Tag und Nachname (Falls gefunden) der Person die Reserviert hat in die variable schreiben
                String reservationName = this.getReservationName((actDay + 1), currentYear, (currentMonth + 1));
                // Tag und Nachname (Falls gefunden) in die Tabellenzelle schreiben
                roomTable.setValueAt("" + reservationName, row, col);

            } else {
                //Falls Felder über sind clearThat
                roomTable.setValueAt("", row, col);
            }
            
            col++;
            // Wenn col = 6 dann auch in die nächste Zeile springen
            if (col > 6) {
                col = 0;
                row += 1;
            }
            // +1 Tag
            actDay++;
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

        roomTable.setName("RoomTable");
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

    public void actFrame() {
        /*
        Wenn der Monat 11 (0-11 = 12) ist auf 0 setzen und ein Jahr hochzählen
        und die Labels aktualisieren
        
        Wenn der Monat <0 auf 12 setzen und ein Jahr abziehen
        und die Labels aktualisieren
        */
        if (currentMonth > 11) {
            currentMonth = 0;
            currentYear += 1;
            yearLbl.setText("" + currentYear);
        }else if( currentMonth < 0){
            currentMonth = 11;
            currentYear -= 1;
            yearLbl.setText("" + currentYear);            
        }
        monthLbl.setText(getMonth(currentMonth));
        // Table füllen mit dem neuen Monats-Daten
        this.fillTable(currentMonth, currentYear);
    }

    private void nextMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextMonthActionPerformed
        currentMonth += 1;
        this.actFrame();
    }//GEN-LAST:event_nextMonthActionPerformed

    private void prevMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevMonthActionPerformed
        currentMonth -= 1;
        this.actFrame();
    }//GEN-LAST:event_prevMonthActionPerformed

    private void roomBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomBoxActionPerformed
        this.actFrame();
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
