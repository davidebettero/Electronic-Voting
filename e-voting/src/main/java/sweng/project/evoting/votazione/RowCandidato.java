package sweng.project.evoting.votazione;

import java.util.Objects;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sweng.project.evoting.Candidato;
import sweng.project.evoting.DigitalVotingDaoImpl;

public class RowCandidato {
	private final String id;
	private final Candidato c;
	private ButtonBar b;
	
	public RowCandidato(final String id, final Candidato c) {
		this.id = id;
		this.c = Objects.requireNonNull(c);
		this.b = new ButtonBar();
		Button button_elimina = new Button("elimina");
		/*
        ImageView eliminaPng = new ImageView(new Image(getClass().getResource("red_x.png").toString()));
        eliminaPng.setFitHeight(20);
        eliminaPng.setPreserveRatio(true);
        button_elimina.setGraphic(eliminaPng);
        */
		button_elimina.setOnAction(event -> handleElimina());
		this.b.getButtons().addAll(button_elimina);
		this.b.setTranslateX(-20);
		
	}
	
	private void handleElimina() {
		new DigitalVotingDaoImpl().eliminaCandidatoOrdinale(id, c);
	}

	public String getNome() {
		return String.format("%s %s", c.getNome().trim(), c.getCognome().trim());
	}
	
	public ButtonBar getButtonBar() {
		return b;
	}
}
