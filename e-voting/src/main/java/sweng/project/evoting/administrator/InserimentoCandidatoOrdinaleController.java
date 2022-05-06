package sweng.project.evoting.administrator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sweng.project.evoting.Candidato;
import sweng.project.evoting.votazione.VotazioneOrdinale;

public class InserimentoCandidatoOrdinaleController {
	private String id;
	private VotazioneOrdinale v;
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField candidato;

    @FXML
    private Button okButton;

    @FXML
    private AnchorPane pane;

    @FXML
    private Button undoButton;

    @FXML
    void handleCandidato(ActionEvent event) {

    }
    
    public void setId(final String id) {
    	this.id = id;
    }
    
    public void setVotazione(final VotazioneOrdinale v) {
    	this.v = v;
    }

    @FXML
    void handleOk(ActionEvent event) throws IOException {
    	if(candidato != null && !candidato.getText().toString().isEmpty() && !candidato.getText().toString().isBlank()) {
    		String[] generalita = candidato.getText().toString().split(" ");
    		v.insertCandidato(this.id, new Candidato(generalita[0], generalita[1], null));
	    	
	    	FXMLLoader next = new FXMLLoader(getClass().getResource("..//administrator//settingVotazioneOrdinaleWindow.fxml"));
        	Parent root = next.load();
        	SettingVotazioneOrdinaleController svoc = next.getController();
        	svoc.setId(id);
        	svoc.setVotazione(v);
        	pane.getChildren().removeAll();
        	pane.getChildren().setAll(root);
    	}
    }

    @FXML
    void hanldeUndo(ActionEvent event) throws IOException {
    	AnchorPane next = FXMLLoader.load(getClass().getResource("..//administrator//ordinaleSettingsWindow.fxml"));
    	pane.getChildren().removeAll();
    	pane.getChildren().setAll(next);
    }

    @FXML
    void initialize() {
        assert candidato != null : "fx:id=\"candidato\" was not injected: check your FXML file 'inserimentoCandidatoOrdinaleWindow.fxml'.";
        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'inserimentoCandidatoOrdinaleWindow.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'inserimentoCandidatoOrdinaleWindow.fxml'.";
        assert undoButton != null : "fx:id=\"undoButton\" was not injected: check your FXML file 'inserimentoCandidatoOrdinaleWindow.fxml'.";

    }
}
