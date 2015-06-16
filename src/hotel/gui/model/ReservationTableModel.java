package hotel.gui.model;

import hotel.db.provider.ReservationProvider;
import hotel.entity.Reservation;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * @author stephan
 */
public class ReservationTableModel extends AbstractTableModel {

    private List<Reservation> Reservations;

    public List<Reservation> getReservations() {
        return Reservations;
    }

    public void setReservations(List<Reservation> Reservations) {
        this.Reservations = Reservations;
    }
    
    public Reservation getReservationInLine(final int index) {
        return Reservations.get(index);
    }
    
    @Override
    public int getRowCount() {
        return Reservations.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }
    
    @Override
    public String getColumnName(final int column) {
        switch(column) {
            case 0: return "ID";
            case 1: return "Ankunft";
            case 2: return "Abreise";
            case 3: return "Preis";
            case 4: return "Bezahlt";
            case 5: return "Storniert";
        }
        return "";
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0: return getReservationInLine(rowIndex).getId();
            case 1: return getArrival(rowIndex);
            case 2: return getDeparture(rowIndex);
            case 3: return getPrice(rowIndex);
            case 4: return getReservationInLine(rowIndex).isPayed();
            case 5: return getReservationInLine(rowIndex).isCanceled();
        }
        return null;
    }
    
    private String getArrival(int row) {
        return format(getReservationInLine(row).getStart());
    }
    
    private String getDeparture(int row) {
        return format(getReservationInLine(row).getEnd());
    }
    
    private int getPrice(int row) {
        return new ReservationProvider().getTotal(getReservationInLine(row));
    }
    
    private String format(final Date date) {
        return new SimpleDateFormat("dd.MM.yyyy").format(date);
    }
    
    @Override
    public Class getColumnClass(final int column) {
        switch(column) {
            case 0: return Integer.class;
            case 1:
            case 2: return String.class;
            case 3: return Integer.class;
            case 4:
            case 5: return Boolean.class;
        }
        return null;
    }
    
}
