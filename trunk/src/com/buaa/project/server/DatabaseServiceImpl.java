package com.buaa.project.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.buaa.project.client.DatabaseService;
import com.buaa.project.client.dd.BeanNewsDTO;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class DatabaseServiceImpl extends RemoteServiceServlet implements DatabaseService {
	
	String dbDriver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/test";
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	public boolean login(String username, String password) throws Exception {

		// public boolean login(Map formData)throws Exception{
		try{

		System.out.println(username);
		
		Class.forName(dbDriver);
		
		Connection conn = DriverManager.getConnection(url, "root", "wangrui");

		String sql = "select *  from user where id='" + username
				+ "'and psw = '" + password + "'";

		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		if (rs.next())
			return true;
		else
			return false;
		}catch(Exception ex){
			
			ex.printStackTrace();
			
			return false;
			
		}
 	}

	public String userType(String name) throws Exception {
		try {
			Class.forName(dbDriver);
			Connection conn = DriverManager.getConnection(url, "root", "wangrui");
			
			String sql = "select type from user where id = '"+ name +"'";
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			
			while(rs.next()){
				
				String type = rs.getString("type");
				return type;
			
				}
			
			rs.close();
			stmt.close();
			conn.close();
			}
			
			catch(Exception ex){
				
				ex.printStackTrace();
			}
			
			return null;
	}

	public boolean validate(String username) throws Exception {

		Class.forName(dbDriver);
		Connection conn = DriverManager.getConnection(url, "root", "wangrui");
		String sql = "select *  from user where id='" + username + "'";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		int i = 0;
		
			while (rs.next()) 

				i++;
				if (i > 0) 
					return true;
				 else 
					return false;

	
	}

	public String getNewsContent(String N_TITLE) throws Exception {
		try {
			Class.forName(dbDriver);
			Connection conn = DriverManager.getConnection(url, "root", "wangrui");
		      String cmd =
		          "select N_CONTENT from news where N_TITLE = '"+ N_TITLE +"'";
		     Statement stmt = conn.createStatement();
		     ResultSet rs = stmt.executeQuery(cmd);
		     
		     while(rs.next()){
		    	 
		    	
		    	String newstitle = rs.getString("N_CONTENT");
		    	System.out.println(newstitle); 
		    	return newstitle;
		    	
		     }
		    
		      
		    }
		catch (Exception ex) {
		      ex.printStackTrace();
		    }

		    return null;
		  }

	public List getdata(){
		try {
		Class.forName(dbDriver);
		Connection conn = DriverManager.getConnection(url, "root", "wangrui");
		String cmd  = "select * from news order by N_ID desc";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(cmd);
		
		
		List userInfo = new ArrayList(); 
		while(rs.next()){
			BeanNewsDTO bean  = new BeanNewsDTO();
			bean.setN_TITLE(rs.getString("N_TITLE"));
			bean.setN_AUTHOR(rs.getString("N_AUTHOR"));
			bean.setN_TIME(rs.getString("N_TIME"));
			bean.setN_ID(rs.getString("N_ID"));
			userInfo.add(bean);
			
		}
		rs.close();
		stmt.close();
		conn.close();
		
	
		return userInfo;
		

	}
		catch (Exception ex) {
		      ex.printStackTrace();
		    }
		return null;
	   }

}
