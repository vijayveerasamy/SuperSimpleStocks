/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jpmorgan.supersimple.stocks.util;

import com.jpmorgan.supersimple.stocks.model.Trade;
import java.util.Calendar;
import org.apache.commons.collections.Predicate;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author Vijay Veerasamy
 */
public class SimplePredicate implements Predicate{
    
    private String stockSymbol = "";
    private Calendar dateRange = null;


    public SimplePredicate(String stockSymbol, int pastMinutes){
        this.stockSymbol = stockSymbol;
        if( pastMinutes > 0 ){
            dateRange = Calendar.getInstance();
            dateRange.add(Calendar.MINUTE, -pastMinutes);
            Logger.getLogger(SimplePredicate.class.getName()).log(Level.INFO, "Filter objects by date and time: "+ dateRange.getTime() );
        }

    }
    
    public boolean evaluate(Object tradeObject) {
        Trade trade = (Trade) tradeObject;
        boolean shouldInclude = trade.getStock().getStockSymbol().equals(stockSymbol);
        if(shouldInclude==true && dateRange != null){
            shouldInclude = dateRange.getTime().compareTo(trade.getTimeStamp())<=0;
        }
        return shouldInclude;
    }
                
}
