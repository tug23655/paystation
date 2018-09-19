package paystation.domain;

import java.util.*;
/**
 * Implementation of the pay station.
 *
 * Responsibilities:
 *
 * 1) Accept payment; 
 * 2) Calculate parking time based on payment; 
 * 3) Know earning, parking time bought; 
 * 4) Issue receipts; 
 * 5) Handle buy and cancel events.
 *
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 *
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
public class PayStationImpl implements PayStation {
    
    private int insertedSoFar;
    private int timeBought;
    private int total = 0;
    
    public Map<Integer, Integer> myMap = new HashMap<Integer, Integer>();
    
    public PayStationImpl(){
       myMap.put(5, 0);
       myMap.put(10, 0);
       myMap.put(25, 0);
   }
       

    @Override
    public void addPayment(int coinValue)
            throws IllegalCoinException {
        switch (coinValue) {
            case 5: 
                myMap.put(5, myMap.get(5)+1);
                break;
            case 10: 
                myMap.put(10, myMap.get(10)+1);
                break;
            case 25: 
                myMap.put(25, myMap.get(25)+1);
                break;
            default:
                throw new IllegalCoinException("Invalid coin: " + coinValue);
        }
        insertedSoFar += coinValue;
        timeBought = insertedSoFar / 5 * 2;
        
    }

    @Override
    public int readDisplay() {
        return timeBought;
    }

    @Override
    public Receipt buy() {
        Receipt r = new ReceiptImpl(timeBought);
        
        total += insertedSoFar;
        reset();
        
        myMap.put(5, 0);
        myMap.put(10, 0);
        myMap.put(25, 0);
        
        return r;
    }

    @Override
    public Map<Integer, Integer> cancel() {
        
        reset();
       
        Map<Integer, Integer> temp = new HashMap<Integer, Integer>();
        
        temp.put(5, myMap.get(5));
        temp.put(10, myMap.get(10));
        temp.put(25, myMap.get(25));
        
        myMap.put(5, 0);
        myMap.put(10, 0);
        myMap.put(25, 0);
        
        
        return temp;
    }
    
    private void reset() {
        timeBought = insertedSoFar = 0;
    }
    
    
    public int empty(){
        int moneyCollected=0;
        moneyCollected = total;
        total = 0;
        return moneyCollected;
    }
    
   
           
}
