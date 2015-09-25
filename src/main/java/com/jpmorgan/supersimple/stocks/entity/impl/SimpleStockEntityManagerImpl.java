/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jpmorgan.supersimple.stocks.entity.impl;

import com.jpmorgan.supersimple.stocks.Exception.InvalidOperationException;
import com.jpmorgan.supersimple.stocks.Exception.NotFoundException;
import com.jpmorgan.supersimple.stocks.entity.SimpleStockEntityManager;
import com.jpmorgan.supersimple.stocks.model.Stock;
import com.jpmorgan.supersimple.stocks.model.Trade;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Vijay Veerasamy
 */
public class SimpleStockEntityManagerImpl implements SimpleStockEntityManager {
    
    private static Map<String, Stock> stocks = new HashMap<String, Stock>();

    private static List trades = new ArrayList();
            
    private static SimpleStockEntityManager INSTANCE = new SimpleStockEntityManagerImpl();
    
    /**
     * Private constructor for singleton
     */
    private SimpleStockEntityManagerImpl () {
        //Can't instantiated by others
    }
    
    public static SimpleStockEntityManager getInstance() {
        return INSTANCE;
    }
            
    @Override
    public List<Trade> getTrades() {
        return trades;
    }

    @Override
    public boolean recordTrade(Trade trade) throws InvalidOperationException {
        
        if ( trade!=null && trade instanceof Trade ) {
            trades.add(trade);
            trade.getStock().setMarketPrice(trade.getTradePrice());
            return true;
        }
        else return false;
    }
    
    @Override
    public boolean launchStock(Stock stock) throws InvalidOperationException {
  
        if (stock!=null && stock instanceof Stock) {
            stocks.put(stock.getStockSymbol(), stock);
            return true;
        }
        else return false;

    }
    
    @Override
    public Map<String, Stock> getStocks() {
        return stocks;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Stock getStockBySymbol(String stockSymbol) throws NotFoundException {
        return stocks.get(stockSymbol);
    }
}
