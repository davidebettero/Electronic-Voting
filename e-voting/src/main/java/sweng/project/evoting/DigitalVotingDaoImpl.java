package sweng.project.evoting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class DigitalVotingDaoImpl implements DigitalVotingDao {
	private Connection conn = null;
	
	// restituisce un oggetto che rappresenta la connessione al database
	private static Connection getConnection() {
		return new App().connect();
	}
	
	/*
	 * Metodo che controlla se un utente identificato dai parametri passati come argomento � presente nel database
	 * restituendo true; false altrimenti.
	 * Come richiesto vengono utilizzati i prepared statement per fare in modo che non siano possibili 
	 * attacchi di tipo SQL injection nell'esecuzione della query SQL.
	 */
	public boolean isValid(String user, String psw,String type) {
		 try {
			 // creazione della connessione
			 conn = getConnection();
			 
			 conn.setAutoCommit(true);
			 Statement st = conn.createStatement();
			 st.execute("set search_path = digitalvoting");
		     //st.execute("insert into elettore values ("+user+",'"+App.encoding(psw)+");");
			 st.setFetchSize(50);
			 String strQuery = "SELECT * FROM $tableName as E WHERE E.username=? AND E.password=?";
			 String query = strQuery.replace("$tableName",type);
			 PreparedStatement stmt = conn.prepareStatement(query);
			 stmt.setString(1,user);
			 stmt.setString(2, psw);
			 ResultSet rs = stmt.executeQuery();
			 
			// chiusura connessione
		    try {
		    	conn.close();
		    }catch(Exception e) {
		    	e.printStackTrace();
		    }
			 
			 // se la query restituisce un risultato la funzione restituisce true, false altrimenti. 
			 if (rs.next()) return true;
			 else return false;
		 } catch(SQLException e) {
			 e.printStackTrace();
		 }  	
		 return false;
	}
	
	// restituisce una lista contenente tutti gli username di tutti gli elettori presenti nel database
	public List<String> getAllElettori(){
		try {
			conn = getConnection();
			conn.setAutoCommit(true);
			Statement st = conn.createStatement();
			st.execute("set search_path = digitalvoting");
			st.setFetchSize(50);
			List<String> elettori = new ArrayList<>();
			String query = "SELECT E.username FROM elettore AS E";
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet results = stmt.executeQuery();
			ResultSetMetaData rsmd = results.getMetaData(); 
			int columnCount = rsmd.getColumnCount();
			while (results.next()) {              
				int i = 1;
				while(i <= columnCount) {
				   elettori.add(results.getString(i++));
				}
			}
			
			// chiusura connessione
		    try {
		    	conn.close();
		    }catch(Exception e) {
		    	e.printStackTrace();
		    }
		    
			return elettori;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// restituisce una lista contenente tutti gli username di tutti gli amministratori presenti nel database
	public List<String> getAllAmministratori(){
		try {
			conn = getConnection();
			conn.setAutoCommit(true);
			Statement st = conn.createStatement();
			st.execute("set search_path = digitalvoting");
			st.setFetchSize(50);
			List<String> amministratori = new ArrayList<>();
			String query = "SELECT A.username FROM amministratore AS A";
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet results = stmt.executeQuery();
			ResultSetMetaData rsmd = results.getMetaData(); 
			int columnCount = rsmd.getColumnCount();
			while (results.next()) {              
				int i = 1;
				while(i <= columnCount) {
				   amministratori.add(results.getString(i++));
				}
			}
			
			// chiusura connessione
		    try {
		    	conn.close();
		    }catch(Exception e) {
		    	e.printStackTrace();
		    }
		    
			return amministratori;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// restituisce una lista di tutti gli utenti (elettori e amministratori) presenti nel database
	public List<Utente> getAllUtenti(){
		try {
			conn = getConnection();
			conn.setAutoCommit(true);
			Statement st = conn.createStatement();
			st.execute("set search_path = digitalvoting");
			st.setFetchSize(50);
			
			List<Utente> utenti = new ArrayList<>();
			// aggiungo gli elettori alla lista
			String query = "SELECT E.username, E.password FROM elettore AS E";
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet results = stmt.executeQuery();
			ResultSetMetaData rsmd = results.getMetaData(); 
			int columnCount = rsmd.getColumnCount(); 
			while (results.next()) {              
				int i = 1;
				while(i <= columnCount) {
				   utenti.add(new Utente(results.getString(i++), results.getString(i++), "Elettore"));
				}
			}
			
			// aggiungo gli amministratori alla lista
			query = "SELECT A.username, A.password FROM amministratore AS A";
			stmt = conn.prepareStatement(query);
			results = stmt.executeQuery();
			rsmd = results.getMetaData();
			columnCount = rsmd.getColumnCount();
			while (results.next()) {              
				int i = 1;
				while(i <= columnCount) {
				   utenti.add(new Utente(results.getString(i++), results.getString(i++), "Amministratore"));
				}
			}
			
			// chiusura connessione
		    try {
		    	conn.close();
		    }catch(Exception e) {
		    	e.printStackTrace();
		    }
			
			return utenti;

		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	
	
	
	
	//inserisce un voto all'interno del db nella tabella categorico
	public void addVotoCategorico(String id,String idVoto,String [] scelte) {
		try{
			conn = getConnection();
            conn.setAutoCommit(true);
            Statement st = conn.createStatement();
            st.execute("set search_path=digitalvoting");
            st.execute("insert into categorico (id,id_votazione,partito,candidato_principale,candidato1,candidato2) values ("+id+","+idVoto+",'"+scelte[0]+"','"+scelte[1]+"','"+scelte[2]+"','"+scelte[3]+"');");
            // Turn use of the cursor on.
            st.setFetchSize(50);
            st.close();
    
        }catch(SQLException e) {
             e.printStackTrace();
        }  	
	}
	
	//inserisce un voto all interno del db nella tabella referendum
	public void addVotoReferendum(String id,String idVoto,Boolean scelte) {
         try{
        	conn = getConnection();
            conn.setAutoCommit(true);
            Statement st = conn.createStatement();
            st.execute("set search_path=digitalvoting");
            st.execute("insert into referendum (id,id_votazione,scelta) values ("+id+","+idVoto+","+scelte+");");
            // Turn use of the cursor on.
            st.setFetchSize(50);
            st.close();
         }catch(SQLException e) {
            e.printStackTrace();
       }
	
    }
	
	//inserisce una votazione all interno del db 
	public void insertReferendumVotingSession(String id, Timestamp inizio, Timestamp fine, String tipo, String testo) {
		String query = "INSERT INTO referendum (id,inizio,fine,tipo,testo) VALUES (?,?,?,?,?)"; //query da eseguire
		
		Connection conn = null; 
		PreparedStatement ps = null;
		try {
			conn = getConnection(); //apro connessione
			conn.setAutoCommit(true);
			Statement st = conn.createStatement();
			st.execute("set search_path=digitalvoting"); //set search_path
			
			ps = conn.prepareStatement(query);	//setto il prepareStatement

			//inserisco i valori nella query
			ps.setString(1, id); 
			ps.setTimestamp(2,inizio); 
			ps.setTimestamp(3,fine);
			ps.setString(4, tipo); // viene inserita una roba che non è corretta (vedi screen su discord) -andrea
			ps.setString(5, testo); //viene inserita una roba che non è corretta (vedi screen su discord) -andrea
			ps.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
