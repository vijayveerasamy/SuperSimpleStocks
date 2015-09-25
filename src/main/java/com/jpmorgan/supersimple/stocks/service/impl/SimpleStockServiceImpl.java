/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jpmorgan.supersimple.stocks.service.impl;

import com.jpmorgan.supersimple.stocks.Exception.InvalidOperationException;
import com.jpmorgan.supersimple.stocks.Exception.NotFoundException;
import com.jpmorgan.supersimple.stocks.entity.SimpleStockEntityManager;
import com.jpmorgan.supersimple.stocks.model.Stock;
import com.jpmorgan.supersimple.stocks.model.StockType;
import com.jpmorgan.supersimple.stocks.model.Trade;
import com.jpmorgan.supersimple.stocks.service.SimpleStockService;
import com.jpmorgan.supersimple.stocks.util.SimplePredicate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.math3.stat.StatUtils;

/**
 *
 * @author Vijay Veerasamy
 */
public class SimpleStockServiceImpl implements SimpleStockService {
    
    private static SimpleStockService INSTANCE = new SimpleStockServiceImpl();
    
    private static SimpleStockEntityManager ssEnityManager = SimpleStockEntityManager.INSTANCE;
    
    private SimpleStockServiceImpl() {
        //private constructor
    }
    
    public static SimpleStockService getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean launchStock(Stock stock) {     
        try {            
            return ssEnityManager.launchStock(stock);
        } catch (InvalidOperationException ex) {
            Logger.getLogger(SimpleStockServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean recordTrade(Trade trade) {
        try {
            return ssEnityManager.recordTrade(trade);
        } catch (InvalidOperationException ex) {
            Logger.getLogger(SimpleStockServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public double getDividendYield(String stockSymbol) {
        double result = 0.00;
        Stock stock = getStockBySymbol(stockSymbol);
        if (stock!=null && stock.getMarketPrice()>0) {
            if (stock.getStockType().equals(StockType.PREFERRED))
                result=stock.getFixedDividend()*stock.getParValue()/stock.getMarketPrice();
            else
                result = stock.getLastDividend()/stock.getMarketPrice();
        }
       return result;
    }

    @Override
    public double getPERatio(String stockSymbol) {
        double result = 0.00;
        Stock stock = getStockBySymbol(stockSymbol);
        double dividendYield = getDividendYield(stockSymbol);
        
        if (dividendYield>0 && stock!=null) {
            result = stock.getMarketPrice()/dividendYield;
        }
        
        return result;
    }

    @Override
    public double getVWStockPrice(String stockSymbol, int pastMinutes) {
        double vwStockPrice = 0.0;

	
        Collection<Trade> trades = CollectionUtils.select(ssEnityManager.getTrades(), new SimplePredicate(stockSymbol, pastMinutes));

        Logger.getLogger(SimpleStockServiceImpl.class.getName()).log(Level.INFO, "Trades are filtered by [{0},{1}]: {2}", new Object[]{stockSymbol, pastMinutes, trades.size()});
	
	int totalTotalShares = 0;
	double totalTradedPrice = 0.0;
	for(Trade trade : trades){
            // Calculate the sum of Trade Price x Quantity
            totalTradedPrice += (trade.getTradePrice() * trade.getQuantityShares());
            // Totalling Quantity
            totalTotalShares += trade.getQuantityShares();
	}

	// Calculate the volume weighted stock price
	if(totalTotalShares > 0){
            vwStockPrice = totalTradedPrice / totalTotalShares;	
	}


	return vwStockPrice;
    }

    @Override
    public double getGBCEAllShareIndex() {
        double gbceAllShareIndex = 0.0;

        // Collect stock price for all stocks
        Map<String, Stock> stocks = ssEnityManager.getStocks();
        List<Double> stockPrices = new ArrayList();
        for(String stockSymbol: stocks.keySet() ){
            double stockPrice = getVWStockPrice(stockSymbol, 0);
            if(stockPrice > 0){
                stockPrices.add(stockPrice);
            }
        }

        if(stockPrices.size()>=1){
            double[] stockPricesArray = new double[stockPrices.size()];

            for(int i=0; i<=(stockPrices.size()-1); i++){
                stockPricesArray[i] = stockPrices.get(i);
            }
            // Get GBCE All Share Index
            gbceAllShareIndex = StatUtils.geometricMean(stockPricesArray);
        }
        return gbceAllShareIndex;
    }

    @Override
    public Map<String, Stock> getStocks() {
        return ssEnityManager.getStocks();
    }

    @Override
    public List<Trade> getTrades() {
        return ssEnityManager.getTrades();
    }

    @Override
    public Stock getStockBySymbol(String stockSymbol) {

        try {
            return ssEnityManager.getStockBySymbol(stockSymbol);
        } catch (NotFoundException ex) {
            Logger.getLogger(SimpleStockServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
    
}
