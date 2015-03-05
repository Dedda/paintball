package hotel.entity;

import java.util.Date;
import java.util.Objects;

public class Staff {

    private int id;
    private StaffCategory category;
    private String name;
    private String surname;
    private Date recruitement;
    private Date firing;

    public Staff() {
    }

    public Staff(int id, StaffCategory category, String name, String surname, Date recruitement, Date firing) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.surname = surname;
        this.recruitement = recruitement;
        this.firing = firing;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Staff other = (Staff) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.recruitement, other.recruitement)) {
            return false;
        }
        if (!Objects.equals(this.firing, other.firing)) {
            return false;
        }
        return true;
    }
    
}
