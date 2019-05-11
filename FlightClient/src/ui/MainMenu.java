/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import protocol.objects.FlightSearch;
import utils.DateOperations;
import java.util.List;
import javax.swing.JOptionPane;
import protocol.RmiCommunication;
import protocol.objects.Flight;
import ui.flights.FlightsMenu;

/**
 * MainMenu represents the main menu window.
 *
 */
public class MainMenu extends JFrame {

    private JLabel city_label, info_label;
    private JTextField from, to;
    private JButton departure_date, arrival_date, search;
    private JComboBox passengers;
    private Container pane;

    /**
     * Constructor
     */
    public MainMenu() {
        super("Flights Booking - Tickets");

        //hotel_label image
        city_label = new JLabel(new ImageIcon("resources//city.jpg"));
        city_label.setBounds(00, -47, 810, 400);

        info_label = new JLabel("Flight Bookings App");
        info_label.setBounds(250, 380, 300, 50);
        info_label.setFont(new Font("Courier New", Font.BOLD, 25));

        from = new JTextField("Athens (AT)");
        from.setBounds(40, 150, 150, 30);

        to = new JTextField("SAMOS (SM)");
        to.setBounds(189, 150, 150, 30);

        departure_date = new JButton("2019-1-14");
        departure_date.setBounds(330, 150, 130, 30);
        departure_date.setBackground(Color.white);
        departure_date.addActionListener((ActionEvent e) -> {
            departure_date.setText(new DateMenu(MainMenu.this).setPickedDate());
        });

        arrival_date = new JButton("2019-2-20");
        arrival_date.setBounds(460, 150, 130, 30);
        arrival_date.setBackground(Color.white);
        arrival_date.addActionListener((ActionEvent e) -> {
            arrival_date.setText(new DateMenu(MainMenu.this).setPickedDate());
        });

        final Integer passengers_number[] = {1, 2, 3, 4, 5, 6, 7, 8};
        passengers = new JComboBox(passengers_number);
        passengers.setBounds(590, 149, 120, 31);
        passengers.setBackground(Color.white);

        search = new JButton("Search ->");
        search.setBounds(580, 200, 130, 30);
        search.setBackground(Color.green);
        search.setForeground(Color.white);
        search.addActionListener((ActionEvent e) -> {
            if (checkFields()) {
                if (checkDates()) {
                    //call search flight of rmi server
                    List<Flight> list = RmiCommunication.searchFlights(new FlightSearch(
                            from.getText(), to.getText(), DateOperations.convertStringToDate(departure_date.getText()),
                            DateOperations.convertStringToDate(arrival_date.getText()), (Integer) (passengers.getSelectedItem())
                    ));
                    if (list.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "No flights were found for these information.",
                                "Info", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        //take return list and append it to a new JPanel JTable.
                        new FlightsMenu(list, (Integer) (passengers.getSelectedItem()));
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Check out the dates.Please try again.",
                            "Warn", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Fill in th fields.Please try again.",
                        "Warn", JOptionPane.WARNING_MESSAGE);
            }
        });

        //Set Container, layout
        //Components adding into Frame
        pane = getContentPane();
        pane.setLayout(null);

        this.add(search);
        this.add(passengers);
        this.add(info_label);
        this.add(departure_date);
        this.add(arrival_date);
        this.add(from);
        this.add(to);
        this.add(city_label);
        this.setVisible(true);
        this.setSize(810, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(pane);
        this.repaint();
        this.getRootPane().updateUI();
        //----------------------------------------------------------
    }

    private boolean checkDates() {
        return DateOperations.compareDates(DateOperations.convertStringToDate(departure_date.getText()),
                DateOperations.convertStringToDate(arrival_date.getText()));
    }

    private boolean checkFields() {
        if (!from.getText().equals("")) {
            if (!to.getText().equals("")) {
                return true;
            }
        }
        return false;
    }
}
