package sweng.project.evoting.votazione;

import java.io.IOException;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import sweng.project.evoting.Candidato;
import sweng.project.evoting.DigitalVotingDaoImpl;
import sweng.project.evoting.administrator.DeleteCandidatoController;
import sweng.project.evoting.administrator.SettingVotazioneOrdinaleController;

public class RowCandidato {
	@FXML
    private Pane pane;
	
	private final String id;
	private final Candidato c;
	private ButtonBar b;
	
	public RowCandidato(final String id, final Candidato c, final Pane p) {
		this.pane = p;
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
		button_elimina.setOnAction(event -> {
			try {
				handleElimina();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		this.b.getButtons().addAll(button_elimina);
		this.b.setTranslateX(-20);
		
	}
	
	private void handleElimina() throws IOException {
		FXMLLoader next = new FXMLLoader(getClass().getResource("..//administrator//deleteCandidatoWindow.fxml"));
    	Parent root = next.load();
    	DeleteCandidatoController dcc = next.getController();
    	dcc.setId(id);
    	dcc.setCandidato(c);
    	dcc.setNomeCandidato(c);
    	pane.getChildren().removeAll();
    	pane.getChildren().setAll(root);
	}

	public String getNome() {
		return String.format("%s %s", c.getNome().trim(), c.getCognome().trim());
	}
	
	public ButtonBar getButtonBar() {
		return b;
	}
}
