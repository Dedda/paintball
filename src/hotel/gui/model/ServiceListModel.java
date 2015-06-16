package hotel.gui.model;

import hotel.entity.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.AbstractListModel;

/**
 * @author stephan
 */
public class ServiceListModel extends AbstractListModel<String>{

    private List<Service> service;

    public List<Service> getServices() {
        return service;
    }

    public void setServices(List<Service> service) {
        this.service = service;
    }
    
    public void setServices(final Map<Service, Integer> service) {
        this.service = new ArrayList<>();
        for (Service current : service.keySet()) {
            for (int i = 0; i < service.get(current); i++) {
                this.service.add(current);
            }
        }
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
