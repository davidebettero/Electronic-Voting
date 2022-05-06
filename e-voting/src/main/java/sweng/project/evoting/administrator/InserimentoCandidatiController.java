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
import sweng.project.evoting.votazione.VotazioneCategorica;

public class InserimentoCandidatiController {
	// variabile che vale true se la votazione è categorica con preferenze, false altrimenti. Di default è impostata a false.
	private boolean preferenze = false;
	
	// variabile che vale true se è una votazione a maggioranza assoluta, false altrimenti. Di default è impostata a false.
	private boolean assoluta = false;
	
	private String id;
	private VotazioneCategorica v;

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
    
    public void setId(final String id) {
    	this.id = id;
    }
    
    public void setVotazione(final VotazioneCategorica v) {
    	this.v = v;
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
    private void handleOk(ActionEvent event) throws IOException {
    	if(candidato != null && partito != null && !candidato.getText().toString().isEmpty() && !partito.getText().toString().isEmpty()) {   	
	    	final String cP1, cP2, cP3, cP4;
    		if(candidatoPartitoUno == null || candidatoPartitoUno.getText().toString().isEmpty() || candidatoPartitoUno.getText().toString().isBlank())
    			cP1 = "";
    		else
    			cP1 = candidatoPartitoUno.getText().toString();
    		
    		if(candidatoPartitoDue == null || candidatoPartitoDue.getText().toString().isEmpty() || candidatoPartitoDue.getText().toString().isBlank())
    			cP2 = "";
    		else
    			cP2 = candidatoPartitoDue.getText().toString();
    		
    		if(candidatoPartitoTre == null || candidatoPartitoTre.getText().toString().isEmpty() || candidatoPartitoTre.getText().toString().isBlank())
    			cP3 = "";
    		else
    			cP3 = candidatoPartitoTre.getText().toString();
    		
    		if(candidatoPartitoQuattro == null || candidatoPartitoQuattro.getText().toString().isEmpty() || candidatoPartitoQuattro.getText().toString().isBlank())
    			cP4 = "";
    		else
    			cP4 = candidatoPartitoQuattro.getText().toString();
    			
    		String[] generalita = candidato.getText().toString().split(" ");
    		v.insertCandidato(new Candidato(generalita[0], generalita[1], partito.getText().toString()), cP1, cP2, cP3, cP4);
    		
    		FXMLLoader next = new FXMLLoader(getClass().getResource("..//administrator//settingVotazioneWindow.fxml"));
        	Parent root = next.load();
        	SettingVotazioneController svc = next.getController();
        	svc.setId(id);
        	svc.setVotazione(v);
        	pane.getChildren().removeAll();
        	pane.getChildren().setAll(root);
    	}
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
