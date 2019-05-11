/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The class DateMenu represents the graphical window of selecting dates.
 */
public class DateMenu {
    //define variables

    private int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
    private int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
    //create object of JLabel with alignment
    private JLabel l = new JLabel("", JLabel.CENTER);
    //define variable
    private String day = "";
    //declaration
    private JDialog d;
    //create object of JButton
    private JButton[] button = new JButton[49];

    /**
     * Constructor DateMenu
     *
     * @param parent. JFrame window
     */
    public DateMenu(JFrame parent)//create constructor 
    {
        //create object
        d = new JDialog();
        //set modal true
        d.setModal(true);
        //define string
        String[] header = {"Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat"};
        //create JPanel object and set layout
        JPanel p1 = new JPanel(new GridLayout(7, 7));
        //set size
        p1.setPreferredSize(new Dimension(430, 120));
        //for loop condition
        for (int x = 0; x < button.length; x++) {
            //define variable
            final int selection = x;
            //create object of JButton
            button[x] = new JButton();
            //set focus painted false
            button[x].setFocusPainted(false);
            //set background colour
            button[x].setBackground(Color.white);
            //if loop condition
            if (x > 6) {//add action listener
                button[x].addActionListener((ActionEvent ae) -> {
                    day = button[selection].getActionCommand();
                    //call dispose() method
                    d.dispose();
                });
            }
            if (x < 7) {//if loop condition 
                button[x].setText(header[x]);
                //set fore ground colour
                button[x].setForeground(Color.red);
            }
            p1.add(button[x]);//add button
        }
        //create JPanel object with grid layout
        JPanel p2 = new JPanel(new GridLayout(1, 3));

        //create object of button for previous month
        JButton previous = new JButton("<< Previous");
        //add action command
        previous.addActionListener((ActionEvent ae) -> {
            //decrement month by 1
            month--;
            //call method
            displayDate();
        });
        p2.add(previous);//add button
        p2.add(l);//add label
        //create object of button for next month
        JButton next = new JButton("Next >>");
        //add action command
        next.addActionListener((ActionEvent ae) -> {
            //increment month by 1
            month++;
            //call method
            displayDate();
        });
        p2.add(next);// add next button
        //set border alignment
        d.add(p1, BorderLayout.CENTER);
        d.add(p2, BorderLayout.SOUTH);
        d.pack();
        //set location
        d.setLocationRelativeTo(parent);
        //call method
        displayDate();
        //set visible true
        d.setVisible(true);
    }

    /**
     * displayDate
     */
    public void displayDate() {
        for (int x = 7; x < button.length; x++) {//for loop
            button[x].setText("");//set text
        }
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        //create object of SimpleDateFormat 
        java.util.Calendar cal = java.util.Calendar.getInstance();
        //create object of java.util.Calendar 
        cal.set(year, month, 1); //set year, month and date
        //define variables
        int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
        int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
        //condition
        for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++) {//set text
            button[x].setText("" + day);
        }
        l.setText(sdf.format(cal.getTime()));
        //set title
        d.setTitle("Date Picker");
    }

    /**
     * setPickedDate
     *
     * @return String date
     */
    public String setPickedDate() {
        //if condition
        if (day.equals("")) {
            return day;
        }
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(year, month, Integer.parseInt(day));
        return sdf.format(cal.getTime());
    }
}
