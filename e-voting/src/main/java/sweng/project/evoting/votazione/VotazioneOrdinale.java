package sweng.project.evoting.votazione;

import java.sql.Timestamp;
import java.util.List;

import sweng.project.evoting.Candidato;
import sweng.project.evoting.DigitalVotingDaoImpl;

public class VotazioneOrdinale extends Votazione {
	
	public VotazioneOrdinale(String id, Timestamp inizio, Timestamp fine) {
		super(id, inizio, fine);
	}
	
	@Override
	public void insertVotazione() {
		new DigitalVotingDaoImpl().insertOrdinaleVotingSession(this.getId(), this.getInizio(), this.getFine());
	}
	
	public void insertCandidato(final String id, final Candidato c) {
		new DigitalVotingDaoImpl().insertCandidatoOrdinale(this.getId(), c);
	}

	@Override
	public String getTipo() {
		return "Ordinale";
	}

}
