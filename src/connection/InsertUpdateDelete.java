package connection;

import java.sql.*;

public class InsertUpdateDelete {

	public static void setData(String Query, String msg){
	    
        Connection con=null;
        Statement st=null;
        try{
            con=ConnectionProvider.getCon();
            st=con.createStatement();
            st.executeUpdate(Query);
            
            if(!msg.equals("")){
                
            	System.out.println(msg);
            }
            
        }catch(Exception e){
        	
        	System.out.println(e);
        }
        finally{
        
            try{
            }catch(Exception e){
            }
        }
    }
}
