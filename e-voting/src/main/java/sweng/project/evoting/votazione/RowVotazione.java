package sweng.project.evoting.votazione;

import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.Pane;

public class RowVotazione {
	@FXML
    private Pane pane;
	
	private final Votazione v;
	private ButtonBar b;
	
	public RowVotazione(final Votazione v, final Pane p) {
		this.pane = Objects.requireNonNull(p);
		this.v = Objects.requireNonNull(v);
		this.b = new ButtonBar();
		Button button_vota = new Button("vota");
		Button button_info = new Button("info");
		
		button_vota.setOnAction(event -> {
			try {
				handleVota();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		button_info.setOnAction(event -> {
			try {
				handleInfo();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		this.b.getButtons().add(button_vota);
		this.b.getButtons().add(button_info);
		this.b.setTranslateX(-15);
	}
	
	private void handleVota() throws IOException {
		
	}
	
	private void handleInfo() throws IOException {
		
	}
	
	public String getTipo() {
		return v.toString().trim();
	}
	
	public ButtonBar getButtonBar() {
		return b;
	}
}
