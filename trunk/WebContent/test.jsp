
<%@ page import="java.sql.*"%> 

<%@ page import="javax.sql.*"%> 

<%@ page import="javax.naming.*"%> 

<%@ page session="false" %> 





<% 

out.print("my test"); 

DataSource ds = null; 

try{ 

InitialContext ctx=new InitialContext(); 

ds=(DataSource)ctx.lookup("java:comp/env/jdbc/mysql"); 

Connection conn = ds.getConnection(); 

Statement stmt = conn.createStatement(); 


String strSql = " select * from user"; 

ResultSet rs = stmt.executeQuery(strSql); 

while(rs.next()){ 
	


out.println(rs.getString("id")); 
out.println(rs.getString("psw"));

} 

out.print("test close"); 

} 

catch(Exception ex){ 

out.print("message:"+ex.getMessage()); 

ex.printStackTrace(); 

} 

%> 