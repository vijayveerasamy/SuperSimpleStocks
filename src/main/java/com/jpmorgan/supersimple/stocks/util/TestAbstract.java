/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jpmorgan.supersimple.stocks.util;

/**
 *
 * @author Vijay Veerasamy
 */
public abstract class TestAbstract implements TestInterface {
    public abstract boolean callTest();
    
    public boolean runTest() {
        return false;
    }
}
