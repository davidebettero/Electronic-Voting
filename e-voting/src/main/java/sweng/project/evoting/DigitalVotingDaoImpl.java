package sweng.project.evoting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DigitalVotingDaoImpl implements DigitalVotingDao {
	private Connection conn;
	
	public DigitalVotingDaoImpl(Connection conn) {
		this.conn=conn;
	}
	

	public boolean isValid(String user, String psw,String type) {
		 try{
		        conn.setAutoCommit(true);
		        Statement st = this.conn.createStatement();
		        st.execute("set search_path=digitalvoting");
		        //st.execute("insert into elettore (username,password) values ("+user+",'"+App.encoding(psw)+"');");
		        // Turn use of the cursor on.
		        st.setFetchSize(50);
		        String strQuery="SELECT * FROM $tableName as E WHERE E.username=? AND E.password=?";
		        String query =strQuery.replace("$tableName",type);
		        PreparedStatement stmt =conn.prepareStatement(query);
        		stmt.setString(1,user );
     		    stmt.setString(2, App.encoding(psw));
     		    ResultSet rs = stmt.executeQuery();
     		    if (rs.next()) return true;
		
		 }catch(SQLException e) {
		   System.out.print(e);
		 }  	
		 return false;
	}
}
