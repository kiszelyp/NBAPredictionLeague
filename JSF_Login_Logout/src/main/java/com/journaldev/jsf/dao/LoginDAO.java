package com.journaldev.jsf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.journaldev.jsf.util.DataConnect;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class LoginDAO {
        private static final AtomicInteger count = new AtomicInteger(0); 
//        public static int id;
        
	public static boolean validate(String user, String password) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("Select uname, password from Users where uname = ? and password = ?");
			ps.setString(1, user);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			}
		} catch (SQLException ex) {
			System.out.println("Login error -->" + ex.getMessage());
			return false;
		} finally {
			DataConnect.close(con);
		}
		return false;
	}
        
    public static void add(String user, String password) {
        Connection con = null;
        PreparedStatement ps = null;
//                id = count.incrementAndGet();
        UUID id = UUID.randomUUID();

            try {
                con = DataConnect.getConnection();
                ps = con.prepareStatement("INSERT INTO Users(uid,uname,password) VALUES(?,?,?)");
                ps.setString(1, id.toString());
                ps.setString(2, user);
                ps.setString(3, password);

                ps.executeUpdate();
                System.out.println("Data added successfully");

            } catch (SQLException ex) {
                System.out.println("Registering error -->" + ex.getMessage());
            } finally {
                DataConnect.close(con);
            }
        }
                
            public static boolean isUserExist(String user) {
		Connection con = null;
		PreparedStatement ps;

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("Select uname from Users where uname = ?");
			ps.setString(1, user);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			}
		} catch (SQLException ex) {
			System.out.println("User exist check error -->" + ex.getMessage());
			return false;
		} finally {
			DataConnect.close(con);
		}
		return false;
	}
                    
}