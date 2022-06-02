package sweng.project.evoting.administrator;

import java.io.IOException;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.Pane;
import sweng.project.evoting.votazione.Votazione;

public class RowVotazioneAttiva {
	@FXML
    private Pane pane;
	
	private ButtonBar b;
	private final Votazione v;
	
	public RowVotazioneAttiva(final Votazione v, final Pane p) {
		this.v = Objects.requireNonNull(v);
		this.pane = Objects.requireNonNull(p);
		
		this.b = new ButtonBar();
		Button button_termina = new Button("termina");
		
		button_termina.setOnAction(event -> {
			try {
				handleTermina();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		this.b.getButtons().add(button_termina);
		this.b.setTranslateX(-15);
	}
	
	public void handleTermina() throws IOException {
		FXMLLoader next = new FXMLLoader(getClass().getResource("..//administrator//confermaTerminaVotazioneWindow.fxml"));
    	Parent root = next.load();
    	ConfermaTerminaVotazioneController ctvc = next.getController();
		ctvc.setInfo(v.getId(), v.getTipo());
    	pane.getChildren().removeAll();
    	pane.getChildren().setAll(root);
	}
	
	public String getTipo() {
		return v.toString().trim();
	}
	
	public String getInizio() {
		String inizio = v.getInizio().toString();
		String[] info = inizio.split(" ");
		String[] data = info[0].split("-");
		String[] ora = info[1].split(":");
		return String.format("%s/%s/%s %s:%s", data[2], data[1], data[0], ora[0], ora[1]);
	}
	
	public String getFine() {
		String fine = v.getFine().toString();
		String[] info = fine.split(" ");
		String[] data = info[0].split("-");
		String[] ora = info[1].split(":");
		return String.format("%s/%s/%s %s:%s", data[2], data[1], data[0], ora[0], ora[1]);
	}
	
	public ButtonBar getButtonBar() {
		return b;
	}
}
