package hotel.entity;

import java.util.Date;

public class Reservation {

    private int id;
    private Date start;
    private Date end;
    private Guest guest;
    private Room room;
    private String additionalInfo;
    private Date arrival;
    private Date departure;
    private Date payed;
    private Date canceled;
    
}
