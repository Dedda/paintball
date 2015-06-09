package hotel.entity;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.Days;

public class Reservation {

    private int id;
    private Date start;
    private Date end;
    private Guest guest;
    private int people;
    private List<Integer> rooms;
    private String additionalInfo;
    private Date payed;
    private Date canceled;

    public Reservation() {
    }

    public Reservation(int id, Date start, Date end, Guest guest, int people, List<Integer> rooms, String additionalInfo, Date payed, Date canceled) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.guest = guest;
        this.people = people;
        this.rooms = rooms;
        this.additionalInfo = additionalInfo;
        this.payed = payed;
        this.canceled = canceled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Date getPayed() {
        return payed;
    }

    public void setPayed(Date payed) {
        this.payed = payed;
    }

    public Date getCanceled() {
        return canceled;
    }

    public void setCanceled(Date canceled) {
        this.canceled = canceled;
    }
    
    public int getDays() {
        return Days.daysBetween(new DateTime(start.getTime()), new DateTime(end.getTime())).getDays();
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public List<Integer> getRooms() {
        return rooms;
    }

    public void setRooms(List<Integer> rooms) {
        this.rooms = rooms;
    }
    
    public boolean isPayed() {
        return null != payed && payed.before(new Date());
    }
    
    public boolean isCanceled() {
        return null != canceled && canceled.before(new Date());
    }
    
}
