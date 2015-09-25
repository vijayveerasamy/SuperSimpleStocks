/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jpmorgan.supersimple.stocks;

import com.jpmorgan.supersimple.stocks.model.Stock;
import com.jpmorgan.supersimple.stocks.model.StockType;
import com.jpmorgan.supersimple.stocks.model.Trade;
import com.jpmorgan.supersimple.stocks.model.TradeIndicator;
import com.jpmorgan.supersimple.stocks.service.SimpleStockService;
import com.jpmorgan.supersimple.stocks.util.SimpleUtil;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.Test;
import org.junit.Assert;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author Vijay Veerasamy
 */
public class SimpleStockServiceTest {
    
    @Test
    public void doTest() {
        SimpleStockService ssService1 = SimpleStockService.INSTANCE; 
        SimpleStockService ssService2 = SimpleStockService.INSTANCE; 
        Stock stock = null;
        
        Assert.assertSame(ssService1, ssService2);
        
        stock = new Stock();
        stock.setStockSymbol("TEA");
        stock.setStockType(StockType.COMMON);
        stock.setLastDividend(0);
        stock.setParValue(100);
        
        Assert.assertTrue(ssService1.launchStock(stock));        
        Assert.assertSame(1, ssService1.getStocks().size());
        
        stock = new Stock();
        stock.setStockSymbol("POP");
        stock.setStockType(StockType.COMMON);
        stock.setLastDividend(8);
        stock.setParValue(100);
        
        Assert.assertTrue(ssService2.launchStock(stock));        
        Assert.assertSame(2, ssService2.getStocks().size());
        
        stock = new Stock();
        stock.setStockSymbol("ALE");
        stock.setStockType(StockType.COMMON);
        stock.setLastDividend(23);
        stock.setParValue(60);
        
        Assert.assertTrue(ssService1.launchStock(stock));        
        Assert.assertSame(3, ssService2.getStocks().size());
        
        stock = new Stock();
        stock.setStockSymbol("GIN");
        stock.setStockType(StockType.PREFERRED);
        stock.setLastDividend(8);
        stock.setFixedDividend(2);
        stock.setParValue(100);
        
        Assert.assertTrue(ssService1.launchStock(stock));        
        Assert.assertSame(4, ssService2.getStocks().size());
        
        stock = new Stock();
        stock.setStockSymbol("JOE");
        stock.setStockType(StockType.COMMON);
        stock.setLastDividend(13);
        stock.setParValue(250);
        
        Assert.assertTrue(ssService1.launchStock(stock));        
        Assert.assertSame(5, ssService2.getStocks().size());
           
        Trade trade = null;        
        for (int i=0; i<=20; i++) {        
            trade = new Trade();
            stock = ssService1.getStockBySymbol("POP");              
            trade.setStock(stock);
            trade.setQuantityShares(SimpleUtil.getRandomNumber(21, 51));
            trade.setTradePrice(SimpleUtil.getRandomNumber(20, 30));
            trade.setTradeIndicator(TradeIndicator.SELL);
            trade.setTimeStamp(SimpleUtil.getRandomMinutesPastNow(1,20));
            ssService1.recordTrade(trade);
        }
        
        for (int i=0; i<=20; i++) {        
            trade = new Trade();
            stock = ssService1.getStockBySymbol("JOE");              
            trade.setStock(stock);
            trade.setQuantityShares(SimpleUtil.getRandomNumber(21, 51));
            trade.setTradePrice(SimpleUtil.getRandomNumber(20, 30));
            trade.setTradeIndicator(TradeIndicator.BUY);
            trade.setTimeStamp(SimpleUtil.getRandomMinutesPastNow(1,20));
            ssService1.recordTrade(trade);
        }

        
        List<Trade> trades = ssService2.getTrades();
        
        Logger.getLogger(SimpleStockServiceTest.class.getName()).log(Level.INFO, "JOE total trade: {0}", trades.size());
        Logger.getLogger(SimpleStockServiceTest.class.getName()).log(Level.INFO, "JOE dividend yield: {0}", String.format("%.2f", ssService2.getDividendYield("JOE")));
        Logger.getLogger(SimpleStockServiceTest.class.getName()).log(Level.INFO, "JOE P/E ratio: {0}", String.format("%.2f", ssService2.getPERatio("JOE")));
        Logger.getLogger(SimpleStockServiceTest.class.getName()).log(Level.INFO, "JOE volume weighted stock price: {0}", String.format("%.2f", ssService2.getVWStockPrice("JOE", 15)));
        Logger.getLogger(SimpleStockServiceTest.class.getName()).log(Level.INFO, "JOE GBCE All Shares Index: {0}", String.format("%.2f", ssService2.getGBCEAllShareIndex()));

    }
    
}
