package com.mycompany.hotelreservation;

import java.util.ArrayList;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.util.Arrays;

public class Reservation {
  
    private Bill bi = new Bill();
    
    public void bookNow(){ //you can open the list of offers or reservation
        while(true){
            final String[] BOOK_LIST = {"Offers","Reservation","Back"};
            int bookChoice = JOptionPane.showOptionDialog(null, "Please Choose The Option You Need", "BOOKING", 
                          JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, BOOK_LIST, BOOK_LIST[0]);
            if(bookChoice==0)
               offers();                 
            if(bookChoice==1)
               makeReservation();                  
            if(bookChoice==2) //exit from bookNow method
                return;                  
        } 
    }
    
    private void makeReservation(){
        ArrayList<String> elementofBill = new ArrayList(Arrays.asList("Number Of Room(s): ", "Number Of Day(s): ","VIP Reservations (T or F): ", "Code of Discount (if any): ", "First Name: ", "Second Name: ", "passport ID: ", "Total: ", "Invocie Number: "));

        int room;
        String strRoom;
        
        while(true){
            boolean flag;
            do{//this loop to write number of room and add the number of bed of each room in array call elemntOfBill
                strRoom = JOptionPane.showInputDialog(null,elementofBill.get(0),
                        JOptionPane.INFORMATION_MESSAGE);
                if(strRoom==null)
                    return;

                room = Integer.parseInt(strRoom);
                
                if(room>=1){//The purpose of this three loop are copy and add the number of bed of each room in array call elemntOfBill
                    for (int j = 1; j < room+1; j++) {
                        elementofBill.add(j,("Number Of Bed in #" +String.valueOf((j))+ " room: "));
                    }
                    flag = false;
                }else{
                    JOptionPane.showMessageDialog(null, "Number of room is incorrect, please write the number correctly.");
                    flag = true;
                }
            }while(flag);
            
            int n = elementofBill.size();
            String[] infoInBill = new String[n];
            infoInBill[0] = strRoom;

            for (int i = 1; i < n-2; i++) { //Enter the info of user and put in array call infoInBill     
                infoInBill[i] = JOptionPane.showInputDialog(null,elementofBill.get(i),
                       JOptionPane.INFORMATION_MESSAGE);
                if(infoInBill[i]==null) //when you click on cancel the program went out this method
                    return;          
            }   
            
            String offerType = infoInBill[room+2];

            infoInBill[n-2] = String.valueOf(total(infoInBill, room) + "$"); //go to calculate total and put in infoInBill
            infoInBill[n-1] = putInvoiceNumber(offerType); //put the number of invoice number (number of bill)
            
            JOptionPane.showMessageDialog( null, new JList( bi.bill(elementofBill,infoInBill,offerType)),
                    "The Bill", JOptionPane.INFORMATION_MESSAGE); //show the Bill
            return;
        }
    }
    
    public static double total(String []info,int ro){//Calculate the price of your chosen stuff in makeReservation method
        
        double total = (ro*45);
        int numberOfBeds=0;
        for (int c = 1; c <= ro; c++) { //Calculate number of beds in rooms you are chose it
            int numBed = Integer.parseInt(info[c]);
            numberOfBeds += numBed;
        }
        total = total +(numberOfBeds*15); 
        
        int numDays = Integer.parseInt(info[ro+1]); //get number of days and change it from String to integer
        total = total * numDays;   
        
        if(info[ro+2].equalsIgnoreCase("T")) //check type of reservation if vip or not
            total = total + (ro*30*numDays);
        
        String discount = info[ro+3]; //if customer enter the discount code here make discount for him
        if(HotelManagement.codeDiscount.contains(discount)){
            int index = HotelManagement.codeDiscount.indexOf(discount);
            total = total - (total*HotelManagement.discountPercent.get(index));
        }
        
        return total;
    }  
    
    public void offers(){ //Show the lists of offers and you can choose one  
        
        while(true){ //choose the type offer you want (vip or normal)
            final String[] OFFER_LIST = {"VIP","Normal","Manage Offer","Back"};
            int offerChoice = JOptionPane.showOptionDialog(null, "Please Choose The Option You Need", "Price", 
            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, OFFER_LIST, OFFER_LIST[0]);
            switch (offerChoice) {
                case 0 -> typeOfOffers(HotelManagement.vipOffer, OFFER_LIST[0]);
                case 1 -> typeOfOffers(HotelManagement.normalOffer, OFFER_LIST[1]);
                case 2 -> {
                    HotelManagement managehotal = new HotelManagement();
                    managehotal.manage();
                }
                case 3 -> {
                    //exit from offers method
                    return;
                }
            }
        }
    }
    
    public void typeOfOffers(ArrayList offer, String offerType){ //Offers displayed in the Offers Method are stored here
        
        int totalNumberOfOffer = offer.size();
        
        if(totalNumberOfOffer > 0){
            int countOffer=0;
            int OfferNumber = 1;
            
            while(true){//surf the offer and you can take one if you want
                final String[] BACK_NEXT = {"back", "Take This Offer","Next"};
                if(countOffer < 0)
                        return;
                while(countOffer >= 0){//show offer
                    int displayOffer = JOptionPane.showOptionDialog(null, new JList((String[])offer.get(countOffer)) , "Offer #"+ String.valueOf(OfferNumber) + " out of #" + String.valueOf(totalNumberOfOffer), 
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, BACK_NEXT, BACK_NEXT[0]);
                    if(displayOffer==0){
                        countOffer--;
                        OfferNumber--;
                    }
                    else if(displayOffer==1){//Go to takeOffer method, and complete the rest of the information to make the bill, then Stop offers method
                        takeOffer((String[]) offer.get(countOffer), offerType);
                        return;
                    }
                    else if(displayOffer==2 && countOffer < (totalNumberOfOffer-1)){
                        countOffer++;
                        OfferNumber++;
                    }
                    else if(countOffer==totalNumberOfOffer)
                        countOffer=1;
                    else{
                        JOptionPane.showMessageDialog( null, "Sorry, we haven't other offer now.",
                        "", JOptionPane.INFORMATION_MESSAGE); 
                    }
                }
            }   
        }else{
                JOptionPane.showMessageDialog( null, "Sorry, we haven't any offer now.",
                        "", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void takeOffer(String[] offer, String offerType){ //when you take offer in offers method you come here to write your information and show the bill
        
        ArrayList<String> elementofBill = new ArrayList(Arrays.asList("Firat Name: ", "Second Name: ", "passport ID: ","Invoice Number: "));
        
        int n = elementofBill.size();          
        String []info = new String[n];
        
        for (int i = 0; i < n-1; i++) { //this code to enter information of user by user           
            info[i] = JOptionPane.showInputDialog(null,elementofBill.get(i),
              JOptionPane.INFORMATION_MESSAGE);
        } 
        info[n-1] = putInvoiceNumber(offerType); //put the number of invoice number (number of bill)
        
        JOptionPane.showMessageDialog( null, new JList(bi.bill(elementofBill, info,offer,offerType)),
           "The Bill", JOptionPane.INFORMATION_MESSAGE); //show the bill
    }
    
    private String putInvoiceNumber(String typeReservation){
        if(typeReservation.equalsIgnoreCase("T") || typeReservation.equalsIgnoreCase("VIP"))
            return String.valueOf(Bill.getVipInvoiceNumber() + " - VIP"); //put the number of invoice number (number of bill)
        else 
            return String.valueOf(Bill.getNormalInvoiceNumber() + " - Normal"); //put the number of invoice number (number of bill)  
    }  
}