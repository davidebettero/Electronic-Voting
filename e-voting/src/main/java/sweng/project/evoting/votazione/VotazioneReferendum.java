package sweng.project.evoting.votazione;

import java.sql.Timestamp;
import java.util.Objects;
import sweng.project.evoting.DigitalVotingDaoImpl;

public class VotazioneReferendum extends Votazione {
	private final String tipo;
	private final String testo;
	
	public VotazioneReferendum(String id, Timestamp inizio, Timestamp fine, String tipo, String testo) {
		super(id, inizio, fine);
		this.tipo = Objects.requireNonNull(tipo);
		
		if(Objects.requireNonNull(testo).isEmpty() || Objects.requireNonNull(testo).isBlank())
			throw new IllegalArgumentException("Il referendum deve avere un testo");
		this.testo = testo;
	}
	
	public String getTesto() {
		return this.testo;
	}
	
	@Override
	public void insertVotazione() {
		new DigitalVotingDaoImpl().insertReferendumVotingSession(this.getId(), this.getInizio(), this.getFine(), tipo, testo);
	}
	
	@Override
	public String getTipo() {
		return this.tipo;
	}
	
	@Override
	public String toString() {
		return String.format("Referendum %s", tipo);
	}
}
