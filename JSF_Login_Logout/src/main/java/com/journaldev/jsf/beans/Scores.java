/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.journaldev.jsf.beans;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

/**
 *
 * @author kiszelyi
 */
@ManagedBean
@SessionScoped
public class Scores implements Serializable {

    public Scores(){
    }

    public String getScores() throws IOException, SAXException, ParserConfigurationException, TransformerConfigurationException, TransformerException {
       StringBuilder ret = new StringBuilder();
        try {
            URL url = new URL ("https://api.mysportsfeeds.com/v1.1/pull/nba/2017-2018-regular/scoreboard.xml?fordate=20171026");
            String userpassword = "kiszelyp" + ":" + "gr99yz19";
            String encoding = Base64.encode(userpassword.getBytes("UTF-8"));

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setRequestProperty  ("Authorization", "Basic " + encoding);
            String response = connection.getResponseMessage();
            System.out.println(response);
            InputStream content = (InputStream)connection.getInputStream();
            BufferedReader in   = 
                new BufferedReader (new InputStreamReader (content));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                ret.append(line);
            }
          return line;
        } catch(Exception e) {
            e.printStackTrace();
        }
          return ret.toString();
}
    
        public String getTest() throws IOException, SAXException, ParserConfigurationException, TransformerConfigurationException, TransformerException {
       StringBuilder ret = new StringBuilder();
        try {
            URL url = new URL ("https://api.mysportsfeeds.com/v1.1/pull/nba/2017-2018-regular/full_game_schedule.xml");
            String userpassword = "kiszely" + ":" + "gr99yz19";
            String encoding = Base64.encode(userpassword.getBytes());

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setRequestProperty  ("Authorization", "Basic " + encoding);
            InputStream content = (InputStream)connection.getInputStream();
            BufferedReader in   = 
                new BufferedReader (new InputStreamReader (content));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                ret.append(line);
            }
          return line;
        } catch(Exception e) {
            e.printStackTrace();
        }
          return ret.toString();
}
}
