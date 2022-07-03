package sweng.project.evoting.administrator;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
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
import sweng.project.evoting.Amministratore;
import sweng.project.evoting.DigitalVotingDaoImpl;
import sweng.project.evoting.SessioneSingleton;
import sweng.project.evoting.votazione.VotazioneCategorica;

public class RiepilogoVotazioneCategoricaController {
	private String idVotazione;
	private VotazioneCategorica v;
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text calcoloVincitore;

    @FXML
    private TableColumn<?, ?> colonnaCanDiPartito1;

    @FXML
    private TableColumn<?, ?> colonnaCanDiPartito2;

    @FXML
    private TableColumn<?, ?> colonnaCanDiPartito3;

    @FXML
    private TableColumn<?, ?> colonnaCanDiPartito4;

    @FXML
    private TableColumn<?, ?> colonnaCandidati;

    @FXML
    private TableColumn<?, ?> colonnaPartiti;

    @FXML
    private Text dataFine;

    @FXML
    private Text dataInizio;

    @FXML
    private Button okButton;

    @FXML
    private Text oraFine;

    @FXML
    private Text oraInizio;

    @FXML
    private Pane pane;

    @FXML
    private Text preferenze;

    @FXML
    private TableView<RowCandidatoCategorico> tabellaCandidati;

    @FXML
    private Button undoButton;
    
    public void setId(final String idVotazione) {
    	this.idVotazione = Objects.requireNonNull(idVotazione);
    }
    
    public void setVotazione(VotazioneCategorica v) {
    	this.v = Objects.requireNonNull(v);
    }
    
    public void setInfo(final String oI, final String oF, final String dI, final String dF, final String conPreferenze, final String modCalcoloVincitore) {
    	oraInizio.setText(Objects.requireNonNull(oI));
    	oraInizio.setFill(Color.BLUE);
    	
    	oraFine.setText(Objects.requireNonNull(oF));
    	oraFine.setFill(Color.BLUE);
    	
    	dataInizio.setText(Objects.requireNonNull(dI));
    	dataInizio.setFill(Color.BLUE);
    	
    	dataFine.setText(Objects.requireNonNull(dF));
    	dataFine.setFill(Color.BLUE);
    	
    	preferenze.setText(Objects.requireNonNull(conPreferenze));
    	preferenze.setFill(Color.BLUE);
    	
    	calcoloVincitore.setText(Objects.requireNonNull(modCalcoloVincitore));
    	calcoloVincitore.setFill(Color.BLUE);
    }
    
    public void setTabella(final List<String[]> candidati) {
    	colonnaCandidati.setCellValueFactory(new PropertyValueFactory<>("Generalita"));
    	colonnaPartiti.setCellValueFactory(new PropertyValueFactory<>("Partito"));
    	colonnaCanDiPartito1.setCellValueFactory(new PropertyValueFactory<>("CandidatoDiPartito1"));
    	colonnaCanDiPartito2.setCellValueFactory(new PropertyValueFactory<>("CandidatoDiPartito2"));
    	colonnaCanDiPartito3.setCellValueFactory(new PropertyValueFactory<>("CandidatoDiPartito3"));
    	colonnaCanDiPartito4.setCellValueFactory(new PropertyValueFactory<>("CandidatoDiPartito4"));
    	
    	for(String[] s : candidati) {
    		RowCandidatoCategorico rcc = new RowCandidatoCategorico(s[0], s[1], s[2], s[3], s[4], s[5], s[6]);
    		tabellaCandidati.getItems().add(rcc);
    	}
    }

    @FXML
    void handleOk(ActionEvent event) throws IOException {
    	new DigitalVotingDaoImpl().insertIntoLogTable(
    			Timestamp.from(Instant.now()), 
    			(Amministratore) SessioneSingleton.getSessioneSingleton().getUser(),
    			String.format("Ha creato la %s con id: %s", v.getTipo().toLowerCase(), idVotazione)
    		);
    	
    	AnchorPane next = FXMLLoader.load(getClass().getResource("..//administrator//votazioneCategoricaCreataWindow.fxml"));
    	pane.getChildren().removeAll();
    	pane.getChildren().setAll(next);
    }

    @FXML
    void handleUndo(ActionEvent event) throws IOException {
    	FXMLLoader next = new FXMLLoader(getClass().getResource("..//administrator//settingVotazioneWindow.fxml"));
    	Parent root = next.load();
    	SettingVotazioneController svc = next.getController();
    	svc.setId(idVotazione);
    	svc.setVotazione(v);
    	pane.getChildren().removeAll();
    	pane.getChildren().setAll(root);
    }

    @FXML
    void initialize() {
        assert calcoloVincitore != null : "fx:id=\"calcoloVincitore\" was not injected: check your FXML file 'riepilogoVotazioneCategoricaWindow.fxml'.";
        assert colonnaCanDiPartito1 != null : "fx:id=\"colonnaCanDiPartito1\" was not injected: check your FXML file 'riepilogoVotazioneCategoricaWindow.fxml'.";
        assert colonnaCanDiPartito2 != null : "fx:id=\"colonnaCanDiPartito2\" was not injected: check your FXML file 'riepilogoVotazioneCategoricaWindow.fxml'.";
        assert colonnaCanDiPartito3 != null : "fx:id=\"colonnaCanDiPartito3\" was not injected: check your FXML file 'riepilogoVotazioneCategoricaWindow.fxml'.";
        assert colonnaCanDiPartito4 != null : "fx:id=\"colonnaCanDiPartito4\" was not injected: check your FXML file 'riepilogoVotazioneCategoricaWindow.fxml'.";
        assert colonnaCandidati != null : "fx:id=\"colonnaCandidati\" was not injected: check your FXML file 'riepilogoVotazioneCategoricaWindow.fxml'.";
        assert colonnaPartiti != null : "fx:id=\"colonnaPartiti\" was not injected: check your FXML file 'riepilogoVotazioneCategoricaWindow.fxml'.";
        assert dataFine != null : "fx:id=\"dataFine\" was not injected: check your FXML file 'riepilogoVotazioneCategoricaWindow.fxml'.";
        assert dataInizio != null : "fx:id=\"dataInizio\" was not injected: check your FXML file 'riepilogoVotazioneCategoricaWindow.fxml'.";
        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'riepilogoVotazioneCategoricaWindow.fxml'.";
        assert oraFine != null : "fx:id=\"oraFine\" was not injected: check your FXML file 'riepilogoVotazioneCategoricaWindow.fxml'.";
        assert oraInizio != null : "fx:id=\"oraInizio\" was not injected: check your FXML file 'riepilogoVotazioneCategoricaWindow.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'riepilogoVotazioneCategoricaWindow.fxml'.";
        assert preferenze != null : "fx:id=\"preferenze\" was not injected: check your FXML file 'riepilogoVotazioneCategoricaWindow.fxml'.";
        assert tabellaCandidati != null : "fx:id=\"tabellaCandidati\" was not injected: check your FXML file 'riepilogoVotazioneCategoricaWindow.fxml'.";
        assert undoButton != null : "fx:id=\"undoButton\" was not injected: check your FXML file 'riepilogoVotazioneCategoricaWindow.fxml'.";

    }
}
