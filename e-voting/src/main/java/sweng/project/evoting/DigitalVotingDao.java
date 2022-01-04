package sweng.project.evoting;

import java.util.List;

public interface DigitalVotingDao {
	
	public boolean isValid(String nome, String password,String type);
	
	public List<String> getAllElettori();
	
	public List<String> getAllAmministratori();
	
	public List<Utente> getAllUtenti();
	
	public void addVotoCategorico(String id,String idVoto,String [] scelte);
	
	public void addVotoReferendum(String id,String idVoto,Boolean scelte);
	
	public void insertVotingSession(String id,String inizio,String fine,String modVoto,String modCalcolo);
}
