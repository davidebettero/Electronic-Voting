package sweng.project.evoting;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

import sweng.project.evoting.votazione.Votazione;

public interface DigitalVotingDao {
	
	public boolean isValid(String nome, String password,String type);
	
	public List<String> getAllElettori();
	
	public List<String> getAllAmministratori();
	
	public List<Utente> getAllUtenti();
	
	public String[] getVoterInfo(String username, String password);
	
	public String[] getAdministratorInfo(String username, String password);
	
	public boolean hasAlreadyVoted(String idVotazione, String taxCode, String username);
	
	public void insertVotante(String idVotazione, String taxCode, String username);
	
	public void insertVotoReferendum(String idVotazione, String scelta);

	public void insertReferendumVotingSession(String id,Timestamp inizio,Timestamp fine,String tipo,String Testo);
	
	public void insertOrdinaleVotingSession(String id, Timestamp inizio, Timestamp fine);
	
	public void insertCandidatoOrdinale(final String id, final Candidato c);
	
	public void insertCategoricaVotingSession(String id, Timestamp inizio, Timestamp fine, boolean conPreferenze, String modCalcoloVincitore);
	
	public void insertCandidatoCategorico(String id, Candidato c, String cP1, String cP2, String cP3, String cP4);
	
	public void deleteVotazioneCategorica(final String id);
	
	public void deleteVotazioneOrdinale(final String id);
	
	public void eliminaCandidatoOrdinale(final String id, final Candidato c);
	
	public List<Candidato> candidatiOrdinale(final String id);
	
	public String[] getInfoVotazioneOrdinale(final String id);
	
	public List<Votazione> getAllVotazioni() throws ParseException;
	
	public String[] getInfoReferendum(String id);
	
	public String[] getInfoOrdinale(String id);
	
	public void terminaVotazione(String id, String tipo);
	
	public int[] getRisultatiReferendum(final String id);
}
