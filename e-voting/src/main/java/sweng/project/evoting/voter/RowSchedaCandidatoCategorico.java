package sweng.project.evoting.voter;

import java.util.Objects;

import javafx.scene.control.CheckBox;

public class RowSchedaCandidatoCategorico {
	private CheckBox candidato;
	private CheckBox partito;
	private CheckBox candidatoDiPartito1;
	private CheckBox candidatoDiPartito2;
	private CheckBox candidatoDiPartito3;
	private CheckBox candidatoDiPartito4;
	
	public RowSchedaCandidatoCategorico(final String candidato, final String partito, final String candidatoDiPartito1, final String candidatoDiPartito2, final String candidatoDiPartito3, final String candidatoDiPartito4) {
		if(Objects.requireNonNull(candidato).trim().isEmpty() || Objects.requireNonNull(partito).trim().isEmpty())
			throw new IllegalArgumentException("Devono essere specificati il candidato principale e il partito");
		
		this.candidato = new CheckBox();
		this.candidato.setText(candidato);
		
		this.partito = new CheckBox();
		this.partito.setText(partito);
		
		if(!candidatoDiPartito1.isEmpty()) {
			this.candidatoDiPartito1 = new CheckBox();
			this.candidatoDiPartito1.setText(candidatoDiPartito1);
		}
		
		if(!candidatoDiPartito2.isEmpty()) {
			this.candidatoDiPartito2 = new CheckBox();
			this.candidatoDiPartito2.setText(candidatoDiPartito2);
		}
		
		if(!candidatoDiPartito3.isEmpty()) {
			this.candidatoDiPartito3 = new CheckBox();
			this.candidatoDiPartito3.setText(candidatoDiPartito3);
		}
		
		if(!candidatoDiPartito4.isEmpty()) {
			this.candidatoDiPartito4 = new CheckBox();
			this.candidatoDiPartito4.setText(candidatoDiPartito4);
		}
	}
	
	public CheckBox getCandidato() {
		return candidato;
	}
	
	public CheckBox getPartito() {
		return partito;
	}
	
	public CheckBox getCandidatoDiPartito1() {
		return candidatoDiPartito1;
	}
	
	public CheckBox getCandidatoDiPartito2() {
		return candidatoDiPartito2;
	}
	
	public CheckBox getCandidatoDiPartito3() {
		return candidatoDiPartito3;
	}
	
	public CheckBox getCandidatoDiPartito4() {
		return candidatoDiPartito4;
	}
}
