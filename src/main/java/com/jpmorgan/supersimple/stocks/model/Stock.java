/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jpmorgan.supersimple.stocks.model;

/**
 *
 * @author Vijay Veerasamy
 */
public class Stock {
    
    private String stockSymbol = null;
    
    private StockType stockType = StockType.COMMON;
    
    private double lastDividend = 0.00;
    
    private double fixedDividend = 0.00;
    
    private double parValue = 0.00;
    
    private double marketPrice = 0.00;

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public StockType getStockType() {
        return stockType;
    }

    public void setStockType(StockType stockType) {
        this.stockType = stockType;
    }

    public double getLastDividend() {
        return lastDividend;
    }

    public void setLastDividend(double lastDividend) {
        this.lastDividend = lastDividend;
    }

    public double getFixedDividend() {
        return fixedDividend;
    }

    public void setFixedDividend(double fixedDividend) {
        this.fixedDividend = fixedDividend;
    }

    public double getParValue() {
        return parValue;
    }

    public void setParValue(double parValue) {
        this.parValue = parValue;
    }

    public double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(double marketPrice) {
        this.marketPrice = marketPrice;
    }

    @Override
    public String toString() {
        return "Stock{" + "stockSymbol=" + stockSymbol + ", stockType=" + stockType + ", lastDividend=" + lastDividend + ", fixedDividend=" + fixedDividend + ", parValue=" + parValue + ", marketPrice=" + marketPrice + '}';
    }
    
}
