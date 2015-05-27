package hotel.gui.model;

import hotel.entity.Service;
import java.util.List;
import javax.swing.AbstractListModel;

public class ServiceListModel extends AbstractListModel<String>{

    private List<Service> service;

    public List<Service> getServices() {
        return service;
    }

    public void setServices(List<Service> service) {
        this.service = service;
    }
    
    public Service getServiceInLine(final int index) {
        return service.get(index);
    }
    
    @Override
    public int getSize() {
        return service.size();
    }

    @Override
    public String getElementAt(int index) {
        return service.get(index).getName();
    }

}
