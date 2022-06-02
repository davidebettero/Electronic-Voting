package sweng.project.evoting.administrator;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import sweng.project.evoting.Amministratore;
import sweng.project.evoting.DigitalVotingDaoImpl;
import sweng.project.evoting.SessioneSingleton;

public class ConfermaTerminaVotazioneController {
	private String idVotazione, tipo;
	
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
    
    public void setInfo(final String idVotazione, final String tipo) {
    	this.idVotazione = idVotazione;
    	this.tipo = tipo;
    }

    @FXML
    void handleAnnulla(ActionEvent event) throws IOException, ParseException {
    	FXMLLoader next = new FXMLLoader(getClass().getResource("..//administrator//terminaVotazioniWindow.fxml"));
    	Parent root = next.load();
		TerminaVotazioniController tvc = next.getController();
		tvc.setTabella(new DigitalVotingDaoImpl().getAllVotazioni());
		pane.getChildren().removeAll();
    	pane.getChildren().setAll(root);
    }

    @FXML
    void handleConferma(ActionEvent event) throws IOException, ParseException {
    	Amministratore a = (Amministratore) SessioneSingleton.getSessioneSingleton().getUser();
    	a.terminaVotazione(idVotazione, tipo);
    	
    	FXMLLoader next = new FXMLLoader(getClass().getResource("..//administrator//terminaVotazioniWindow.fxml"));
    	Parent root = next.load();
		TerminaVotazioniController tvc = next.getController();
		tvc.setTabella(new DigitalVotingDaoImpl().getAllVotazioni());
		pane.getChildren().removeAll();
    	pane.getChildren().setAll(root);
    }

    @FXML
    void initialize() {
        assert annullaButton != null : "fx:id=\"annullaButton\" was not injected: check your FXML file 'confermaTerminaVotazioneWindow.fxml'.";
        assert confermaButton != null : "fx:id=\"confermaButton\" was not injected: check your FXML file 'confermaTerminaVotazioneWindow.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'confermaTerminaVotazioneWindow.fxml'.";

    }

}
