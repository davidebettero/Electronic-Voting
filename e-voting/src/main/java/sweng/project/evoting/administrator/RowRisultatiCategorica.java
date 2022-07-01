package sweng.project.evoting.administrator;

import java.util.Objects;

public class RowRisultatiCategorica {
	private String candidatoPartito; 
	private int numeroVoti;
	
	public RowRisultatiCategorica(final String candidatoPartito, final int numeroVoti) {
		if(numeroVoti < 0)
			throw new IllegalArgumentException("Il numero di voti ricevuti da un candidato o da un partito non può essere negativo!");
		
		this.candidatoPartito = Objects.requireNonNull(candidatoPartito);
		this.numeroVoti = numeroVoti;
	}
	
	public String getCandidatoPartito() {
		return candidatoPartito;
	}
	
	public Integer getNumeroVoti() {
		return numeroVoti;
	}
}
