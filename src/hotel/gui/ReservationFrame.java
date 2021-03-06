/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.gui;

import hotel.db.provider.ReservationProvider;
import hotel.db.provider.RoomProvider;
import hotel.db.provider.ServiceProvider;
import hotel.entity.Guest;
import hotel.entity.Reservation;
import hotel.entity.Room;
import hotel.entity.Service;
import hotel.gui.model.RoomListModel;
import hotel.gui.model.ServiceListModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.JodaTimePermission;

/**
 * @author stephan
 */
public class ReservationFrame extends javax.swing.JFrame {

    public static final int ONE_DAY = 1000*60*60*24;
    private final Reservation reservation;
    private boolean existing;
    private Guest guest;
    
    /**
     * Creates new form ReservationFrame
     */
    public ReservationFrame(final Reservation reservation, final boolean existing, final Guest guest) {
        this.reservation = reservation;
        this.existing = existing;
        this.guest = guest;
        initComponents();
        if (existing) {
            idValueLbl.setText(reservation.getId() + "");
            peopleSpinner.setValue(reservation.getPeople());
        }
        guestNameLbl.setText(reservation.getGuest().getFullName());
        payedBtn.setEnabled(!reservation.isPayed());
        cancelReservationBtn.setEnabled(!reservation.isCanceled());
        stateTextLbl.setText(reservation.isCanceled() ? "storniert" : (reservation.isPayed() ? "bezahlt" : "offen"));
        Map<Service, Integer> services = new ServiceProvider().getForReservation(reservation);
        ServiceListModel serviceListModel = new ServiceListModel();
        serviceListModel.setServices(services);
        servicesList2.setModel(serviceListModel);
        List<Room> rooms = new RoomProvider().getForReservation(reservation.getId());
        RoomListModel roomListModel = new RoomListModel();
        roomListModel.setRooms(rooms);
        roomsList.setModel(roomListModel);
        int price = new ReservationProvider().getTotal(reservation);
        priceValueLbl.setText(price + " Euro");
        departureSpinner.setValue(new Date(getArrival().getTime() + ONE_DAY));
    }

    private String format(final Date date) {
        return new SimpleDateFormat("dd.MM.yyyy").format(date);
    }
    
    private List<Service> getSelectedServices() {
        ServiceListModel model = (ServiceListModel) servicesList2.getModel();
        return model.getServices();
    }
    
    private Map<Integer, Integer> getGroupedServiceIds() {
        List<Service> services = getSelectedServices();
        Map<Integer, List<Service>> idToServiceMap = services.stream().collect(Collectors.groupingBy(Service::getId));
        Map<Integer, Integer> idToAmountMap = new HashMap<>();
        idToServiceMap.keySet().forEach(i -> idToAmountMap.put(i, idToServiceMap.get(i).size()));
        return idToAmountMap;
    }
    
    private List<Room> getSelectedRooms() {
        int[] index = roomsList.getSelectedIndices();
        RoomListModel model = (RoomListModel) roomsList.getModel();
        List<Room> selected = Arrays.stream(index).mapToObj(i -> model.getRoomInLine(i)).collect(Collectors.toList());
        return selected;
    }
    
    private Date getArrival() {
        return (Date) arrivalSpinner.getValue();
    }
    
    private Date getDeparture() {
        return (Date) departureSpinner.getValue();
    }
    
    private List<Integer> getRooms() {
        RoomListModel model = (RoomListModel) roomsList.getModel();
        return model.getRooms().stream().map(Room::getId).collect(Collectors.toList());
    }
    
    private int getDays() {
        return Days.daysBetween(new DateTime(getArrival().getTime()), new DateTime(getDeparture().getTime())).getDays();
    }
    
