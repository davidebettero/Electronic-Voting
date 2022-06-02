package sweng.project.evoting.voter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import sweng.project.evoting.DigitalVotingDaoImpl;
import sweng.project.evoting.Elettore;
import sweng.project.evoting.SessioneSingleton;

public class ConfermaVotoReferendumController {
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
    
    private String idVotazione;

    @FXML
    void handleAnnulla(ActionEvent event) throws IOException {
    	String[] info = new DigitalVotingDaoImpl().getInfoReferendum(idVotazione);
    	
    	FXMLLoader next = new FXMLLoader(getClass().getResource("..//voter//schedaVotoReferendumWindow.fxml"));
    	Parent root = next.load();
    	SchedaVotoReferendumController svrc = next.getController();
		svrc.setInfo(idVotazione, info[3], String.format("Referendum %s".toUpperCase(), info[2]));
    	pane.getChildren().removeAll();
    	pane.getChildren().setAll(root);
    }

    @FXML
    void handleConferma(ActionEvent event) {
    	Elettore el = (Elettore)SessioneSingleton.getSessioneSingleton().getUser();
    	el.esprimi_voto(idVotazione);
    	new DigitalVotingDaoImpl().insertVotoReferendum(idVotazione, scelta.getText().toString());
    	
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
        assert annullaButton != null : "fx:id=\"annullaButton\" was not injected: check your FXML file 'confermaVotoReferendumWindow.fxml'.";
        assert confermaButton != null : "fx:id=\"confermaButton\" was not injected: check your FXML file 'confermaVotoReferendumWindow.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'confermaVotoReferendumWindow.fxml'.";
        assert scelta != null : "fx:id=\"scelta\" was not injected: check your FXML file 'confermaVotoReferendumWindow.fxml'.";

    }

	public void setScelta(final String idVotazione, final String s) {
		this.idVotazione = idVotazione;
		
		scelta.setText(s);
		scelta.setTextAlignment(TextAlignment.CENTER);
	}
}
