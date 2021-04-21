package connection;

import java.sql.*;



public class ConnectionProvider {
	
	public static Connection getCon(){
	    
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con =DriverManager.getConnection("jdbc:mysql:///clinic","root","");
            return con;
        
        }catch(Exception e){
            
            return null;
        }    
    }

}
