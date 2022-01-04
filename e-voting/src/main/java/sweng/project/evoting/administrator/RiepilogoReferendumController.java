package sweng.project.evoting.administrator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import sweng.project.evoting.DigitalVotingDaoImpl;

public class RiepilogoReferendumController {
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Text dataVotazione;

    @FXML
    private Text oraFineVotazione;

    @FXML
    private Text oraInizioVotazione;

    @FXML
    private Button confermaButton;

    @FXML
    private AnchorPane pane;

    @FXML
    private Text testo;

    @FXML
    private Text typeOfReferendum;

    @FXML
    private Button undoButton;
    
    @FXML
    private void handleData(MouseEvent event) {

    }

    @FXML
    private void handleOraFine(MouseEvent event) {

    }

    @FXML
    private void handleOraInizio(MouseEvent event) {

    }

    @FXML
    private void handleConferma(ActionEvent event) throws IOException {
    	/*
    	String[] tipo = typeOfReferendum.getText().split(" ");

    	// crea la votazione "referendum" secondo le preferenze selezionate dall'amministratore
    	DigitalVotingDaoImpl d = new DigitalVotingDaoImpl();
    	d.insertVotingSession("1", dataVotazione + " " + oraInizioVotazione + ":00", dataVotazione + " " + oraFineVotazione + ":00", "Referendum", tipo[1]+" "+tipo[2]);
    	*/
    	AnchorPane next = FXMLLoader.load(getClass().getResource("..//administrator//referendumCreatoWindow.fxml"));
    	pane.getChildren().removeAll();
    	pane.getChildren().setAll(next);
    }

    @FXML
    private void handleTesto(MouseEvent event) {

    }

    @FXML
    private void handleType(MouseEvent event) {
    	
    }

    @FXML
    private void hanldeUndo(ActionEvent event) throws IOException {
    	AnchorPane next = FXMLLoader.load(getClass().getResource("..//administrator//referendumSettingsWindow.fxml"));
    	pane.getChildren().removeAll();
    	pane.getChildren().setAll(next);
    }
    
    public void insertData(String data) {
    	dataVotazione.setFill(Color.BLUE);
    	dataVotazione.setText(data);
    }
    
    public void insertOraInizio(String oI) {
    	oraInizioVotazione.setFill(Color.BLUE);
    	oraInizioVotazione.setText(oI);
    }
    
    public void insertOraFine(String oF) {
    	oraFineVotazione.setFill(Color.BLUE);
    	oraFineVotazione.setText(oF);
    }
    
    public void insertType(String type) {
    	typeOfReferendum.setFill(Color.BLUE);
    	typeOfReferendum.setText("Referendum " + type + " quorum");
    }
    
    public void insertText(String text) {
    	testo.setFill(Color.BLUE);
    	testo.setText(text);
    }

    @FXML
    private void initialize() {
        assert confermaButton != null : "fx:id=\"confermaButton\" was not injected: check your FXML file 'riepilogoReferendumWindow.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'riepilogoReferendumWindow.fxml'.";
        assert testo != null : "fx:id=\"testo\" was not injected: check your FXML file 'riepilogoReferendumWindow.fxml'.";
        assert typeOfReferendum != null : "fx:id=\"typeOfReferendum\" was not injected: check your FXML file 'riepilogoReferendumWindow.fxml'.";
        assert undoButton != null : "fx:id=\"undoButton\" was not injected: check your FXML file 'riepilogoReferendumWindow.fxml'.";

    }
}
