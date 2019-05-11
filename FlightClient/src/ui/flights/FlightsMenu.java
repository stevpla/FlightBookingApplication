/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.flights;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import protocol.objects.Flight;
import protocol.objects.FlightReservation;
import protocol.RmiCommunication;

/**
 *
 * FlightsMenu represents the window which displays the Flights from the RMI
 * Server.
 */
public class FlightsMenu extends JFrame implements ActionListener {

    private static final int COLUMNS = 6;
    private Object[] columnNames = {"Date", "Departure_airport", "Arrival_airport", "Code",
        "Seats", "Price"};
    private Object[][] data;

    private JTable table;
    private JButton book, search;
    private JLabel label;
    private Container pane;

    private static int GLOBAL_PASSENGERS;
    private static Flight[] FLIGHTS;

    /**
     * Constructor
     *
     * @param list
     * @param passengers
     */
    public FlightsMenu(List<Flight> list, int passengers) {
        super();

        FLIGHTS = new Flight[2];
        GLOBAL_PASSENGERS = passengers;
        //list to 2d object[]
        data = new Object[list.size()][COLUMNS];
        //Fill object array
        for (int x = 0; x < list.size(); x++) {
            data[x][0] = list.get(x).getDate();
            data[x][1] = list.get(x).getDepartureAirport();
            data[x][2] = list.get(x).getArrivalAirport();
            data[x][3] = list.get(x).getCode();
            data[x][4] = list.get(x).getSeats();
            data[x][5] = list.get(x).getPrice();
        }

        table = new JTable(data, columnNames);
        table.setRowSelectionAllowed(true);
        table.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table.setAutoCreateRowSorter(true);
        //table.setBounds(0, 0, 850, 450); 

        // adding it to JScrollPane 
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(200, 20, 750, 500);

        label = new JLabel("''Select the 2 dates to book!''");
        label.setBounds(00, 50, 200, 150);

        book = new JButton("Book");
        book.setBackground(Color.cyan);
        book.setBounds(30, 140, 120, 30);

        search = new JButton("Search");
        search.setBackground(Color.LIGHT_GRAY);
        search.setBounds(10, 480, 140, 30);

        pane = getContentPane();
        pane.setLayout(null);
        book.addActionListener(this);
        search.addActionListener(this);
        this.setTitle("Flights");
        this.add(book);
        this.add(search);
        this.add(label);
        this.add(sp);
        this.setSize(1000, 600);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setContentPane(pane);
        this.repaint();
        this.getRootPane().updateUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object ob = e.getSource();

        if (ob == book) {
            if (table.getSelectedRows().length == 2) {
                int[] selectedrows = table.getSelectedRows();
                for (int i = 0; i < selectedrows.length; i++) {
                    FLIGHTS[i] = new Flight((Date) table.getValueAt(selectedrows[i], 0),
                            (String) table.getValueAt(selectedrows[i], 1),
                            (String) table.getValueAt(selectedrows[i], 2),
                            (String) table.getValueAt(selectedrows[i], 3),
                            (int) table.getValueAt(selectedrows[i], 4),
                            (float) table.getValueAt(selectedrows[i], 5));
                }

                if (RmiCommunication.updateFlights(new FlightReservation(FLIGHTS[0],
                        FLIGHTS[1], GLOBAL_PASSENGERS))) {
                    //option pane flights booking confirmed!!!
                    JOptionPane.showMessageDialog(this, "The flights booking confirmed!",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                    //Return to main menu
                    this.dispose();
                } else {
                    //FLights could not be booked. SOrry!
                    JOptionPane.showMessageDialog(this, "Flights booking could not be confirmed. Sorry.",
                            "Warn", JOptionPane.WARNING_MESSAGE);
                    this.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Select only 2 Flights!",
                        "Warn", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (ob == search) {
            this.dispose();
        }
    }
}
