package sweng.project.evoting.voter;

import java.util.Objects;
import javafx.scene.control.TextField;


public class RowCandidatoOrdinale {
	private final String nome, cognome;
	private TextField scelta;
	
	public RowCandidatoOrdinale(final String nome, final String cognome) {
		Objects.requireNonNull(nome, "Deve essere specificato il nome del candidato ordinale");
		Objects.requireNonNull(cognome, "Deve essere specificato il cognome del candidato ordinale");
		
		if(nome.isEmpty() || nome.isBlank() || cognome.isEmpty() || cognome.isBlank())
			throw new IllegalArgumentException("Devono essere indicati nome e cognome del candidato ordinale");
		
		this.nome = nome;
		this.cognome = cognome;
		
		scelta = new TextField();
		scelta.setMaxWidth(35);
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public String getGeneralita() {
		return String.format("%s %s", nome, cognome);
	}
	
	public TextField getTextField() {
		return scelta;
	}
}
