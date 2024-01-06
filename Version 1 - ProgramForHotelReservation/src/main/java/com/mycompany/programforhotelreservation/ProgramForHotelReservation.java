package com.mycompany.programforhotelreservation;

import javax.swing.JList;
import javax.swing.JOptionPane;

public class ProgramForHotelReservation {
    
    public static void main(String[] args) {
            boolean flag = true;
            
            while(flag){
                int select = menu(); //selection from the menu is saved
                switch (select) {
                    case 0:
                        information(); break;
                    case 1:
                        prices(); break;
                    case 2:
                        bookNow(); break;
                    case 3:
                        billList(); break;
                    case 4: //exit the program
                        JOptionPane.showMessageDialog(null,"Have A Nice Day :)\nAnd See You Later....");
                        flag=false; break;
                }
            }
    }

    static int invoiceNumber = 1;
    
    public static int menu(){ //Display menu and choose one of options
        String[] options = {"INFO", "Price","Book Now","Bill List","EXIT"};
        int selection = JOptionPane.showOptionDialog(null, "Please Choose The Option You Need", "WELCOME TO AMI HOTEL", 
        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        return selection;
    }
    
    public static void information(){ //Display info for this hotal
         JOptionPane.showMessageDialog(null,"Hello Dear \n\nWelcome to our hotel AMI, we are so glad\nfor your visit, "
                            + "Our hotel founded in 2023\nWe are pleasedthat our hotel has a five\nrating based on the international rating.\n"
                            + "An overview of the advantages of the hotel\nWe have the most luxurious halls for meetings,\nconferences, etc, "
                            + "and we also have a gym, a spacious\nswimming pool, we have a large dining hall,\nand we also have morning breakfast, "
                            + "and room \nservice is available around the clock", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
    }
      
    public static void prices(){ //Show prices in general
        JOptionPane.showMessageDialog(null, "One Room--One Bed--For One Day -->> 60$\n"
                +"One Room--two Bed--For One Day -->> 75$\n"
                +"One Room--three Bed--For One Day -->> 90$\n"
                +"two Room--Each Room One Bed--For One Day -->> 120$\n"
                +"two Room--Each Room two Bed--For One Day -->> 150$\n"
                +"The previous prices are normal, but if it was vip, it will increase by $30",
                "PRICES",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void bookNow(){ //By way you can open the list of offers or reservation
            boolean flag = true;
            booking:{
                while(flag){
                    String[] bookList = { "Offers","Reservation","Back"};
                    int bookChoice = JOptionPane.showOptionDialog(null, "Please Choose The Option You Need", "BOOKING", 
                                  JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, bookList, bookList[0]);
                    
                    if(bookChoice==0)
                       offers();                 
                    if(bookChoice==1)
                       makeReservation();                  
                    if(bookChoice==2) //exit from bookNow method
                        break booking;                  
                }
            }
    }
      
    public static void offers(){ //Show the lists of offers and you can choose one  
        boolean loop = true;
        offers:{
            while(loop){ //choose the type offer you want (vip or normal)
                String[] offerList = { "VIP","Normal","Back"};
                int offerChoice = JOptionPane.showOptionDialog(null, "Please Choose The Option You Need", "Price", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, offerList, offerList[0]);
                if(offerChoice == 2){//exit from offers method
                    break offers;
                }
                vipOrNormal:{
                    while(offerChoice == 0 | offerChoice == 1){//surf the offer and you can take one if you want
                            String[] backOrNext = { "back", "Take This Offer","Next"};
                            int countOffer=1;
                            while(countOffer > 0){//show offer
                                  int displayOffer = JOptionPane.showOptionDialog(null, new JList(typeOfOffers(countOffer,offerChoice)) , "The Prices ", 
                                  JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, backOrNext, backOrNext[0]); 
                                  if(displayOffer==2)
                                      countOffer++;
                                  if(displayOffer==0)
                                      break vipOrNormal;
                                  if(countOffer==4)
                                      countOffer=1;
                                  if(displayOffer==1){//Go to takeOffer method, and complete the rest of the information to make the bill, then Stop offers method
                                      takeOffer(typeOfOffers(countOffer,offerChoice));
                                      break offers;
                                    }
                                        
                            }                      
                    }
                }
            }
        }
    }
      
      public static String []typeOfOffers(int offerNumber, int type){ //Offers displayed in the Offers Method are stored here
          String []offer = new String[6];
          
          if(type==0){ //VIP Offers
          String [] firstVipOffer = {"First Offer","One Room" ,"One Bed","For Three Day","Supports: gym and pool","The Price Is 200$"};   
          String [] secondVipOffer = {"Second Offer","two Room" ,"two Bed","For two Day","Supports: gym and pool","The Price Is 210$"};
          String [] thirdVipOffer = {"Third Offer","One Room" ,"two Bed","For Seven Day","Supports: gym and pool","The Price Is 300$"};                                   
          if(offerNumber==1)
              offer=firstVipOffer;
          if(offerNumber==2)
              offer=secondVipOffer;
          if(offerNumber==3)    
              offer=thirdVipOffer;
          }
          
          if(type==1){ //Normal Offers
          String [] firstNormalOffer = {"First Offer","One Room" ,"One Bed","For Three Day","The Price Is 120$"};   
          String [] secondNormalOffer = {"Second Offer","two Room" ,"two Bed","For two Day","The Price Is 160$"};
          String [] thirdNormalOffer = {"Third Offer","One Room" ,"two Bed","For Seven Day","The Price Is 200$"};                                  
          if(offerNumber==1)
              offer=firstNormalOffer;
          if(offerNumber==2)
              offer=secondNormalOffer;
          if(offerNumber==3)    
              offer=thirdNormalOffer;
          }       
          return offer;
      }
      
      public static void takeOffer(String []offer){ //when you take offer in offers method you come here to write your information and show the bill
             String []customerInfo = {"Firat Name: ", "Second Name: ", "passport ID: ","Invoice Number: "};
             String []info = new String[customerInfo.length];
             for (int i = 0; i < customerInfo.length-1; i++) { //this code to enter information of user by user           
                 info[i] = JOptionPane.showInputDialog(null,customerInfo[i],
                   JOptionPane.INFORMATION_MESSAGE);
             } 
             info[customerInfo.length-1] = String.valueOf(invoiceNumber); //put the number of invoice number (number of bill)
             invoiceNumber++;
             JOptionPane.showMessageDialog( null, new JList(Bill(customerInfo, info,offer)),
                "The Bill", JOptionPane.INFORMATION_MESSAGE); //show the bill
      }
      
      public static void makeReservation(){//make reservation       
        String []elementOfBill={"Number Of Room(s): ", "Number Of Day(s): ","VIP Reservations (T or F): ", "Code of Discount (if any): ", "First Name: ", "Second Name: ", "passport ID: ", "Total: ", "Invocie Number: "};
        String []infoInBill;
        String []newElementOfBill; 
        boolean flag;
        int room;
        
        reservation:{
            while(true){
                
                do{//this loop to write number of room and add the number of bed of each room in array call elemntOfBill
                    String strRoom = JOptionPane.showInputDialog(null,elementOfBill[0],
                            JOptionPane.INFORMATION_MESSAGE);
                    if(strRoom==null)
                        break reservation;

                    room = Integer.parseInt(strRoom);
                    infoInBill = new String[elementOfBill.length+room];
                    newElementOfBill = new String[elementOfBill.length+room]; 
                    infoInBill[0]=strRoom;

                    if(room>=1){//The purpose of this three loop are copy and add the number of bed of each room in array call elemntOfBill
                        for (int j = 0; j < 1; j++) {
                            newElementOfBill[j] = elementOfBill[j];
                        }
                        for (int j = 0; j < room; j++) {
                            newElementOfBill[j+1] = "Number Of Bed in #" +String.valueOf((j+1))+ " room: ";
                        }
                        for (int j = 1; j < elementOfBill.length; j++) {
                            newElementOfBill[j + room] = elementOfBill[j];
                        }
                        flag = false;
                    }else{
                        JOptionPane.showMessageDialog(null, "Number of room is incorrect, please write the number correctly.");
                        flag = true;
                    }
                }while(flag);

                for (int i = 1; i < infoInBill.length-2; i++) { //Enter the info of user and put in array call infoInBill          
                    infoInBill[i] = JOptionPane.showInputDialog(null,newElementOfBill[i],
                           JOptionPane.INFORMATION_MESSAGE);
                    if(infoInBill[i]==null) //when you click on cancel the program went out this method
                        break reservation;            
                }        

                infoInBill[newElementOfBill.length-2] = String.valueOf(total(infoInBill, room)); //go to calculate total and put in infoInBill
                infoInBill[newElementOfBill.length-1] = String.valueOf(invoiceNumber); //put the number of invoice number (number of bill)
                invoiceNumber++;

                JOptionPane.showMessageDialog( null, new JList(Bill(newElementOfBill, infoInBill)),
                        "The Bill", JOptionPane.INFORMATION_MESSAGE); //show the Bill
                break reservation;
            }
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
        if(discount.equalsIgnoreCase("ami00"))
            total = total -(total*0.1);
        if(discount.equalsIgnoreCase("code100"))
            total = total -(total*0.05);
        
        return total;
    }
    
    public static String[] Bill(String []elementBill, String []info){ //make the bill after you reservation in makeReservation method
        String []bi = new String[elementBill.length];
        for (int i = 0; i < bi.length; i++) {
            if(i == (elementBill.length-2)){ //to add this symbol ($) at end of total
                bi[i] = elementBill[i] + info[i] + "$"; 
            }
            if(i != (elementBill.length-2))
            bi[i] = elementBill[i] + info[i]; 
        } //Merge the items in (elemntBill) and information in (info) that were given, and put them in some matrix to display bill 
        billStorage(bi);
        return bi;
    }
    public static String[] Bill(String []elementBill, String []info, String []offer){ //make the bill after customer choose offer in offers method
        String []bi = new String[elementBill.length+offer.length];
        for (int i = 0; i < offer.length; i++) {
            bi[i] = offer[i];
        }
        for (int i = 0,p = offer.length; i < elementBill.length; i++,p++) {
            bi[p] = elementBill[i] + info[i];
        } //Merge the items in (elemntBill) and information in (info) that were given, and put them in some matrix to display bill  
        billStorage(bi);
        return bi;
    }
    
    public static void billList(){ //print the bill which user enter the invoice number for it.
        while(true){
                String strBill= JOptionPane.showInputDialog(null,"Enter the invoice number of bill which you want to show: ","BILL LIST",
                JOptionPane.INFORMATION_MESSAGE);
                if(strBill == null){
                    break;
                }
                int numberOfBill = Integer.parseInt(strBill);
                showBillStorage(numberOfBill); //go to showBillStorage method to check the invoice number which written by user          
        }
    }
    
    static String [][]billList = new String[100][30];
    public static String[][] billStorage(String []bill){ //this code storage the bill in 2D array
        for (int i = invoiceNumber-1; i < invoiceNumber; i++) {
            for (int j = 0; j < bill.length; j++) {
                billList[i][j] = bill[j];
            }
        }
        return billList;
    }
    
    public static void showBillStorage(int numBill){
        if(numBill<invoiceNumber){
            System.out.println("The Bill of this invoice number " + numBill + " is:");
            System.out.println();
            for (int i = numBill; i < numBill+1; i++) {
                for (int j = 0; j < billList[i].length; j++) {
                    if(billList[i][j] == null)
                        break;
                    System.out.println(billList[i][j]+" ");
                }
            } 
        }else{
            JOptionPane.showMessageDialog(null, "There is no invoice with this invoice number: " + numBill);
        }
        System.out.println("-----------------------------------------------");
    }
    
}