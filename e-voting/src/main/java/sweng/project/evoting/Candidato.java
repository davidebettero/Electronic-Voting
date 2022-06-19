package sweng.project.evoting;

import java.util.Objects;

public class Candidato {
	private final String nome, cognome, partito;
	
	public Candidato(final String nome, final String cognome, final String partito) {
		if(nome.isEmpty() || cognome.isEmpty()) throw new IllegalArgumentException("Nome e cognome del candidato devono essere specificati");
		this.nome = Objects.requireNonNull(nome);
		this.cognome = Objects.requireNonNull(cognome);
		this.partito = (partito == null) ? "" : partito;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public String getPartito() {
		if(partito.isEmpty() || partito.isBlank()) return "Candidato votazione ordinale (non indicato il partito)";
		else return partito;
	}
	
	@Override
	public String toString() {
		return String.format("%s %s", nome, cognome) + ((partito.isEmpty() || partito.isBlank()) ? "" : ("\nPartito: " + partito));
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Candidato) {
			Candidato c = (Candidato) obj;
			return c.nome.equals(this.nome) && c.cognome.equals(this.cognome) && c.partito.equals(this.partito);
		}
		return false;
	}
}
