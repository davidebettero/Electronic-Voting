package sweng.project.evoting;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.jupiter.api.Test;

public class UtenteTest {
	@Test
	public void testLogInTrue() {
		Utente u = new Utente("Davide", "db", "Elettore");
		boolean result = false;
		
		try {
			 // creazione della connessione
			Connection conn = new App().connect();
			 
			 conn.setAutoCommit(true);
			 Statement st = conn.createStatement();
			 st.execute("set search_path = digitalvoting");
			 st.setFetchSize(50);
			 String query = "SELECT * FROM Elettore as E WHERE E.username='Davide' AND E.password='" + App.encoding("db") + "';";
			 PreparedStatement stmt = conn.prepareStatement(query);
			 ResultSet rs = stmt.executeQuery();
			 
			// chiusura connessione
		    try {
		    	conn.close();
		    }catch(Exception e) {
		    	e.printStackTrace();
		    }
		 // se la query restituisce un risultato la funzione restituisce true, false altrimenti. 
			 if (rs.next()) result = true;
		 } catch(SQLException e) {
			 e.printStackTrace();
		 } 
		
		assertEquals(u.login(), result);
	}
	
	@Test
	public void testLogInFalse() {
		Utente u = new Utente("Davide", "db", "Elettore");
		boolean result = false;
		
		try {
			 // creazione della connessione
			Connection conn = new App().connect();
			 
			 conn.setAutoCommit(true);
			 Statement st = conn.createStatement();
			 st.execute("set search_path = digitalvoting");
			 st.setFetchSize(50);
			 String query = "SELECT * FROM Elettore as E WHERE E.username='Davide' AND E.password='db';";
			 PreparedStatement stmt = conn.prepareStatement(query);
			 ResultSet rs = stmt.executeQuery();
			 
			// chiusura connessione
		    try {
		    	conn.close();
		    }catch(Exception e) {
		    	e.printStackTrace();
		    }
		 // se la query restituisce un risultato la funzione restituisce true, false altrimenti. 
			 if (rs.next()) result = true;
		 } catch(SQLException e) {
			 e.printStackTrace();
		 } 
		
		assertNotEquals(u.login(), result);
	}
	
	@Test
	public void testUsernameNotNull() {
		Utente u = new Utente("Davide", "db", "Elettore");
		String result = u.getUsername();
		assertNotNull(result);
	}
	
	@Test
	public void testPasswordNotNull() {
		Utente u = new Utente("Davide", "db", "Elettore");
		String result = u.getPassword();
		assertNotNull(result);
	}
}
