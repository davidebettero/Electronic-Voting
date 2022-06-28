package sweng.project.evoting.voter;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class SchedaVotoCategoricoController {
	private String idVotazione;
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> colonnaCandidato;

    @FXML
    private TableColumn<?, ?> colonnaCandidatoDiPartito1;

    @FXML
    private TableColumn<?, ?> colonnaCandidatoDiPartito2;

    @FXML
    private TableColumn<?, ?> colonnaCandidatoDiPartito3;

    @FXML
    private TableColumn<?, ?> colonnaCandidatoDiPartito4;

    @FXML
    private TableColumn<?, ?> colonnaPartito;

    @FXML
    private Button confermaButton;

    @FXML
    private Pane pane;

    @FXML
    private Button schedaBiancaButton;

    @FXML
    private TableView<RowSchedaCandidatoCategorico> tabellaElettorale;

    @FXML
    private Text votoNonValido;
    
    public void setId(final String idVotazione) {
    	this.idVotazione = Objects.requireNonNull(idVotazione);
    }
    
    public void setTabella(final List<String[]> candidati) {
    	colonnaCandidato.setCellValueFactory(new PropertyValueFactory<>("Candidato"));
    	colonnaPartito.setCellValueFactory(new PropertyValueFactory<>("Partito"));
    	colonnaCandidatoDiPartito1.setCellValueFactory(new PropertyValueFactory<>("CandidatoDiPartito1"));
    	colonnaCandidatoDiPartito2.setCellValueFactory(new PropertyValueFactory<>("CandidatoDiPartito2"));
    	colonnaCandidatoDiPartito3.setCellValueFactory(new PropertyValueFactory<>("CandidatoDiPartito3"));
    	colonnaCandidatoDiPartito4.setCellValueFactory(new PropertyValueFactory<>("CandidatoDiPartito4"));
    	
    	for(String[] s : candidati) {
    		RowSchedaCandidatoCategorico rscc = new RowSchedaCandidatoCategorico(
    				s[1] + " " + s[0], 
    				s[2], 
    				(s[3] == null) ? "" : s[3],
    				(s[4] == null) ? "" : s[4],
    				(s[5] == null) ? "" : s[5],
    				(s[6] == null) ? "" : s[6]
    		);
    		tabellaElettorale.getItems().add(rscc);
    	}
    }

    @FXML
    void handleConferma(ActionEvent event) {

    }

    @FXML
    void handleSchedaBianca(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert colonnaCandidato != null : "fx:id=\"colonnaCandidato\" was not injected: check your FXML file 'schedaVotoCategoricoWindow.fxml'.";
        assert colonnaCandidatoDiPartito1 != null : "fx:id=\"colonnaCandidatoDiPartito1\" was not injected: check your FXML file 'schedaVotoCategoricoWindow.fxml'.";
        assert colonnaCandidatoDiPartito2 != null : "fx:id=\"colonnaCandidatoDiPartito2\" was not injected: check your FXML file 'schedaVotoCategoricoWindow.fxml'.";
        assert colonnaCandidatoDiPartito3 != null : "fx:id=\"colonnaCandidatoDiPartito3\" was not injected: check your FXML file 'schedaVotoCategoricoWindow.fxml'.";
        assert colonnaCandidatoDiPartito4 != null : "fx:id=\"colonnaCandidatoDiPartito4\" was not injected: check your FXML file 'schedaVotoCategoricoWindow.fxml'.";
        assert colonnaPartito != null : "fx:id=\"colonnaPartito\" was not injected: check your FXML file 'schedaVotoCategoricoWindow.fxml'.";
        assert confermaButton != null : "fx:id=\"confermaButton\" was not injected: check your FXML file 'schedaVotoCategoricoWindow.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'schedaVotoCategoricoWindow.fxml'.";
        assert schedaBiancaButton != null : "fx:id=\"schedaBiancaButton\" was not injected: check your FXML file 'schedaVotoCategoricoWindow.fxml'.";
        assert tabellaElettorale != null : "fx:id=\"tabellaElettorale\" was not injected: check your FXML file 'schedaVotoCategoricoWindow.fxml'.";
        assert votoNonValido != null : "fx:id=\"votoNonValido\" was not injected: check your FXML file 'schedaVotoCategoricoWindow.fxml'.";

    }
}
