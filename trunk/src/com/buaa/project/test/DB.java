package com.buaa.project.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

	
	static String dbDriver = "com.mysql.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/test";
	static Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	
	
	
	public static void main(String[] args) throws Exception {
		
		
		// TODO Auto-generated method stub
		try{
			
			
			Class.forName(dbDriver);
			
			Connection conn = DriverManager.getConnection(url, "root", "wangrui");

			 String sql = "select * from news ";
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				
				String newstitle = rs.getString("N_CONTENT");
				System.out.println(newstitle); 
		  
				
			}
			
		}catch(SQLException ex){
			
			ex.printStackTrace();
		}

	}

}
