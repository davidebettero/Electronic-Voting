package sweng.project.evoting.administrator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class InserimentoCandidatiController {
	// variabile che vale true se la votazione è categorica con preferenze, false altrimenti. Di default è impostata a false.
	private boolean preferenze = false;
	
	// variabile che vale true se è una votazione a maggioranza assoluta, false altrimenti. Di default è impostata a false.
	private boolean assoluta = false;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField candidato;

    @FXML
    private TextField candidatoPartitoDue;

    @FXML
    private TextField candidatoPartitoQuattro;

    @FXML
    private TextField candidatoPartitoTre;

    @FXML
    private TextField candidatoPartitoUno;

    @FXML
    private Button okButton;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField partito;

    @FXML
    private Button undoButton;
    
    public void setPreferenze() {
    	preferenze = true;
    }
    
    public void setAssoluta() {
    	assoluta = true;
    }

    @FXML
    private void handleCandidato(ActionEvent event) {

    }

    @FXML
    private void handleCandidatoPartitoDue(ActionEvent event) {

    }

    @FXML
    private void handleCandidatoPartitoQuattro(ActionEvent event) {

    }

    @FXML
    private void handleCandidatoPartitoTre(ActionEvent event) {

    }

    @FXML
    private void handleCandidatoPartitoUno(ActionEvent event) {

    }

    @FXML
    private void handleOk(ActionEvent event) {

    }

    @FXML
    private void handlePartito(ActionEvent event) {

    }

    @FXML
    private void hanldeUndo(ActionEvent event) throws IOException {
    	if(preferenze) {
	    	AnchorPane next = FXMLLoader.load(getClass().getResource("..//administrator//modalitaCalcoloVincitoreCategoricoPreferenzeWindow.fxml"));
	    	pane.getChildren().removeAll();
	    	pane.getChildren().setAll(next);
    	} else {
    		AnchorPane next = FXMLLoader.load(getClass().getResource("..//administrator//modalitaCalcoloVincitoreCategoricoWindow.fxml"));
        	pane.getChildren().removeAll();
        	pane.getChildren().setAll(next);
    	}
    }

    @FXML
    private void initialize() {
        assert candidato != null : "fx:id=\"candidato\" was not injected: check your FXML file 'inserimentoCandidatiWindow.fxml'.";
        assert candidatoPartitoDue != null : "fx:id=\"candidatoPartitoDue\" was not injected: check your FXML file 'inserimentoCandidatiWindow.fxml'.";
        assert candidatoPartitoQuattro != null : "fx:id=\"candidatoPartitoQuattro\" was not injected: check your FXML file 'inserimentoCandidatiWindow.fxml'.";
        assert candidatoPartitoTre != null : "fx:id=\"candidatoPartitoTre\" was not injected: check your FXML file 'inserimentoCandidatiWindow.fxml'.";
        assert candidatoPartitoUno != null : "fx:id=\"candidatoPartitoUno\" was not injected: check your FXML file 'inserimentoCandidatiWindow.fxml'.";
        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'inserimentoCandidatiWindow.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'inserimentoCandidatiWindow.fxml'.";
        assert partito != null : "fx:id=\"partito\" was not injected: check your FXML file 'inserimentoCandidatiWindow.fxml'.";
        assert undoButton != null : "fx:id=\"undoButton\" was not injected: check your FXML file 'inserimentoCandidatiWindow.fxml'.";

    }

}
