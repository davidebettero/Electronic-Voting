package sweng.project.evoting.votazione;

import java.sql.Timestamp;

import sweng.project.evoting.Candidato;
import sweng.project.evoting.DigitalVotingDaoImpl;

public class VotazioneCategorica extends Votazione {
	private final boolean conPreferenze;
	private final String modCalcoloVincitore;

	public VotazioneCategorica(String id, Timestamp inizio, Timestamp fine, boolean conPreferenze, String modCalcoloVincitore) {
		super(id, inizio, fine);
		
		this.conPreferenze = conPreferenze;
		if(modCalcoloVincitore == null || modCalcoloVincitore.isEmpty() || modCalcoloVincitore.isBlank())
			throw new IllegalArgumentException("Deve essere indicata la modalità di calcolo del vincitore della votazione categorica");
		if(!(modCalcoloVincitore.equalsIgnoreCase("maggioranza") || modCalcoloVincitore.equalsIgnoreCase("maggioranza assoluta")))
			throw new IllegalArgumentException("Modalità di calcolo del vincitore della votazione categorica sconosciuta");
		this.modCalcoloVincitore = modCalcoloVincitore;
	}

	@Override
	public String getTipo() {
		return "Votazione categorica" + ((conPreferenze) ? " con preferenze" : "");
	}
	
	public String getModalitaCalcoloVincitore() {
		return modCalcoloVincitore;
	}

	@Override
	public void insertVotazione() {
		new DigitalVotingDaoImpl().insertCategoricaVotingSession(this.getId(), this.getInizio(), this.getFine(), this.conPreferenze, this.modCalcoloVincitore);
		
	}
	
	public void insertCandidato(final Candidato c, final String cP1, final String cP2, final String cP3, final String cP4) {
		new DigitalVotingDaoImpl().insertCandidatoCategorico(this.getId(), c, cP1, cP2, cP3, cP4);
	}
	
	public void deleteVotazione() {
		new DigitalVotingDaoImpl().deleteVotazioneCategorica(this.getId());
	}

}
