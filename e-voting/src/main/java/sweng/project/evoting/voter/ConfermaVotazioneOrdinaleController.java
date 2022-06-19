package sweng.project.evoting.voter;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sweng.project.evoting.Candidato;
import sweng.project.evoting.DigitalVotingDaoImpl;
import sweng.project.evoting.Elettore;
import sweng.project.evoting.SessioneSingleton;
import sweng.project.evoting.votazione.VotoOrdinale;

public class ConfermaVotazioneOrdinaleController {
	private String idVotazione;
	private List<Candidato> preferenze;
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button annullaButton;

    @FXML
    private Button confermaButton;

    @FXML
    private Pane pane;

    @FXML
    private Text scelta;

    @FXML
    void handleAnnulla(ActionEvent event) throws IOException {
    	String[] info = new DigitalVotingDaoImpl().getInfoOrdinale(idVotazione);
    	
		FXMLLoader next = new FXMLLoader(getClass().getResource("..//voter//schedaVotoOrdinaleWindow.fxml"));
		Parent root = next.load();
		SchedaVotoOrdinaleController svoc = next.getController();
		if(info.length >= 2)
			svoc.setInfo(Arrays.copyOfRange(info, 2, info.length), idVotazione);
    	
    	pane.getChildren().removeAll();
    	pane.getChildren().setAll(root);
    }

    @FXML
    void handleConferma(ActionEvent event) throws IOException {
    	VotoOrdinale vo = new VotoOrdinale(idVotazione, preferenze);
		new DigitalVotingDaoImpl().insertVotoOrdinale(idVotazione, vo);
		Elettore el = (Elettore) SessioneSingleton.getSessioneSingleton().getUser();
    	el.esprimi_voto(idVotazione);
    	
    	confermaButton.getScene().getWindow().hide();
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("..//voter//voterWindow.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(loader.load()));
			VoterWindowController vwc = loader.getController();
			vwc.setTabella(new DigitalVotingDaoImpl().getAllVotazioni());
			stage.setTitle("Benvenuto " + el.getName());
			stage.show();
		}catch (Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void initialize() {
        assert annullaButton != null : "fx:id=\"annullaButton\" was not injected: check your FXML file 'confermaVotazioneOrdinaleWindow.fxml'.";
        assert confermaButton != null : "fx:id=\"confermaButton\" was not injected: check your FXML file 'confermaVotazioneOrdinaleWindow.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'confermaVotazioneOrdinaleWindow.fxml'.";
        assert scelta != null : "fx:id=\"scelta\" was not injected: check your FXML file 'confermaVotazioneOrdinaleWindow.fxml'.";

    }
    
    public void setText(final String idVotazione, final List<Candidato> preferenze) {
    	this.idVotazione = Objects.requireNonNull(idVotazione);
    	this.preferenze = Objects.requireNonNull(preferenze);
    	
    	String info = new String();
    	for(int i = 0; i < preferenze.size(); i++) {
    		if(i != preferenze.size()-1) info += String.format("%d°: %s\n", (i+1), preferenze.get(i));
    		else info += String.format("%d°: %s", (i+1), preferenze.get(i));
    	}
    	scelta.setText(info);
    	scelta.setFill(Color.BLACK);
    }

}
