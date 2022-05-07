package sweng.project.evoting.administrator;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import sweng.project.evoting.Candidato;
import sweng.project.evoting.DigitalVotingDaoImpl;
import sweng.project.evoting.votazione.RowCandidato;
import sweng.project.evoting.votazione.VotazioneOrdinale;

public class RiepilogoVotazioneOrdinaleController {
	private String id;
	private VotazioneOrdinale v;
	private List<Candidato> lista;
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> colonnaAzioni;

    @FXML
    private TableColumn<?, ?> colonnaCandidati;

    @FXML
    private Text dataFineValue;

    @FXML
    private Text dataInizioValue;

    @FXML
    private Text oFValue;

    @FXML
    private Text oIValue;

    @FXML
    private Button okButton;

    @FXML
    private Pane pane;

    @FXML
    private TableView<RowCandidato> tabellaCandidati;

    @FXML
    private Button undoButton;
    
    public void setId(final String id) {
    	this.id = id;
    }
    
    public void setVotazione(final VotazioneOrdinale v) {
    	this.v = v;
    }
    
    public void setTabella(List<Candidato> l) {
    	this.lista = l;
    	colonnaCandidati.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        colonnaAzioni.setCellValueFactory(new PropertyValueFactory<>("ButtonBar"));
        
        //List<Candidato> lista = new DigitalVotingDaoImpl().candidatiOrdinale("2acb3b3f-4b01-4776-a1d8-43952b4daa19");
        for(Candidato c : lista) {
        	RowCandidato rc = new RowCandidato(id, c);
        	tabellaCandidati.getItems().add(rc);
        }
    }

    @FXML
    void handleOk(ActionEvent event) {
    	System.out.println(id);
    }

    @FXML
    void hanldeUndo(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert colonnaAzioni != null : "fx:id=\"colonnaAzioni\" was not injected: check your FXML file 'riepilogoVotazioneOrdinaleWindow.fxml'.";
        assert colonnaCandidati != null : "fx:id=\"colonnaCandidati\" was not injected: check your FXML file 'riepilogoVotazioneOrdinaleWindow.fxml'.";
        assert dataFineValue != null : "fx:id=\"dataFineValue\" was not injected: check your FXML file 'riepilogoVotazioneOrdinaleWindow.fxml'.";
        assert dataInizioValue != null : "fx:id=\"dataInizioValue\" was not injected: check your FXML file 'riepilogoVotazioneOrdinaleWindow.fxml'.";
        assert oFValue != null : "fx:id=\"oFValue\" was not injected: check your FXML file 'riepilogoVotazioneOrdinaleWindow.fxml'.";
        assert oIValue != null : "fx:id=\"oIValue\" was not injected: check your FXML file 'riepilogoVotazioneOrdinaleWindow.fxml'.";
        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'riepilogoVotazioneOrdinaleWindow.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'riepilogoVotazioneOrdinaleWindow.fxml'.";
        assert tabellaCandidati != null : "fx:id=\"tabellaCandidati\" was not injected: check your FXML file 'riepilogoVotazioneOrdinaleWindow.fxml'.";
        assert undoButton != null : "fx:id=\"undoButton\" was not injected: check your FXML file 'riepilogoVotazioneOrdinaleWindow.fxml'.";
    }
}
