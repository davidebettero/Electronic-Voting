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
	
	//inserisce una votazione ti tipo 'referendum' all'interno del db 
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
			ps.setString(4, tipo);
			ps.setString(5, testo);
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
	
	public void insertOrdinaleVotingSession(String id, Timestamp inizio, Timestamp fine) {
		String query = "INSERT INTO ordinale (id,inizio,fine) VALUES (?,?,?)"; //query da eseguire
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
	
	public void insertCandidatoOrdinale(final String id, final Candidato c) {
		Connection conn = getConnection(); //apro connessione 
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(true);
			Statement st = conn.createStatement();
			st.execute("set search_path=digitalvoting"); //set search_path
			
			String query = "INSERT INTO candidatiOrdinale VALUES(?,?,?)"; //query da eseguire
			ps = conn.prepareStatement(query);	//setto il prepareStatement
			ps.setString(1, id); 
			ps.setString(2,c.getNome()); 
			ps.setString(3,c.getCognome());
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

	public void insertCategoricaVotingSession(String id, Timestamp inizio, Timestamp fine, boolean conPreferenze, String modCalcoloVincitore) {
		String query = "INSERT INTO categorico (id,inizio,fine,conpreferenze,modcalcolovincitore) VALUES (?,?,?,?,?)"; //query da eseguire
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
			ps.setBoolean(4, conPreferenze);
			ps.setString(5, modCalcoloVincitore);
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

	public void insertCandidatoCategorico(String id, Candidato c, String cP1, String cP2, String cP3, String cP4) {
		String query = "INSERT INTO candidaticategorico (id,nome,cognome,partito,candidatodipartito1,candidatodipartito2,candidatodipartito3,candidatodipartito4) VALUES (?,?,?,?,?,?,?,?)"; //query da eseguire
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
			ps.setString(2,c.getNome()); 
			ps.setString(3,c.getCognome());
			ps.setString(4,c.getPartito());
			ps.setString(5, cP1);
			ps.setString(6, cP2);
			ps.setString(7, cP3);
			ps.setString(8, cP4);
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

	public void deleteVotazioneCategorica(final String id) {
		String query1 = "DELETE FROM candidaticategorico WHERE id = ?";	//query 1 da eseguire
		String query2 = "DELETE FROM categorico WHERE id = ?";	//query 2 da eseguire
		Connection conn = null; 
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		try {
			conn = getConnection(); //apro connessione
			conn.setAutoCommit(true);
			Statement st = conn.createStatement();
			st.execute("set search_path=digitalvoting"); //set search_path
			
			ps1 = conn.prepareStatement(query1);	//setto il prepareStatement
			//inserisco i valori nella query 1
			ps1.setString(1, id); 
			ps1.executeUpdate();
			
			ps2 = conn.prepareStatement(query2);	//setto il prepareStatement
			//inserisco i valori nella query 2
			ps2.setString(1, id); 
			ps2.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				ps1.close();
				ps2.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteVotazioneOrdinale(final String id) {
		String query1 = "DELETE FROM candidatiordinale WHERE id = ?";	//query 1 da eseguire
		String query2 = "DELETE FROM ordinale WHERE id = ?";	//query 2 da eseguire
		Connection conn = null; 
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		try {
			conn = getConnection(); //apro connessione
			conn.setAutoCommit(true);
			Statement st = conn.createStatement();
			st.execute("set search_path=digitalvoting"); //set search_path
			
			ps1 = conn.prepareStatement(query1);	//setto il prepareStatement
			//inserisco i valori nella query 1
			ps1.setString(1, id); 
			ps1.executeUpdate();
			
			ps2 = conn.prepareStatement(query2);	//setto il prepareStatement
			//inserisco i valori nella query 2
			ps2.setString(1, id); 
			ps2.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				ps1.close();
				ps2.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void eliminaCandidatoOrdinale(final String id, final Candidato c) {
		String query = "DELETE FROM candidaticategorico WHERE id = ? AND nome = ? AND cognome = ?"; //query da eseguire
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
			ps.setString(2,c.getNome()); 
			ps.setString(3,c.getCognome());
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
	
	public List<Candidato> candidatiOrdinale(final String id) {
		String query = "SELECT nome, cognome FROM candidatiordinale WHERE id = ?";
		Connection conn = null; 
		PreparedStatement ps = null;
		List<Candidato> lst = new ArrayList<>();
		try {
			conn = getConnection(); //apro connessione
			conn.setAutoCommit(true);
			Statement st = conn.createStatement();
			st.execute("set search_path=digitalvoting"); //set search_path
			
			ps = conn.prepareStatement(query);	//setto il prepareStatement

			//inserisco i valori nella query
			ps.setString(1, id);
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				lst.add(new Candidato(res.getString("nome"), res.getString("cognome"), null));
			}
			
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
		return lst;
	}
	
	// restituisce un array che contiene in ordine data d'inizio, ora d'inizio, data di fine, ora di fine della votazione ordinale con questo id
	public String[] getInfoVotazioneOrdinale(final String id) {
		String query = "SELECT inizio, fine FROM ordinale WHERE id = ?";
		Connection conn = null; 
		PreparedStatement ps = null;
		String[] result = new String[4];
		try {
			conn = getConnection(); //apro connessione
			conn.setAutoCommit(true);
			Statement st = conn.createStatement();
			st.execute("set search_path=digitalvoting"); //set search_path
			
			ps = conn.prepareStatement(query);	//setto il prepareStatement

			//inserisco i valori nella query
			ps.setString(1, id);
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				String[] i = res.getString("inizio").split(" ");
				String[] dI = i[0].split("-");
				result[0] = dI[2] + "-" + dI[1] + "-" + dI[0];
				result[1] = i[1].substring(0, 5);
				
				String[] f = res.getString("fine").split(" ");
				String[] dF = f[0].split("-");
				result[2] = dF[2] + "-" + dF[1] + "-" + dF[0];
				result[3] = f[1].substring(0, 5);
			}
			
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
		return result;
	}
	
}
