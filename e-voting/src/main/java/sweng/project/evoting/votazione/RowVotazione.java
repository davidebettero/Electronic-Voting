package sweng.project.evoting.votazione;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sweng.project.evoting.DigitalVotingDaoImpl;
import sweng.project.evoting.Elettore;
import sweng.project.evoting.SessioneSingleton;
import sweng.project.evoting.voter.InfoOrdinaleController;
import sweng.project.evoting.voter.InfoReferendumController;
import sweng.project.evoting.voter.SchedaVotoReferendumController;

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
		// controllare prima che l'utente non abbia già votato e nel caso permettergli di votare
		final String idVotazione = v.getId();
		Elettore e = (Elettore) SessioneSingleton.getSessioneSingleton().getUser();
		if(e.checkAlreadyVoted(idVotazione)) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("..//voter//giaVotatoWindow.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(loader.load()));
			stage.setTitle("Errore");
			stage.setResizable(false);
			stage.show();
		}else {
			try {
				String[] info = new DigitalVotingDaoImpl().getInfoReferendum(v.getId());
				
				FXMLLoader next = new FXMLLoader(getClass().getResource("..//voter//schedaVotoReferendumWindow.fxml"));
		    	Parent root = next.load();
		    	SchedaVotoReferendumController svrc = next.getController();
				svrc.setInfo(idVotazione, info[3], String.format("Referendum %s".toUpperCase(), info[2]));
		    	pane.getChildren().removeAll();
		    	pane.getChildren().setAll(root);
		    	
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	private void handleInfo() throws IOException {
		if(v.getTipo().toLowerCase().contains("referendum")) {
			try {
				String[] info = new DigitalVotingDaoImpl().getInfoReferendum(v.getId());
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("..//voter//infoReferendumWindow.fxml"));
    			Stage stage = new Stage();
    			stage.setScene(new Scene(loader.load()));
    			
    			InfoReferendumController irc = loader.getController();
    			irc.setInfo(info[0], info[1], info[2], info[3]);
    			
    			stage.setTitle("Info referendum");
    			stage.setResizable(false);
    			stage.show();
    		}catch (Exception e) {
    			e.printStackTrace();
    		}
		}else if (v.getTipo().toLowerCase().contains("ordinale")) {
			String[] info = new DigitalVotingDaoImpl().getInfoOrdinale(v.getId());

			FXMLLoader loader = new FXMLLoader(getClass().getResource("..//voter//infoOrdinaleWindow.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(loader.load()));

			InfoOrdinaleController ioc = loader.getController();
			if(info.length >= 2)
				ioc.setInfo(info[0], info[1], Arrays.copyOfRange(info, 2, info.length));
			
			stage.setTitle("Info votazione ordinale");
			stage.setResizable(false);
			stage.show();
		}else if(v.getTipo().toLowerCase().contains("categorica")) {
			
		}
	}
	
	public String getTipo() {
		return v.toString().trim();
	}
	
	public ButtonBar getButtonBar() {
		return b;
	}
}
