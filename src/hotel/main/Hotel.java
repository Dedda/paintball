/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.main;

import hotel.gui.AddReservationFrame;
import hotel.gui.SalaryFrame;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;


/**
 *
 * @author schaefal
 */
public class Hotel extends JFrame implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
    
        if(e.getActionCommand().equals("Erstelle Reservierung")) {
            res.setVisible(true);
        }
        if(e.getActionCommand().equals("Öffne Lohnübersicht")) {
            lohn.setVisible(true);
        }
    }
    
    private JButton resB;
    private JButton lohnB;
    private AddReservationFrame res;
    private SalaryFrame lohn;
    public Hotel() {
        res = new AddReservationFrame();
        lohn = new SalaryFrame();
        
        this.setLayout(new FlowLayout());
        this.setMinimumSize(new Dimension(400, 400));
        resB = new JButton("Erstelle Reservierung");
        lohnB = new JButton("Öffne Lohnübersicht");
        this.add(resB);
        this.add(lohnB);
        lohnB.addActionListener(this);
        resB.addActionListener(this);
        this.setVisible(true);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    
    public static void main(String[] args) {

        Hotel h = new Hotel();
        h.setVisible(true);
        
    }
}
