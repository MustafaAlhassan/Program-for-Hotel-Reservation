package com.mycompany.hotelreservation;

import javax.swing.JOptionPane;

public class Hotel {
    
    private final String[] OPTIONS = {"INFO", "Price","Book Now","Bill List","EXIT"};
      
    public int menu(){ //Display menu and choose one of options
        int selection = JOptionPane.showOptionDialog(null, "Please Choose The Option You Need", "WELCOME TO AMI HOTEL", 
        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, OPTIONS, OPTIONS[0]);
        return selection;
    }
    
    public void information(){ //Display info for this hotal
        JOptionPane.showMessageDialog(null, """
                                            Hello Dear 
                                            
                                            Welcome to our hotel AMI, we are so glad
                                            for your visit, Our hotel founded in 2023
                                            We are pleasedthat our hotel has a five
                                            rating based on the international rating.
                                            An overview of the advantages of the hotel
                                            We have the most luxurious halls for meetings,
                                            conferences, etc, and we also have a gym, a spacious
                                            swimming pool, we have a large dining hall,
                                            and we also have morning breakfast, and room 
                                            service is available around the clock""", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
    }
      
    public void prices(){ //Show prices in general
        JOptionPane.showMessageDialog(null, """
                                            One Room--One Bed--For One Day -->> 60$
                                            One Room--two Bed--For One Day -->> 75$
                                            One Room--three Bed--For One Day -->> 90$
                                            two Room--Each Room One Bed--For One Day -->> 120$
                                            two Room--Each Room two Bed--For One Day -->> 150$
                                            The previous prices are normal, but if it was vip, it will increase by $30""",
                "PRICES",JOptionPane.INFORMATION_MESSAGE);
    }
}