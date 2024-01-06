package com.mycompany.hotelreservation;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class Bill {
    
    private static ArrayList<String[]> vipListStorage = new ArrayList(50);
    private static ArrayList<String[]> normalListStorage = new ArrayList(50);
    
    private static int vipInvoiceNumber = 1;
    private static int normalInvoiceNumber = 1;

    public static int getVipInvoiceNumber() {
        return vipInvoiceNumber;
    }

    public static int getNormalInvoiceNumber() {
        return normalInvoiceNumber;
    }
    
    static int vipCapacity = 50;
    static int normalCapacity = 50;
    
    private void INCREASE(){ //increase capacity by 50
        if(vipListStorage.size() == vipCapacity){
            vipCapacity += 50;
            vipListStorage.ensureCapacity(vipCapacity);
        }
        if(normalListStorage.size() == normalCapacity){
            normalCapacity += 50;
            normalListStorage.ensureCapacity(normalCapacity);
        }
    }
       
    public String[] bill(ArrayList elementBill, String[] list, String offerType){ //make the bill after you reservation in makeReservation method
        
        int nElement = elementBill.size();      
        String []bi = new String[nElement];
        
        for (int i = 0; i < nElement; i++) {
            bi[i] = elementBill.get(i) + list[i]; 
        } //Merge the items in (elemntBill) and information in (info) that were given, and put them in some matrix to display bill 
        billStorage(bi, offerType);
        return bi;
    }
    
    public String[] bill(ArrayList elementBill, String[] list, String []offer, String offerType){ //make the bill after customer thake offer in offers method
    
        int nElement = elementBill.size();
        int nOffer = offer.length;     
        
        String []bi = Arrays.copyOf(offer, nElement+nOffer);
        
        for (int i = 0,p = nOffer; i < nElement; i++,p++) {
            bi[p] = elementBill.get(i) + list[i];
        } //Merge the items in (elemntBill) and information in (info) that were given, and put them in some matrix to display bill  
        billStorage(bi, offerType);
        return bi;
   }
    
    private void billStorage(String[] list, String offerType){
        if(offerType.equalsIgnoreCase("T") || offerType.equalsIgnoreCase("vip")){
            vipListStorage.add(list);
            vipInvoiceNumber++;
        }
        else{
            normalListStorage.add(list);
            normalInvoiceNumber++;
        }
        INCREASE();
    }
    
    public void billList(){ 
         while(true){
            String offerType = JOptionPane.showInputDialog(null,"Enter offer type which you want to show (write: vip or normal): ","Bill List",
            JOptionPane.INFORMATION_MESSAGE);
            if(offerType == null)
                return;
            
            String strBill= JOptionPane.showInputDialog(null,"Enter the invoice number of bill which you want to show: ","Bill List",
            JOptionPane.INFORMATION_MESSAGE);
            if(strBill == null)
                return;
            
            int numberOfBill = Integer.parseInt(strBill);
            showBillStorage(numberOfBill,offerType); //go to showBillStorage method to check the invoice number which written by user          
        }
    }
    
    private void showBillStorage(int numBill, String offerType){
        int n = numBill;
        if(offerType.equalsIgnoreCase("vip")){
            if(n>0 && n<vipInvoiceNumber){
                System.out.println("The Bill of this invoice number " + numBill + " is:");
                System.out.println();
                n = n - 1;
                System.out.println(Arrays.toString(vipListStorage.get(n)));
                System.out.println("-----------------------------------------------");
            }else{
                JOptionPane.showMessageDialog(null, "There is no invoice with this invoice number: " + numBill);
            }
        }else{
            if(n>0 && n<normalInvoiceNumber){
                System.out.println("The Bill of this invoice number " + numBill + " is:");
                System.out.println();
                n = n - 1;
                System.out.println(Arrays.toString(normalListStorage.get(n)));
                System.out.println("-----------------------------------------------");
            }else{
                JOptionPane.showMessageDialog(null, "There is no invoice with this invoice number: " + numBill);
            }
        }
    }
}