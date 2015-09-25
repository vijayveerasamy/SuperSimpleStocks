/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jpmorgan.supersimple.stocks.service;

import com.jpmorgan.supersimple.stocks.entity.SimpleStockEntityManager;
import com.jpmorgan.supersimple.stocks.model.Stock;
import com.jpmorgan.supersimple.stocks.model.Trade;
import com.jpmorgan.supersimple.stocks.service.impl.SimpleStockServiceImpl;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Vijay Veerasamy
 */
public interface SimpleStockService {
    
    /**
     * Singleton Instance of SimpleStockService set by implemented class
     */
    public static SimpleStockService INSTANCE = SimpleStockServiceImpl.getInstance();
    
    /**
     * Launch stock for trading
     * @param stock
     * @return boolean
     */
    public boolean launchStock(Stock stock);
    
    /**
     * This method returns for the input stock symbol
     * @param StockSymbol
     * @return Stock
     */
    public Stock getStockBySymbol(String stockSymbol);
    
    /**
     * This method will record the stock trade
     * @param Trade
     * @return boolean
     */
    public boolean recordTrade(Trade trade);
    
    /**
     * This will return dividend yield for the input stock symbol.
     * @param Stock Symbol
     * @return 
     */
    public double getDividendYield(String stockSymbol);
    
    /**
     * This will return P/E ratio for the input stock symbol
     * @param Stock Symbol
     * @return 
     */
    public double getPERatio(String stockSymbol);
    
    /**
     * This will return volume weighted stock price for the input stock symbol
     * @param Stock Symbol
     * @return 
     */
    public double getVWStockPrice(String stockSymbol, int pastMinutes);
    
    /**
     * This will return GBCE all share index using geometric mean of prices of all stocks
     * @return double
     */
    public double getGBCEAllShareIndex();
    
    /**
     * This method returns the HashMap of all stocks from the stock system
     * @return Map<String, Stock>
     */
    public Map<String, Stock> getStocks();
    
    /**
     * This method returns the ArrayList of all trades done so far.
     * @return ArrayList
     */
    public List<Trade> getTrades();
}
