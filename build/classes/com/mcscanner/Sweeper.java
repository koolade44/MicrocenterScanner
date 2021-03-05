/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcscanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Student
 */
public class Sweeper implements Runnable {
    public static String pageContent;
    
    @Override
    public void run(){
        try {
            URL url = new URL("https://www.microcenter.com/category/4294966937/video-cards");
            MainUI.updateConsole("Sweeper started");
            
            while (true) {
                if (MainUI.getRun()) {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                        String text;
                        StringBuilder sb = new StringBuilder();
                    
                        while ((text = reader.readLine()) != null) {
                            sb.append(text);
                            sb.append(System.lineSeparator());
                        }
                        String cpc = sb.toString();
                        if (pageContent == null) pageContent = cpc;
                        if (!pageContent.equals(cpc)) MainUI.updateConsole("something has changed");
                        pageContent = cpc;
                    } catch (IOException ex) {
                        Logger.getLogger(Sweeper.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else break;
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(Sweeper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
