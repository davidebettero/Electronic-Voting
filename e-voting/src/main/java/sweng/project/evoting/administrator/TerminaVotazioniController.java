package sweng.project.evoting.administrator;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import sweng.project.evoting.votazione.Votazione;

public class TerminaVotazioniController {
	private List<Votazione> lista;
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> colonnaFine;

    @FXML
    private TableColumn<?, ?> colonnaInizio;

    @FXML
    private TableColumn<?, ?> colonnaTermina;

    @FXML
    private TableColumn<?, ?> colonnaVotazioniAttive;

    @FXML
    private Pane pane;

    @FXML
    private TableView<RowVotazioneAttiva> tabellaVotazioni;

    @FXML
    private Button undoButton;
    
    public void setTabella(final List<Votazione> l) {
    	this.lista = l;
    	
    	colonnaVotazioniAttive.setCellValueFactory(new PropertyValueFactory<>("Tipo"));
    	colonnaInizio.setCellValueFactory(new PropertyValueFactory<>("Inizio"));
    	colonnaFine.setCellValueFactory(new PropertyValueFactory<>("Fine"));
    	colonnaTermina.setCellValueFactory(new PropertyValueFactory<>("ButtonBar"));
    	
    	for(Votazione v : lista) {
    		RowVotazioneAttiva rva = new RowVotazioneAttiva(v, pane);
        	tabellaVotazioni.getItems().add(rva);
    	}
    }

    @FXML
    void handleUndo(ActionEvent event) throws IOException {
    	AnchorPane next = FXMLLoader.load(getClass().getResource("..//administrator//administratorWindow.fxml"));
    	pane.getChildren().removeAll();
    	pane.getChildren().setAll(next);
    }

    @FXML
    void initialize() {
        assert colonnaFine != null : "fx:id=\"colonnaFine\" was not injected: check your FXML file 'terminaVotazioniWindow.fxml'.";
        assert colonnaInizio != null : "fx:id=\"colonnaInizio\" was not injected: check your FXML file 'terminaVotazioniWindow.fxml'.";
        assert colonnaTermina != null : "fx:id=\"colonnaTermina\" was not injected: check your FXML file 'terminaVotazioniWindow.fxml'.";
        assert colonnaVotazioniAttive != null : "fx:id=\"colonnaVotazioniAttive\" was not injected: check your FXML file 'terminaVotazioniWindow.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'terminaVotazioniWindow.fxml'.";
        assert tabellaVotazioni != null : "fx:id=\"tabellaVotazioni\" was not injected: check your FXML file 'terminaVotazioniWindow.fxml'.";
        assert undoButton != null : "fx:id=\"undoButton\" was not injected: check your FXML file 'terminaVotazioniWindow.fxml'.";

    }

}
