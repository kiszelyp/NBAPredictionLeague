/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.journaldev.jsf.dao;

import com.journaldev.jsf.util.DataConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kiszelyi
 */
public class ListDAO {

    public static ArrayList<String> getAllUsers() {
        Connection con = null;
        PreparedStatement ps = null;
        ArrayList<String> userList = new ArrayList<String>();

        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select * from Users");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                userList.add(rs.getString("uname"));
            }
        } catch (SQLException ex) {
            System.out.println("Listing error -->" + ex.getMessage());
        } finally {
            DataConnect.close(con);
        }

        return userList;
    }
}
