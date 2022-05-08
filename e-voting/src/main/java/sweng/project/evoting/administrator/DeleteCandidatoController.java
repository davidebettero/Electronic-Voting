package sweng.project.evoting.administrator;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import sweng.project.evoting.Candidato;
import sweng.project.evoting.DigitalVotingDaoImpl;
import sweng.project.evoting.votazione.VotazioneOrdinale;

public class DeleteCandidatoController {
	private String id;
	private Candidato c;
	private VotazioneOrdinale v;
	
	@FXML
    private Pane pane;
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button annulla;

    @FXML
    private Button conferma;

    @FXML
    private Text nomeCandidato;
    
    public void setId(final String id) {
    	this.id = id;
    }
    
    public void setCandidato(final Candidato c) {
    	this.c = c;
    }
    
    public void setNomeCandidato(final Candidato c) {
    	nomeCandidato.setText(c.getNome() + " " + c.getCognome());
    	nomeCandidato.setTextAlignment(TextAlignment.CENTER);
    	nomeCandidato.setFill(Color.BLUE);
    }

    @FXML
    void handleAnnulla(ActionEvent event) throws IOException {
    	List<Candidato> lista = new DigitalVotingDaoImpl().candidatiOrdinale(id);
    	FXMLLoader next = new FXMLLoader(getClass().getResource("..//administrator//riepilogoVotazioneOrdinaleWindow.fxml"));
    	Parent root = next.load();
    	RiepilogoVotazioneOrdinaleController rvoc = next.getController();
    	rvoc.setTabella(id, lista);
    	rvoc.setId(id);
    	rvoc.setVotazione(v);
    	rvoc.setInfo(id);
    	pane.getChildren().removeAll();
    	pane.getChildren().setAll(root);
    }

    @FXML
    void handleConferma(ActionEvent event) throws IOException {
    	new DigitalVotingDaoImpl().eliminaCandidatoOrdinale(id, c);
    	List<Candidato> lista = new DigitalVotingDaoImpl().candidatiOrdinale(id);
    	FXMLLoader next = new FXMLLoader(getClass().getResource("..//administrator//riepilogoVotazioneOrdinaleWindow.fxml"));
    	Parent root = next.load();
    	RiepilogoVotazioneOrdinaleController rvoc = next.getController();
    	rvoc.setTabella(id, lista);
    	rvoc.setId(id);
    	rvoc.setVotazione(v);
    	rvoc.setInfo(id);
    	pane.getChildren().removeAll();
    	pane.getChildren().setAll(root);
    }

    @FXML
    void initialize() {
        assert annulla != null : "fx:id=\"annulla\" was not injected: check your FXML file 'deleteCandidato.fxml'.";
        assert conferma != null : "fx:id=\"conferma\" was not injected: check your FXML file 'deleteCandidato.fxml'.";
        assert nomeCandidato != null : "fx:id=\"nomeCandidato\" was not injected: check your FXML file 'deleteCandidato.fxml'.";

    }
}
