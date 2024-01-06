package com.mycompany.hotelreservation;

import javax.swing.JOptionPane;

public class HotelReservation {

    public static void main(String[] args) {
            
        Hotel hotel = new Hotel();
        Bill bill = new Bill();
        Reservation reservation = new Reservation();

        while(true){
            int select = hotel.menu(); //selection from the menu is saved
            switch (select) {
                case 0 -> hotel.information();
                case 1 -> hotel.prices();
                case 2 -> reservation.bookNow();
                case 3 -> bill.billList();
                case 4 -> {
                    //exit the program
                    JOptionPane.showMessageDialog(null,"Have A Nice Day :)\nAnd See You Later....");
                    return;
                }
            }
        }
    }
}