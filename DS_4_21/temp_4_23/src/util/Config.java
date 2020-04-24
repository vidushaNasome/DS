/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.*;
/**
 *
 * @author Oshani
 */
public class Config implements DetailsImplemntation{
    private static Connection con = null;
    
    
    private Config() {
	}
    
    public static Connection getConnect() {
		/**
		 *  This create new connection objects when connection is closed or it is
		 */
		try {
                        
			if (con == null || con.isClosed()) {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(connurl, username, pwd);
                                
			}
                        

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}
}
