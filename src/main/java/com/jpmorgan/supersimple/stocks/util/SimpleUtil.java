/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jpmorgan.supersimple.stocks.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author Vijay Veerasamy
 */
public class SimpleUtil {
    
    /**
     * This method returns new date by moving minutes in the past/future by minutes
     * @param from minutes
     * @return 
     */    
    public static Date getRandomMinutesPastNow(int minutesMin, int minutesMax){
        Random rand = new Random();
        int randomMinutes = (rand.nextInt((minutesMax - minutesMin) + 1) + minutesMin)*-1;
        
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, randomMinutes);
        return now.getTime();
    }
       
    public static int getRandomNumber(int minValue, int maxValue) {
        Random rand = new Random();
        return rand.nextInt((maxValue - minValue) + 1) + minValue;
    }
}