    private void calculatePrice() {
        int price = 0;
        RoomProvider roomProvider = new RoomProvider();
        price += getRooms().stream().mapToInt(id -> roomProvider.getForId(id).getCategory().getPrice()).sum() * getDays();
        final Map<Integer, Integer> services = getGroupedServiceIds();
        ServiceProvider serviceProvider = new ServiceProvider();
        price += getGroupedServiceIds().keySet().stream().mapToInt(id -> serviceProvider.getForId(id).getPrice() * services.get(id)).sum();
        priceValueLbl.setText(price + " Euro");
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        servicesList = new javax.swing.JList();
        headerLbl = new javax.swing.JLabel();
        idLbl = new javax.swing.JLabel();
        guestLbl = new javax.swing.JLabel();
        guestNameLbl = new javax.swing.JLabel();
        peopleLbl = new javax.swing.JLabel();
        servicesLbl = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        roomsList = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        servicesList2 = new javax.swing.JList();
        roomsLbl = new javax.swing.JLabel();
        arrivalLbl = new javax.swing.JLabel();
        departureLbl = new javax.swing.JLabel();
        cancelBtn = new javax.swing.JButton();
        payedBtn = new javax.swing.JButton();
        cancelReservationBtn = new javax.swing.JButton();
        stateLbl = new javax.swing.JLabel();
        stateTextLbl = new javax.swing.JLabel();
        priceLbl = new javax.swing.JLabel();
        priceValueLbl = new javax.swing.JLabel();
        idValueLbl = new javax.swing.JLabel();
        saveBtn = new javax.swing.JButton();
        addServiceBtn = new javax.swing.JButton();
        removeServiceBtn = new javax.swing.JButton();
        addRoomBtn = new javax.swing.JButton();
        removeRoomBtn = new javax.swing.JButton();
        arrivalSpinner = new javax.swing.JSpinner();
        departureSpinner = new javax.swing.JSpinner();
        peopleSpinner = new javax.swing.JSpinner();

        jScrollPane1.setViewportView(servicesList);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        headerLbl.setText("Reservierung");

        idLbl.setText("ID:");

        guestLbl.setText("Gast:");

        peopleLbl.setText("Personen:");

        servicesLbl.setText("Services");

        jScrollPane2.setViewportView(roomsList);

        jScrollPane3.setViewportView(servicesList2);

        roomsLbl.setText("Räume");

        arrivalLbl.setText("Ankunft:");

        departureLbl.setText("Abreise:");

        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        payedBtn.setText("Bezahlt");
        payedBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payedBtnActionPerformed(evt);
            }
        });

        cancelReservationBtn.setText("Stornieren");
        cancelReservationBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelReservationBtnActionPerformed(evt);
            }
        });

        stateLbl.setText("Status:");

        priceLbl.setText("Preis:");

        saveBtn.setText("Speichern");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        addServiceBtn.setText("+");
        addServiceBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addServiceBtnActionPerformed(evt);
            }
        });

        removeServiceBtn.setText("-");
        removeServiceBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeServiceBtnActionPerformed(evt);
            }
        });

        addRoomBtn.setText("+");
        addRoomBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRoomBtnActionPerformed(evt);
            }
        });

        removeRoomBtn.setText("-");
        removeRoomBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeRoomBtnActionPerformed(evt);
            }
        });

        arrivalSpinner.setModel(new javax.swing.SpinnerDateModel());
        arrivalSpinner.setEditor(new javax.swing.JSpinner.DateEditor(arrivalSpinner, "dd.MM.yyyy"));
        arrivalSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                arrivalSpinnerStateChanged(evt);
            }
        });

        departureSpinner.setModel(new javax.swing.SpinnerDateModel());
        departureSpinner.setEditor(new javax.swing.JSpinner.DateEditor(departureSpinner, "dd.MM.yyyy"));
        departureSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                departureSpinnerStateChanged(evt);
            }
        });

        peopleSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(headerLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(idLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(idValueLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(departureLbl)
                                    .addGap(18, 18, 18)
                                    .addComponent(departureSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(arrivalLbl)
                                    .addGap(18, 18, 18)
                                    .addComponent(arrivalSpinner))
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(addServiceBtn)
                                .addGap(18, 18, 18)
                                .addComponent(removeServiceBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(priceLbl))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(addRoomBtn)
                                .addGap(18, 18, 18)
                                .addComponent(removeRoomBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cancelBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                                        .addComponent(saveBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(payedBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(roomsLbl)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cancelReservationBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(priceValueLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(peopleLbl)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(peopleSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(guestLbl)
                                    .addGap(18, 18, 18)
                                    .addComponent(guestNameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(servicesLbl)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(stateLbl)
                                    .addGap(18, 18, 18)
                                    .addComponent(stateTextLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 489, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(headerLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(idLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(idValueLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guestLbl)
                    .addComponent(guestNameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(peopleLbl)
                    .addComponent(peopleSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(arrivalLbl)
                    .addComponent(arrivalSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(departureLbl)
                    .addComponent(departureSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(servicesLbl)
                            .addComponent(roomsLbl))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(cancelReservationBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(payedBtn)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addRoomBtn)
                        .addComponent(removeRoomBtn)
                        .addComponent(addServiceBtn)
                        .addComponent(removeServiceBtn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stateLbl, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(stateTextLbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancelBtn)
                    .addComponent(priceValueLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(priceLbl))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        dispose();
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void payedBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payedBtnActionPerformed
        reservation.setPayed(new Date());
        payedBtn.setEnabled(false);
    }//GEN-LAST:event_payedBtnActionPerformed

    private void cancelReservationBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelReservationBtnActionPerformed
        reservation.setCanceled(new Date());
        cancelReservationBtn.setEnabled(false);
    }//GEN-LAST:event_cancelReservationBtnActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        Reservation newReservation = new Reservation();
        ReservationProvider reservationProvider = new ReservationProvider();
        SpinnerNumberModel peopleModel = (SpinnerNumberModel) peopleSpinner.getModel();
        newReservation.setPeople((int) peopleModel.getNumber());
        SpinnerDateModel arrivalModel = (SpinnerDateModel) arrivalSpinner.getModel();
        newReservation.setStart(arrivalModel.getDate());
        SpinnerDateModel departureModel = (SpinnerDateModel) departureSpinner.getModel();
        newReservation.setEnd(departureModel.getDate());
        newReservation.setGuest(guest);
        newReservation.setRooms(getRooms());
        if (existing) {
            newReservation.setId(reservation.getId());
        }
        if (existing) {
            reservationProvider.merge(newReservation);
        } else {
            int id = reservationProvider.save(newReservation);
            reservation.setId(id);
            existing = true;
        }
        
        Map<Integer, Integer> serviceIdToAmountMap = getGroupedServiceIds();
        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.setForReservation(reservation, serviceIdToAmountMap);
//        List<Room> rooms = ((RoomListModel) roomsList.getModel()).getRooms();
//        RoomProvider roomProvider = new RoomProvider();
//        roomProvider.setForReservation(reservation, rooms);
    }//GEN-LAST:event_saveBtnActionPerformed

    private void addServiceBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addServiceBtnActionPerformed
        Service[] options = new Service[2];
        new ServiceProvider().getAll().toArray(options);
        Service selected = (Service) JOptionPane.showInputDialog(this, "Service wählen", "Services", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if (selected == null) {
            return;
        }
        ServiceListModel oldModel = (ServiceListModel) servicesList2.getModel();
        ServiceListModel model = new ServiceListModel();
        List<Service> services = oldModel.getServices();
        services.add(selected);
        model.setServices(services);
        servicesList2.setModel(model);
        calculatePrice();
    }//GEN-LAST:event_addServiceBtnActionPerformed

    private void removeServiceBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeServiceBtnActionPerformed
        int[] selected = servicesList2.getSelectedIndices();
        ServiceListModel oldModel = (ServiceListModel) servicesList2.getModel();
        List<Service> toRemove = Arrays.stream(selected).mapToObj(i -> oldModel.getServiceInLine(i)).collect(Collectors.toList());
        List<Service> services = oldModel.getServices();
        toRemove.stream().forEach(service -> services.remove(service));
        ServiceListModel model = new ServiceListModel();
        model.setServices(services);
        servicesList2.setModel(model);
        calculatePrice();
    }//GEN-LAST:event_removeServiceBtnActionPerformed

    private void removeRoomBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeRoomBtnActionPerformed
        List<Room> selected = getSelectedRooms();
        RoomListModel model = new RoomListModel();
        List<Room> oldRooms = ((RoomListModel) roomsList.getModel()).getRooms();
        oldRooms.removeAll(selected);
        model.setRooms(oldRooms);
        roomsList.setModel(model);
        calculatePrice();
    }//GEN-LAST:event_removeRoomBtnActionPerformed

    private void addRoomBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRoomBtnActionPerformed
        List<Room> rooms = ((RoomListModel) roomsList.getModel()).getRooms();
        RoomProvider roomProvider = new RoomProvider();
        List<Room> allRooms = roomProvider.getAll();
        allRooms.removeAll(rooms);        
        allRooms = allRooms.stream().filter(r -> roomProvider.isFree(r, getArrival(), getDeparture())).collect(Collectors.toList());
        Room[] options = new Room[allRooms.size()];
        allRooms.toArray(options);
        if (options.length == 0) {
            return;
        }
        Room selected = (Room) JOptionPane.showInputDialog(this, "Raum wählen", "Räume", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if (selected == null) {
            return;
        }
        rooms.add(selected);
        RoomListModel model = new RoomListModel();
        model.setRooms(rooms);
        roomsList.setModel(model);
        calculatePrice();
    }//GEN-LAST:event_addRoomBtnActionPerformed

    private void arrivalSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_arrivalSpinnerStateChanged
        Date arrival = getArrival();
        if (arrival.before(new Date())) {
            arrivalSpinner.setValue(new Date());
        }
        Date minDep = new Date(arrival.getTime() + ONE_DAY);
        if (arrival.after(new Date(getDeparture().getTime() - ONE_DAY))) {
            departureSpinner.setValue(minDep);
        }
        calculatePrice();
    }//GEN-LAST:event_arrivalSpinnerStateChanged

    private void departureSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_departureSpinnerStateChanged
        Date departure = getDeparture();
        Date min = new Date(getArrival().getTime() + ONE_DAY);
        if (departure.before(min)) {
            departureSpinner.setValue(min);
        }
        calculatePrice();
    }//GEN-LAST:event_departureSpinnerStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addRoomBtn;
    private javax.swing.JButton addServiceBtn;
    private javax.swing.JLabel arrivalLbl;
    private javax.swing.JSpinner arrivalSpinner;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton cancelReservationBtn;
    private javax.swing.JLabel departureLbl;
    private javax.swing.JSpinner departureSpinner;
    private javax.swing.JLabel guestLbl;
    private javax.swing.JLabel guestNameLbl;
    private javax.swing.JLabel headerLbl;
    private javax.swing.JLabel idLbl;
    private javax.swing.JLabel idValueLbl;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton payedBtn;
    private javax.swing.JLabel peopleLbl;
    private javax.swing.JSpinner peopleSpinner;
    private javax.swing.JLabel priceLbl;
    private javax.swing.JLabel priceValueLbl;
    private javax.swing.JButton removeRoomBtn;
    private javax.swing.JButton removeServiceBtn;
    private javax.swing.JLabel roomsLbl;
    private javax.swing.JList roomsList;
    private javax.swing.JButton saveBtn;
    private javax.swing.JLabel servicesLbl;
    private javax.swing.JList servicesList;
    private javax.swing.JList servicesList2;
    private javax.swing.JLabel stateLbl;
    private javax.swing.JLabel stateTextLbl;
    // End of variables declaration//GEN-END:variables
}
