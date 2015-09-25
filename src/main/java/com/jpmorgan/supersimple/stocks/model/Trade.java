/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jpmorgan.supersimple.stocks.model;

import java.util.Date;

/**
 *
 * @author Vijay Veerasamy
 */
public class Trade {
    
    private Stock stock = null;
    
    private Date timeStamp = null;
    
    private long quantityShares = 0;
    
    private TradeIndicator tradeIndicator = TradeIndicator.BUY;
    
    private double tradePrice = 0.00;

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public long getQuantityShares() {
        return quantityShares;
    }

    public void setQuantityShares(long quantityShares) {
        this.quantityShares = quantityShares;
    }

    public TradeIndicator getTradeIndicator() {
        return tradeIndicator;
    }

    public void setTradeIndicator(TradeIndicator tradeIndicator) {
        this.tradeIndicator = tradeIndicator;
    }

    public double getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(double tradePrice) {
        this.tradePrice = tradePrice;
    }

    @Override
    public String toString() {
        return "Trade{" + "stock=" + stock.getStockSymbol() + ", timeStamp=" + timeStamp + ", quantityShares=" + quantityShares + ", tradeIndicator=" + tradeIndicator + ", tradePrice=" + tradePrice + '}';
    }
    
    
}
