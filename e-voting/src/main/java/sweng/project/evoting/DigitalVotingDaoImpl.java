package sweng.project.evoting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import sweng.project.evoting.votazione.Votazione;
import sweng.project.evoting.votazione.VotazioneCategorica;
import sweng.project.evoting.votazione.VotazioneOrdinale;
import sweng.project.evoting.votazione.VotazioneReferendum;
import sweng.project.evoting.votazione.VotoCategorico;
import sweng.project.evoting.votazione.VotoOrdinale;

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
	public boolean isValid(String user, String psw, String type) {
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
	
	// restituisce le info personali dell'elettore identificato da username e password passate al metodo
	public String[] getVoterInfo(final String username, final String password) {
		String query = "SELECT name, surname, gender, birthdate, birthplace, countryofbirth, taxcode, city15k FROM elettore WHERE username = ? AND password = ?;";
		
		Connection conn = null; 
		PreparedStatement ps = null;
		String[] result = new String[8];
		try {
			conn = getConnection(); //apro connessione
			conn.setAutoCommit(true);
			Statement st = conn.createStatement();
			st.execute("set search_path=digitalvoting"); //set search_path
			
			ps = conn.prepareStatement(query);	//setto il prepareStatement

			//inserisco i valori nella query
			ps.setString(1, username);
			ps.setString(2, App.encoding(password));
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				result[0] = res.getString("name");
				result[1] = res.getString("surname");
				result[2] = res.getString("gender");
				result[3] = String.valueOf(res.getDate("birthdate"));
				result[4] = res.getString("birthplace");
				result[5] = res.getString("countryofbirth");
				result[6] = res.getString("taxcode");
				result[7] = String.valueOf(res.getBoolean("city15k"));
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
	
	public String[] getAdministratorInfo(final String username, final String password) {
		String query = "SELECT nome, cognome, taxcode FROM amministratore WHERE username = ? AND password = ?;";
		
		Connection conn = null; 
		PreparedStatement ps = null;
		String[] result = new String[3];
		try {
			conn = getConnection(); //apro connessione
			conn.setAutoCommit(true);
			Statement st = conn.createStatement();
			st.execute("set search_path=digitalvoting"); //set search_path
			
			ps = conn.prepareStatement(query);	//setto il prepareStatement

			//inserisco i valori nella query
			ps.setString(1, username);
			ps.setString(2, App.encoding(password));
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				result[0] = res.getString("nome");
				result[1] = res.getString("cognome");
				result[2] = res.getString("taxcode");
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
	
	public boolean hasAlreadyVoted(final String idVotazione, final String taxCode, final String username) {
		String query = "SELECT * FROM votanti WHERE idvotazione = ? AND codicefiscale = ? AND username = ?;";
		
		Connection conn = null; 
		PreparedStatement ps = null;
		try {
			conn = getConnection(); //apro connessione
			conn.setAutoCommit(true);
			Statement st = conn.createStatement();
			st.execute("set search_path=digitalvoting"); //set search_path
			
			ps = conn.prepareStatement(query);	//setto il prepareStatement

			//inserisco i valori nella query
			ps.setString(1, idVotazione);
			ps.setString(2, taxCode);
			ps.setString(3, username);
			ResultSet res = ps.executeQuery();
			return res.isBeforeFirst();
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
		return false;
	}
	
	public void insertVotante(final String idVotazione, final String taxCode, final String username) {
		String query = "INSERT INTO votanti VALUES(?,?,?);";
		
		Connection conn = null; 
		PreparedStatement ps = null;
		try {
			conn = getConnection(); //apro connessione
			conn.setAutoCommit(true);
			Statement st = conn.createStatement();
			st.execute("set search_path=digitalvoting"); //set search_path
			
			ps = conn.prepareStatement(query);	//setto il prepareStatement

			//inserisco i valori nella query
			ps.setString(1, idVotazione);
			ps.setString(2, taxCode);
			ps.setString(3, username);
			ps.execute();
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
	
	public void insertVotoReferendum(final String idVotazione, final String scelta){
		if(!scelta.equalsIgnoreCase("si") && !scelta.equalsIgnoreCase("no") && !scelta.equalsIgnoreCase("scheda bianca"))
			throw new IllegalArgumentException("Scelta non valida per una votazione di tipo referendum");
		
		String query = "INSERT INTO votireferendum VALUES(?,?);";
		
		Connection conn = null; 
		PreparedStatement ps = null;
		try {
			conn = getConnection(); //apro connessione
			conn.setAutoCommit(true);
			Statement st = conn.createStatement();
			st.execute("set search_path=digitalvoting"); //set search_path
			
			ps = conn.prepareStatement(query);	//setto il prepareStatement

			//inserisco i valori nella query
			ps.setString(1, idVotazione);
			ps.setString(2, scelta);
			ps.execute();
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
	
	public void insertVotoCategorico(final String idVotazione) {
		String query = "INSERT INTO voticategorico (idVotazione,scelta) VALUES (?,'SCHEDA BIANCA')"; //query da eseguire
		
		Connection conn = null; 
		PreparedStatement ps = null;
		try {
			conn = getConnection(); //apro connessione
			conn.setAutoCommit(true);
			Statement st = conn.createStatement();
			st.execute("set search_path=digitalvoting"); //set search_path
			
			ps = conn.prepareStatement(query);	//setto il prepareStatement

			//inserisco i valori nella query
			ps.setString(1, idVotazione); 
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
	
	public void insertVotoCategorico(final String idVotazione, final VotoCategorico v) {
		String[] preferenza = Objects.requireNonNull(v).getPreferenza();
		String scelta = Arrays.toString(preferenza).replace("[", "").replace("]", "");
		
		String query = "INSERT INTO voticategorico (idVotazione,scelta) VALUES (?,?)"; //query da eseguire
		Connection conn = null; 
		PreparedStatement ps = null;
		try {
			conn = getConnection(); //apro connessione
			conn.setAutoCommit(true);
			Statement st = conn.createStatement();
			st.execute("set search_path=digitalvoting"); //set search_path
			
			ps = conn.prepareStatement(query);	//setto il prepareStatement

			//inserisco i valori nella query
			ps.setString(1, idVotazione);
			ps.setString(2, scelta);
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
	
	public void insertVotoOrdinale(final String idVotazione, final VotoOrdinale v) {
		List<Candidato> preferenze = Objects.requireNonNull(v).getOrdinePreferenze();
		String scelta = new String();
		for(int i = 0; i < preferenze.size(); i++) {
			if(i != preferenze.size() - 1) scelta += String.format("%d°: %s ", (i+1), preferenze.get(i));
			else scelta += String.format("%d°: %s", (i+1), preferenze.get(i));
		}
		String query = "INSERT INTO votiordinale (idVotazione,scelta) VALUES (?,?)"; //query da eseguire
		
		Connection conn = null; 
		PreparedStatement ps = null;
		try {
			conn = getConnection(); //apro connessione
			conn.setAutoCommit(true);
			Statement st = conn.createStatement();
			st.execute("set search_path=digitalvoting"); //set search_path
			
			ps = conn.prepareStatement(query);	//setto il prepareStatement

			//inserisco i valori nella query
			ps.setString(1, idVotazione);
			ps.setString(2, scelta);
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
	
	// inserisce un voto ordinale di tipo 'SCHEDA BIANCA' nel database
	public void insertVotoOrdinale(final String idVotazione) {
		String query = "INSERT INTO votiordinale (idVotazione,scelta) VALUES (?,'SCHEDA BIANCA')"; //query da eseguire
		
		Connection conn = null; 
		PreparedStatement ps = null;
		try {
			conn = getConnection(); //apro connessione
			conn.setAutoCommit(true);
			Statement st = conn.createStatement();
			st.execute("set search_path=digitalvoting"); //set search_path
			
			ps = conn.prepareStatement(query);	//setto il prepareStatement

			//inserisco i valori nella query
			ps.setString(1, idVotazione); 
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
		String query = "DELETE FROM candidatiordinale WHERE id = ? AND nome = ? AND cognome = ?"; //query da eseguire
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
	
	public String[] getInfoVotazioneCategorica(final String id) {
		String query = "SELECT inizio, fine, conpreferenze, modcalcolovincitore FROM categorico WHERE id = ?";
		Connection conn = null; 
		PreparedStatement ps = null;
		String[] result = new String[6];
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
				
				result[4] = Boolean.valueOf(res.getBoolean("conpreferenze")).toString();
				result[5] = res.getString("modcalcolovincitore");
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
	
	public List<String[]> getCandidatiVotazioneCategorica(final String id){
		String query = "SELECT nome, cognome, partito, candidatodipartito1, candidatodipartito2, candidatodipartito3, candidatodipartito4 FROM candidaticategorico WHERE id = ?";
		Connection conn = null; 
		PreparedStatement ps = null;
		List<String[]> result = new ArrayList<>();
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
				String[] item = new String[7];
				item[0] = res.getString("nome");
				item[1] = res.getString("cognome");
				item[2] = res.getString("partito");
				item[3] = res.getString("candidatodipartito1");
				item[4] = res.getString("candidatodipartito2");
				item[5] = res.getString("candidatodipartito3");
				item[6] = res.getString("candidatodipartito4");
				result.add(item);
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
	
	// restituisce tutte le votazioni presenti nel db
	public List<Votazione> getAllExistingVotazioni() throws ParseException {
		Connection conn = null; 
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		List<Votazione> result = new ArrayList<>();
		
		try {
			conn = getConnection(); //apro connessione
			String query1 = "SELECT id, inizio, fine, tipo, testo FROM referendum";
			String query2 = "SELECT id, inizio, fine FROM ordinale";
			String query3 = "SELECT id, inizio, fine, conpreferenze, modcalcolovincitore FROM categorico";
			conn.setAutoCommit(true);
			Statement st = conn.createStatement();
			st.execute("set search_path=digitalvoting"); //set search_path
			
			ps1 = conn.prepareStatement(query1);
			ResultSet res1 = ps1.executeQuery();
			
			while(res1.next()) {
				String id = res1.getString("id");
				String inizio = res1.getString("inizio");
				SimpleDateFormat datetimeFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date start = datetimeFormatter.parse(inizio);
				Timestamp i = new Timestamp(start.getTime());
				
				String fine = res1.getString("fine");
				Date end = datetimeFormatter.parse(fine);
				Timestamp f = new Timestamp(end.getTime());
				
				String tipo = res1.getString("tipo");
				String testo = res1.getString("testo");
				result.add(new VotazioneReferendum(id, i, f, tipo, testo));
			}
			
			ps2 = conn.prepareStatement(query2);
			ResultSet res2 = ps2.executeQuery();
			
			while(res2.next()) {
				String id = res2.getString("id");
				String inizio = res2.getString("inizio");
				SimpleDateFormat datetimeFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date start = datetimeFormatter.parse(inizio);
				Timestamp i = new Timestamp(start.getTime());
				
				String fine = res2.getString("fine");
				Date end = datetimeFormatter.parse(fine);
				Timestamp f = new Timestamp(end.getTime());
				
				result.add(new VotazioneOrdinale(id, i, f));
			}
			
			ps3 = conn.prepareStatement(query3);
			ResultSet res3 = ps3.executeQuery();

			while(res3.next()) {
				String id = res3.getString("id");
				String inizio = res3.getString("inizio");
				SimpleDateFormat datetimeFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date start = datetimeFormatter.parse(inizio);
				Timestamp i = new Timestamp(start.getTime());
				
				String fine = res3.getString("fine");
				Date end = datetimeFormatter.parse(fine);
				Timestamp f = new Timestamp(end.getTime());
				
				boolean conPreferenze = res3.getBoolean("conpreferenze");
				String modCalcoloVincitore = res3.getString("modcalcolovincitore");
				result.add(new VotazioneCategorica(id, i, f, conPreferenze, modCalcoloVincitore));
			}
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				ps1.close();
				ps2.close();
				ps3.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	// restituisce tutte le votazioni attive nel momento in cui viene invocato il metodo, ossia tutte le votazioni già iniziate ma non ancora terminate
	public List<Votazione> getAllVotazioni() throws ParseException {
		Connection conn = null; 
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		List<Votazione> result = new ArrayList<>();
		
		try {
			conn = getConnection(); //apro connessione
			String query1 = "SELECT id, inizio, fine, tipo, testo FROM referendum WHERE inizio <= CURRENT_TIMESTAMP AND fine > CURRENT_TIMESTAMP";
			String query2 = "SELECT id, inizio, fine FROM ordinale WHERE inizio <= CURRENT_TIMESTAMP AND fine > CURRENT_TIMESTAMP";
			String query3 = "SELECT id, inizio, fine, conpreferenze, modcalcolovincitore FROM categorico WHERE inizio <= CURRENT_TIMESTAMP AND fine > CURRENT_TIMESTAMP";
			conn.setAutoCommit(true);
			Statement st = conn.createStatement();
			st.execute("set search_path=digitalvoting"); //set search_path
			
			ps1 = conn.prepareStatement(query1);
			ResultSet res1 = ps1.executeQuery();
			
			while(res1.next()) {
				String id = res1.getString("id");
				String inizio = res1.getString("inizio");
				SimpleDateFormat datetimeFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date start = datetimeFormatter.parse(inizio);
				Timestamp i = new Timestamp(start.getTime());
				
				String fine = res1.getString("fine");
				Date end = datetimeFormatter.parse(fine);
				Timestamp f = new Timestamp(end.getTime());
				
				String tipo = res1.getString("tipo");
				String testo = res1.getString("testo");
				result.add(new VotazioneReferendum(id, i, f, tipo, testo));
			}
			
			ps2 = conn.prepareStatement(query2);
			ResultSet res2 = ps2.executeQuery();
			
			while(res2.next()) {
				String id = res2.getString("id");
				String inizio = res2.getString("inizio");
				SimpleDateFormat datetimeFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date start = datetimeFormatter.parse(inizio);
				Timestamp i = new Timestamp(start.getTime());
				
				String fine = res2.getString("fine");
				Date end = datetimeFormatter.parse(fine);
				Timestamp f = new Timestamp(end.getTime());
				
				result.add(new VotazioneOrdinale(id, i, f));
			}
			
			ps3 = conn.prepareStatement(query3);
			ResultSet res3 = ps3.executeQuery();

			while(res3.next()) {
				String id = res3.getString("id");
				String inizio = res3.getString("inizio");
				SimpleDateFormat datetimeFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date start = datetimeFormatter.parse(inizio);
				Timestamp i = new Timestamp(start.getTime());
				
				String fine = res3.getString("fine");
				Date end = datetimeFormatter.parse(fine);
				Timestamp f = new Timestamp(end.getTime());
				
				boolean conPreferenze = res3.getBoolean("conpreferenze");
				String modCalcoloVincitore = res3.getString("modcalcolovincitore");
				result.add(new VotazioneCategorica(id, i, f, conPreferenze, modCalcoloVincitore));
			}
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				ps1.close();
				ps2.close();
				ps3.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public String[] getInfoReferendum(final String id) {
		String query = "SELECT inizio, fine, tipo, testo FROM referendum WHERE id = ?";
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
				result[0] = res.getString("inizio");
				result[1] = res.getString("fine");
				result[2] = res.getString("tipo");
				result[3] = res.getString("testo");
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
	
	public String[] getInfoOrdinale(final String id) {
		String query1 = "SELECT inizio, fine FROM ordinale WHERE id = ?";
		String query2 = "SELECT nome, cognome FROM candidatiordinale WHERE id = ?";
		Connection conn = null; 
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		List<String> r = new ArrayList<>();
		try {
			conn = getConnection(); //apro connessione
			conn.setAutoCommit(true);
			Statement st = conn.createStatement();
			st.execute("set search_path=digitalvoting"); //set search_path
			
			ps1 = conn.prepareStatement(query1);	//setto il prepareStatement

			//inserisco i valori nella query
			ps1.setString(1, id);
			ResultSet res1 = ps1.executeQuery();
			while(res1.next()) {
				r.add(res1.getString("inizio"));
				r.add(res1.getString("fine"));
			}
			
			ps2 = conn.prepareStatement(query2);	//setto il prepareStatement

			//inserisco i valori nella query
			ps2.setString(1, id);
			ResultSet res2 = ps2.executeQuery();
			while(res2.next()) {
				String nome = res2.getString("nome");
				String cognome = res2.getString("cognome");
				r.add(String.format("%s %s", nome, cognome));
			}
			
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
		String[] result = new String[r.size()];
		return r.toArray(result);
	}
	
	public void terminaVotazione(final String id, final String tipo) {
		String query;
		Connection conn = null; 
		PreparedStatement ps = null;
		
		if(tipo.equalsIgnoreCase("referendum")) {
			query = "UPDATE referendum SET fine = CURRENT_TIMESTAMP WHERE id = ?;";
		}else if(tipo.equalsIgnoreCase("categorica")) {
			query = "UPDATE categorico SET fine = CURRENT_TIMESTAMP WHERE id = ?;";
		}else {
			query = "UPDATE ordinale SET fine = CURRENT_TIMESTAMP WHERE id = ?;";
		}
		
		try {
			conn = getConnection(); //apro connessione
			conn.setAutoCommit(true);
			Statement st = conn.createStatement();
			st.execute("set search_path=digitalvoting"); //set search_path
			
			ps = conn.prepareStatement(query);	//setto il prepareStatement

			//inserisco i valori nella query
			ps.setString(1, id);
			
			ps.execute();
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
	
	public int[] getRisultatiReferendum(final String id) {
		String query1 = "SELECT COUNT(*) AS total FROM votireferendum WHERE idvotazione = ? AND scelta = 'SI';";
		String query2 = "SELECT COUNT(*) AS total FROM votireferendum WHERE idvotazione = ? AND scelta = 'NO';";
		String query3 = "SELECT COUNT(*) AS total FROM votireferendum WHERE idvotazione = ? AND scelta = 'SCHEDA BIANCA';";
		Connection conn = null; 
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		int[] result = new int[3];
		try {
			conn = getConnection(); //apro connessione
			conn.setAutoCommit(true);
			Statement st = conn.createStatement();
			st.execute("set search_path=digitalvoting"); //set search_path
			
			ps1 = conn.prepareStatement(query1);	//setto il prepareStatement

			//inserisco i valori nella query
			ps1.setString(1, id);
			ResultSet res1 = ps1.executeQuery();
			while(res1.next()) {
				result[0] = res1.getInt("total");
			}
			
			ps2 = conn.prepareStatement(query2);	//setto il prepareStatement

			//inserisco i valori nella query
			ps2.setString(1, id);
			ResultSet res2 = ps2.executeQuery();
			while(res2.next()) {
				result[1] = res2.getInt("total");
			}
			
			ps3 = conn.prepareStatement(query3);	//setto il prepareStatement

			//inserisco i valori nella query
			ps3.setString(1, id);
			ResultSet res3 = ps3.executeQuery();
			while(res3.next()) {
				result[2] = res3.getInt("total");
			}

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				ps1.close();
				ps2.close();
				ps3.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public int[] getSchedeBiancheETotaleVotantiCategorico(final String idVotazione) {
		int[] result = new int[2];
		String query1 = "SELECT COUNT(*) AS totalBianche FROM voticategorico WHERE idvotazione = ? AND scelta = 'SCHEDA BIANCA';";
		String query2 = "SELECT COUNT(*) AS totalVotanti FROM voticategorico WHERE idvotazione = ?;";
		Connection conn = null; 
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		try {
			conn = getConnection(); //apro connessione
			conn.setAutoCommit(true);
			Statement st = conn.createStatement();
			st.execute("set search_path=digitalvoting"); //set search_path
			
			ps1 = conn.prepareStatement(query1);	//setto il prepareStatement

			//inserisco i valori nella query
			ps1.setString(1, idVotazione);
			ResultSet res1 = ps1.executeQuery();
			while(res1.next()) {
				result[0] = res1.getInt("totalBianche");
			}
			
			ps2 = conn.prepareStatement(query2);	//setto il prepareStatement

			//inserisco i valori nella query
			ps2.setString(1, idVotazione);
			ResultSet res2 = ps2.executeQuery();
			while(res2.next()) {
				result[1] = res2.getInt("totalVotanti");
			}

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
		return result;
	}
	
	public Map<String, Integer> getRisultatiCategorico(final String idVotazione){
		Map<String, Integer> result = new HashMap<>();
		String query = "SELECT scelta FROM voticategorico WHERE idvotazione = ? AND scelta <> 'SCHEDA BIANCA';";
		Connection conn = null; 
		PreparedStatement ps = null;
		try {
			conn = getConnection(); //apro connessione
			conn.setAutoCommit(true);
			Statement st = conn.createStatement();
			st.execute("set search_path=digitalvoting"); //set search_path
			
			ps = conn.prepareStatement(query);	//setto il prepareStatement

			//inserisco i valori nella query
			ps.setString(1, idVotazione);
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				String[] scelta = res.getString("scelta").split(", ");
				for(String s : scelta) {
					String[] pref = s.split(": ");
					if(pref.length == 2 && !pref[1].isEmpty()) {
						if(result.containsKey(pref[1])) {
							result.put(pref[1], result.get(pref[1]) + 1);
						}else {
							result.put(pref[1], 1);
						}
					}
				}	
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
	
	public int[] getSchedeBiancheETotaleVotantiOrdinale(final String idVotazione) {
		int[] result = new int[2];
		String query1 = "SELECT COUNT(*) AS totalBianche FROM votiordinale WHERE idvotazione = ? AND scelta = 'SCHEDA BIANCA';";
		String query2 = "SELECT COUNT(*) AS totalVotanti FROM votiordinale WHERE idvotazione = ?;";
		Connection conn = null; 
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		try {
			conn = getConnection(); //apro connessione
			conn.setAutoCommit(true);
			Statement st = conn.createStatement();
			st.execute("set search_path=digitalvoting"); //set search_path
			
			ps1 = conn.prepareStatement(query1);	//setto il prepareStatement

			//inserisco i valori nella query
			ps1.setString(1, idVotazione);
			ResultSet res1 = ps1.executeQuery();
			while(res1.next()) {
				result[0] = res1.getInt("totalBianche");
			}
			
			ps2 = conn.prepareStatement(query2);	//setto il prepareStatement

			//inserisco i valori nella query
			ps2.setString(1, idVotazione);
			ResultSet res2 = ps2.executeQuery();
			while(res2.next()) {
				result[1] = res2.getInt("totalVotanti");
			}

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
		
		return result;
	}
	
	public Map<String, Map<String, Integer>> getRisultatiOrdinale(final String idVotazione) {
		Map<String, Map<String, Integer>> result = new HashMap<>();
		String query = "SELECT scelta FROM votiordinale WHERE idvotazione = ? AND scelta <> 'SCHEDA BIANCA';";
		Connection conn = null; 
		PreparedStatement ps = null;
		try {
			conn = getConnection(); //apro connessione
			conn.setAutoCommit(true);
			Statement st = conn.createStatement();
			st.execute("set search_path=digitalvoting"); //set search_path
			
			ps = conn.prepareStatement(query);	//setto il prepareStatement

			//inserisco i valori nella query
			ps.setString(1, idVotazione);
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				String[] s = res.getString("scelta").replaceAll("[\\d.]", "").split("°: ");
				for(int i = 0; i < s.length; i++)
		            s[i] = s[i].trim();

		        s = Arrays.copyOfRange(s, 1, s.length);
		        for(int i = 0; i < s.length; i++) {
		        	if(result.containsKey(s[i])) {
		        		Map<String, Integer> temp = result.get(s[i]);
		        		if(temp.containsKey(Integer.valueOf(i+1).toString())) {
		        			temp.put(Integer.valueOf(i+1).toString(), temp.get(Integer.valueOf(i+1).toString()) + 1);
		        		}else {
		        			temp.put(Integer.valueOf(i+1).toString(), 1);
		        		}
		        		
		        		result.remove(s[i]);
		        		result.put(s[i], temp);
		        	} else {
		        		Map<String, Integer> temp = new HashMap<>();
		        		temp.put(Integer.valueOf(i+1).toString(), 1);
		        		result.put(s[i], temp);
		        	}
		        }
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
	
	public void insertIntoLogTable(final Timestamp data, final Amministratore admin, final String azione) {
		Objects.requireNonNull(data);
		Objects.requireNonNull(admin);
		if(Objects.requireNonNull(azione).isEmpty() || Objects.requireNonNull(azione).isBlank())
			throw new IllegalArgumentException("Non è specificata alcuna azione!");
		
		String query = "INSERT INTO logtable(dataeora, utente, azione) VALUES(?,?,?);";
		Connection conn = null; 
		PreparedStatement ps = null;
		try {
			conn = getConnection(); //apro connessione
			conn.setAutoCommit(true);
			Statement st = conn.createStatement();
			st.execute("set search_path=digitalvoting"); //set search_path
			
			ps = conn.prepareStatement(query);	//setto il prepareStatement

			//inserisco i valori nella query
			ps.setTimestamp(1, data);
			ps.setString(2, admin.getTaxCode());
			ps.setString(3, azione);
			ps.execute();
			
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
	
	public List<String[]> getLog(){
		List<String[]> result = new ArrayList<>();
		String query = "select dataeora, utente, azione from logtable order by dataeora desc;";
		Connection conn = null; 
		PreparedStatement ps = null;
		try {
			conn = getConnection(); //apro connessione
			conn.setAutoCommit(true);
			Statement st = conn.createStatement();
			st.execute("set search_path=digitalvoting"); //set search_path
			
			ps = conn.prepareStatement(query);	//setto il prepareStatement

			ResultSet res = ps.executeQuery();
			while(res.next()) {
				String[] info = new String[3];
				info[0] = res.getTimestamp("dataeora").toString();
				info[1] = res.getString("utente");
				info[2] = res.getString("azione");
				result.add(info);
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
