/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jpmorgan.supersimple.stocks.entity;

import com.jpmorgan.supersimple.stocks.Exception.InvalidOperationException;
import com.jpmorgan.supersimple.stocks.Exception.NotFoundException;
import com.jpmorgan.supersimple.stocks.entity.impl.SimpleStockEntityManagerImpl;
import com.jpmorgan.supersimple.stocks.model.Stock;
import com.jpmorgan.supersimple.stocks.model.Trade;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Vijay Veerasamy
 */
public interface SimpleStockEntityManager {
    
    /**
     * Static Variable of the instance of the implemented class
     */
    public static SimpleStockEntityManager INSTANCE = SimpleStockEntityManagerImpl.getInstance();
    
    
    /**
     * Gets all trades done in the Super Simple Stocks
     * @return ArrayList<Trade>
     */
    public List<Trade> getTrades();
    
    /**
     * Records the trade and returns the status.
     * @param Trade
     * @return boolean
     */
    public boolean recordTrade(Trade trade) throws InvalidOperationException;
    
    /**
     * This will add the stock in to the stock service list available for trading
     * @param Stock
     * @return boolean
     */
    public boolean launchStock(Stock stock) throws InvalidOperationException;
    
    /**
     * Get all stocks listed in the Super Simple Stocks
     * @return HashMap<String, Stock>
     */
    public Map<String, Stock> getStocks();
    
    /**
     * Gets stock by symbol from Super Simple Stocks
     * @param Stock Symbol
     * @return Stock
     */
    public Stock getStockBySymbol(String stockSymbol) throws NotFoundException;
    
    
}
