package sweng.project.evoting.administrator;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sweng.project.evoting.votazione.Votazione;

public class RowVotazioneAdmin {
	@FXML
    private Pane pane;
	
	private ButtonBar b;
	private final Votazione v;
	
	public RowVotazioneAdmin(final Votazione v, final Pane p) {
		this.v = Objects.requireNonNull(v);
		this.pane = Objects.requireNonNull(p);
		
		this.b = new ButtonBar();
		Button button_risultati = new Button("risultati");
		
		button_risultati.setOnAction(event -> {
			try {
				handleVisualizzaRisultati();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		this.b.getButtons().add(button_risultati);
		this.b.setTranslateX(-5);
	}
	
	public void handleVisualizzaRisultati() throws IOException {
		if(v.getFine().after(Timestamp.from(Instant.now()))) {
			// mostrare la schermata votazione ancora attiva
			FXMLLoader loader = new FXMLLoader(getClass().getResource("..//administrator//errVotazioneApertaWindow.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(loader.load()));
			stage.setTitle("Errore - Votazione ancora attiva!");
			stage.setResizable(false);
			stage.show();
		}else {
			//mostrare i risultati
		}
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
