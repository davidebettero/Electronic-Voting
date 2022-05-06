package sweng.project.evoting;

import java.sql.Timestamp;
import java.util.List;

public interface DigitalVotingDao {
	
	public boolean isValid(String nome, String password,String type);
	
	public List<String> getAllElettori();
	
	public List<String> getAllAmministratori();
	
	public List<Utente> getAllUtenti();
	
	public void addVotoCategorico(String id,String idVoto,String [] scelte);
	
	public void addVotoReferendum(String id,String idVoto,Boolean scelte);
	
	public void insertReferendumVotingSession(String id,Timestamp inizio,Timestamp fine,String tipo,String Testo);
	
	public void insertOrdinaleVotingSession(String id, Timestamp inizio, Timestamp fine);
	
	public void insertCandidatoOrdinale(final String id, final Candidato c);
}
