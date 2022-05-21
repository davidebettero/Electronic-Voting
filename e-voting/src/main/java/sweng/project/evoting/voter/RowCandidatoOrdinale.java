package sweng.project.evoting.voter;

import java.util.Objects;

public class RowCandidatoOrdinale {
	private final String nome, cognome;
	
	public RowCandidatoOrdinale(final String nome, final String cognome) {
		Objects.requireNonNull(nome, "Deve essere specificato il nome del candidato ordinale");
		Objects.requireNonNull(cognome, "Deve essere specificato il cognome del candidato ordinale");
		
		if(nome.isEmpty() || nome.isBlank() || cognome.isEmpty() || cognome.isBlank())
			throw new IllegalArgumentException("Devono essere indicati nome e cognome del candidato ordinale");
		
		this.nome = nome;
		this.cognome = cognome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getCognome() {
		return cognome;
	}
}
