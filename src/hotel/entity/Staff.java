package hotel.entity;

import java.util.Date;

public class Staff {

    private int id;
    private StaffCategory category;
    private String name;
    private String surname;
    private Date recruitement;
    private Date firing;

    public Staff() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StaffCategory getCategory() {
        return category;
    }

    public void setCategory(StaffCategory category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getRecruitement() {
        return recruitement;
    }

    public void setRecruitement(Date recruitement) {
        this.recruitement = recruitement;
    }

    public Date getFiring() {
        return firing;
    }

    public void setFiring(Date firing) {
        this.firing = firing;
    }
    
}
