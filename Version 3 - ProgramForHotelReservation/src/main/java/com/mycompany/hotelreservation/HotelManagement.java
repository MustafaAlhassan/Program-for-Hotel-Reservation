package com.mycompany.hotelreservation;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class HotelManagement {
    
    private final char[] PASSWORD = {'1','2','3'};
    static ArrayList<String[]> vipOffer = new ArrayList();
    static ArrayList<String[]> normalOffer = new ArrayList();
    static ArrayList<String> codeDiscount = new ArrayList();
    static ArrayList<Float> discountPercent = new ArrayList();
   
    
    private boolean CheckPassword(String password){
        
        boolean check = true;
        if(password.length() == PASSWORD.length){
            int n = password.length();
            for (int i = 0; i < n; i++) {
                if(PASSWORD[i] != password.charAt(i))
                    check = false;
            }
        }else{
            check = false;
        }
        return check;
    }
    
    public void manage(){
        String password = JOptionPane.showInputDialog(null,"Note: This property is for hotel management only\nEnter password:",
                        JOptionPane.INFORMATION_MESSAGE);
        if(CheckPassword(password)){
            manageOffer();
        }else{
             JOptionPane.showMessageDialog( null, "The password is not correct.",
                    "Error", JOptionPane.INFORMATION_MESSAGE); //show the Bill
        }
    }
    
    public void manageOffer(){
        final String[] MANAGE_OFFER = {"Add Offer","Remove Offer","Modify Offer","Manage Discount","Back"};
        
        while(true){
            int Choice = JOptionPane.showOptionDialog(null, "Please Choose The Option You Need", "Manage Offer", 
            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, MANAGE_OFFER, MANAGE_OFFER[0]);

            switch (Choice) {
                case 0 -> addOffer();
                case 1 -> removeOffer();
                case 2 -> replaceOffer();
                case 3 -> manageDiscount();
                default -> {
                    //To get back
                    return;
                }
            }
        }
    }
    
    private void addOffer(){          
        ArrayList<String> elementofBill = new ArrayList(Arrays.asList("Number Of Room(s): ", "Number Of Day(s): ","Supports: ", "Price: $"));
        
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

            for (int i = 1; i < n; i++) { //Enter the info of user and put in array call infoInBill     
                infoInBill[i] = JOptionPane.showInputDialog(null,elementofBill.get(i),
                       JOptionPane.INFORMATION_MESSAGE);
                if(infoInBill[i]==null) //when you click on cancel the program went out this method
                    return;          
            }   
            
            final String[] ADD_OFFER = {"VIP Offer","Normal Offer"};
        
            int Choice = JOptionPane.showOptionDialog(null, "You need to add this offer to VIP or Normal Offer."
                    + "Please, choose one.", "", 
            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, ADD_OFFER, ADD_OFFER[0]);

            if(Choice == 0){
                vipOffer.add(combine(elementofBill,infoInBill));
            }
            else{
                normalOffer.add(combine(elementofBill,infoInBill));
            }

            JOptionPane.showMessageDialog( null, "The offer added successful!",
                    "", JOptionPane.INFORMATION_MESSAGE);
                        
            return;
        }
    }
    
    private void removeOffer(){   
        while(true){
            final String[] REMOVE_OFFER = {"VIP Offer","Normal Offer"};

            int Choice = JOptionPane.showOptionDialog(null, "You need to remove offer from VIP or Normal Offer?"
                       + "Please, choose one.", "", 
               JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, REMOVE_OFFER, REMOVE_OFFER[0]);
            int totalNumberOfOffer;
            String strNumOffer;
            int numOffer;
            
            if(Choice == 0)
                totalNumberOfOffer = vipOffer.size();
            else
                totalNumberOfOffer = normalOffer.size();
            
            if(totalNumberOfOffer != 0){
                strNumOffer = JOptionPane.showInputDialog(null,"Enter number of offer you want to remove it:",
                        JOptionPane.INFORMATION_MESSAGE);
                numOffer = Integer.parseInt(strNumOffer);
                if(numOffer <= totalNumberOfOffer){
                    if(Choice == 0){
                        numOffer = numOffer-1;
                        vipOffer.remove(numOffer);
                    }else{
                        numOffer = numOffer-1;
                        normalOffer.remove(numOffer);
                    }
                    JOptionPane.showMessageDialog( null, "The offer remove successful!",
                        "", JOptionPane.INFORMATION_MESSAGE); 
                    return;
                }
            }else{
                JOptionPane.showMessageDialog( null, "There wasn't any offer there.",
                        "", JOptionPane.INFORMATION_MESSAGE); 
                return;
            }
            
        }
    }
        
    private void replaceOffer(){ 
        int choice;
        int numOffer = -1;
        
        while(true){
            final String[] ADD_OFFER = {"VIP Offer","Normal Offer"};

            choice = JOptionPane.showOptionDialog(null, "You want to replace offer in VIP or Normal Offer."
                    + "Please, choose one.", "", 
            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, ADD_OFFER, ADD_OFFER[0]);    

            if(choice == 0 && vipOffer.isEmpty()){
                JOptionPane.showMessageDialog( null, "There wasn't any offer there.",
                            "", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            else if (choice == 1 && normalOffer.isEmpty()){
                JOptionPane.showMessageDialog( null, "There wasn't any offer there.",
                            "", JOptionPane.INFORMATION_MESSAGE);
                break;
            }

            int totalNumberOfOffer;

            if(choice == 0)
                totalNumberOfOffer = vipOffer.size();
            else
                totalNumberOfOffer = normalOffer.size();

            String strNumOffer = JOptionPane.showInputDialog(null,"Enter number of offer you want to replace it:",
                            JOptionPane.INFORMATION_MESSAGE);
            numOffer = Integer.parseInt(strNumOffer);

            if(numOffer > totalNumberOfOffer){
                JOptionPane.showMessageDialog( null, "This offer number does not exist",
                        "", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
        
            JOptionPane.showMessageDialog( null, "Enter new offer",
                            "", JOptionPane.INFORMATION_MESSAGE);

            ArrayList<String> elementofBill = new ArrayList(Arrays.asList("Number Of Room(s): ", "Number Of Day(s): ","Supports: ", "Price: "));

            boolean flag;
            int room;
            String strRoom;

            while(true){
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

                for (int i = 1; i < n; i++) { //Enter the info of user and put in array call infoInBill     
                    infoInBill[i] = JOptionPane.showInputDialog(null,elementofBill.get(i),
                           JOptionPane.INFORMATION_MESSAGE);
                    if(infoInBill[i]==null) //when you click on cancel the program went out this method
                        return;          
                }   

                numOffer = numOffer - 1;

                if(choice == 0){
                    vipOffer.set(numOffer, combine(elementofBill,infoInBill));
                }else{
                    normalOffer.set(numOffer, combine(elementofBill,infoInBill));
                }

                JOptionPane.showMessageDialog( null, "The offer replace successful!",
                        "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
    }
    
    public String[] combine(ArrayList elementOfOffer, String[] offer){ //make the bill after you reservation in makeReservation method
        
        int n = elementOfOffer.size();
        
        String []bi = new String[n];
        for (int i = 0; i < n; i++) {
            if(i == (n-2)){ //to add this symbol ($) at end of total
                bi[i] = elementOfOffer.get(i) + offer[i]; 
            }
            if(i != (n-2))
            bi[i] = elementOfOffer.get(i) + offer[i]; 
        } //Merge the items in (elemntBill) and information in (info) that were given, and put them in some matrix to display bill 
        return bi;
    }

    private void manageDiscount() {
        final String[] MANAGE_OFFER = {"Add code for discount","Remove code","Display","Back"};
        
        while(true){
            int Choice = JOptionPane.showOptionDialog(null, "Please Choose The Option You Need", "Manage Discount", 
            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, MANAGE_OFFER, MANAGE_OFFER[0]);

            switch (Choice) {
                case 0 -> addCode();
                case 1 -> removeCode();
                case 2 -> displayDiscountCode();
                default -> {
                    //To get back
                    return;
                }
            }
        }
    }

    private void addCode() {
        while(true){
            String code = JOptionPane.showInputDialog(null,"Enter code of discount: ",
                           JOptionPane.INFORMATION_MESSAGE);
            if(code == null)
                return;
            else if(codeDiscount.contains(code)){
                JOptionPane.showMessageDialog(null, "This code exists", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }else{
                while(true){
                    String strPercent = JOptionPane.showInputDialog(null,"Enter percent of discount (%): ",
                               JOptionPane.INFORMATION_MESSAGE);
                    if(strPercent == null)
                        return;
                    float percent = Integer.parseInt(strPercent);
                    if(percent < 1 || percent > 100){
                        JOptionPane.showMessageDialog(null, "You can't write percent greater than 100 and smaller than 1", "", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    percent = percent/100;
                    codeDiscount.add(code);
                    discountPercent.add(percent);
                    JOptionPane.showMessageDialog(null, "The code of discount was add successful!", "", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
        }
    }

    private void removeCode() {
        if(codeDiscount.isEmpty())
            JOptionPane.showMessageDialog(null, "There are no discount codes currently", "", JOptionPane.INFORMATION_MESSAGE);
        else{
            while(true){
                String code = JOptionPane.showInputDialog(null,"Enter code of discount: ",
                               JOptionPane.INFORMATION_MESSAGE);
                if(codeDiscount.contains(code)){
                    discountPercent.remove(codeDiscount.indexOf(code));
                    codeDiscount.remove(code);
                    JOptionPane.showMessageDialog(null, "The code of discount was remove successful!", "", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }else{
                    JOptionPane.showMessageDialog(null, "The code not exists", "", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
        }
    }

    private void displayDiscountCode() {
        if(codeDiscount.isEmpty())
            JOptionPane.showMessageDialog(null, "There are no discount codes currently", "", JOptionPane.INFORMATION_MESSAGE);
        else{
            System.out.println(Arrays.toString(codeDiscount.toArray()));
            System.out.println(Arrays.toString(discountPercent.toArray()));
        }
    }   
}