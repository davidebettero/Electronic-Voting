package sweng.project.evoting.administrator;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import sweng.project.evoting.votazione.Votazione;

public class VisualizzaRisultatiController {
	private List<Votazione> lista;
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<RowVotazioneAdmin, String> colonnaFine;

    @FXML
    private TableColumn<RowVotazioneAdmin, String> colonnaInizio;

    @FXML
    private TableColumn<RowVotazioneAdmin, Button> colonnaRisultati;

    @FXML
    private TableColumn<RowVotazioneAdmin, String> colonnaVotazioni;

    @FXML
    private Pane pane;

    @FXML
    private TableView<RowVotazioneAdmin> tabellaVotazioni;

    @FXML
    private Button undoButton;
    
    public void setTabella(final List<Votazione> l) {
    	this.lista = l;
    	colonnaVotazioni.setCellValueFactory(new PropertyValueFactory<>("Tipo"));
    	colonnaInizio.setCellValueFactory(new PropertyValueFactory<>("Inizio"));
    	colonnaFine.setCellValueFactory(new PropertyValueFactory<>("Fine"));
    	colonnaRisultati.setCellValueFactory(new PropertyValueFactory<>("ButtonBar"));
    	
    	for(Votazione v : lista) {
    		RowVotazioneAdmin rv = new RowVotazioneAdmin(v, pane);
        	tabellaVotazioni.getItems().add(rv);
    	}
    	if(lista.size() == 0) {
    		tabellaVotazioni.setPlaceholder(new Label("Non c'è nessuna votazione di cui puoi visualizzare i risultati"));
    	}
    }

    @FXML
    void handleUndo(ActionEvent event) throws IOException {
    	Pane next = FXMLLoader.load(getClass().getResource("..//administrator//administratorWindow.fxml"));
    	pane.getChildren().removeAll();
    	pane.getChildren().setAll(next);
    }

    @FXML
    void initialize() {
        assert colonnaFine != null : "fx:id=\"colonnaFine\" was not injected: check your FXML file 'visualizzaRisultatiWindow.fxml'.";
        assert colonnaInizio != null : "fx:id=\"colonnaInizio\" was not injected: check your FXML file 'visualizzaRisultatiWindow.fxml'.";
        assert colonnaRisultati != null : "fx:id=\"colonnaRisultati\" was not injected: check your FXML file 'visualizzaRisultatiWindow.fxml'.";
        assert colonnaVotazioni != null : "fx:id=\"colonnaVotazioni\" was not injected: check your FXML file 'visualizzaRisultatiWindow.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'visualizzaRisultatiWindow.fxml'.";
        assert tabellaVotazioni != null : "fx:id=\"tabellaVotazioni\" was not injected: check your FXML file 'visualizzaRisultatiWindow.fxml'.";
        assert undoButton != null : "fx:id=\"undoButton\" was not injected: check your FXML file 'visualizzaRisultatiWindow.fxml'.";

    }
}
