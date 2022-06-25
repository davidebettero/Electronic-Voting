package sweng.project.evoting.administrator;

import java.util.Objects;

public class RowCandidatoCategorico {
	private String nome, cognome, partito, candidatodipartito1, candidatodipartito2, candidatodipartito3, candidatodipartito4;

	public RowCandidatoCategorico(final String nome, final String cognome, final String partito, final String candidatodipartito1, final String candidatodipartito2, final String candidatodipartito3, final String candidatodipartito4) {
		this.nome = Objects.requireNonNull(nome);
		this.cognome = Objects.requireNonNull(cognome);
		this.partito = Objects.requireNonNull(partito);
		this.candidatodipartito1 = Objects.requireNonNull(candidatodipartito1);
		this.candidatodipartito2 = Objects.requireNonNull(candidatodipartito2);
		this.candidatodipartito3 = Objects.requireNonNull(candidatodipartito3);
		this.candidatodipartito4 = Objects.requireNonNull(candidatodipartito4);
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
	
	public String getPartito() {
		return partito;
	}
	
	public String getCandidatoDiPartito1() {
		return candidatodipartito1;
	}
	
	public String getCandidatoDiPartito2() {
		return candidatodipartito2;
	}
	
	public String getCandidatoDiPartito3() {
		return candidatodipartito3;
	}
	
	public String getCandidatoDiPartito4() {
		return candidatodipartito4;
	}
}
