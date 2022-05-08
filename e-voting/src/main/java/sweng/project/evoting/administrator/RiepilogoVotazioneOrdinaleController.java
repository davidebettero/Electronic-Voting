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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
    
    public void setTabella(final String id, final List<Candidato> l) {
    	this.lista = l;
    	colonnaCandidati.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        colonnaAzioni.setCellValueFactory(new PropertyValueFactory<>("ButtonBar"));

        for(Candidato c : lista) {
        	RowCandidato rc = new RowCandidato(id, c, pane);
        	tabellaCandidati.getItems().add(rc);
        }
    }
    
    public void setInfo(final String id) {
    	String[] info = new DigitalVotingDaoImpl().getInfoVotazioneOrdinale(id);
    	dataInizioValue.setText(info[0]);
    	dataInizioValue.setFill(Color.BLUE);
    	oIValue.setText(info[1]);
    	oIValue.setFill(Color.BLUE);
    	dataFineValue.setText(info[2]);
    	dataFineValue.setFill(Color.BLUE);
    	oFValue.setText(info[3]);
    	oFValue.setFill(Color.BLUE);
    }

    @FXML
    void handleOk(ActionEvent event) throws IOException {
    	AnchorPane next = FXMLLoader.load(getClass().getResource("..//administrator//votazioneOrdinaleCreataWindow.fxml"));
    	pane.getChildren().removeAll();
    	pane.getChildren().setAll(next);
    }

    @FXML
    void hanldeUndo(ActionEvent event) throws IOException {
    	FXMLLoader next = new FXMLLoader(getClass().getResource("..//administrator//settingVotazioneOrdinaleWindow.fxml"));
    	Parent root = next.load();
    	SettingVotazioneOrdinaleController svoc = next.getController();
    	svoc.setId(id);
    	svoc.setVotazione(v);
    	pane.getChildren().removeAll();
    	pane.getChildren().setAll(root);
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
