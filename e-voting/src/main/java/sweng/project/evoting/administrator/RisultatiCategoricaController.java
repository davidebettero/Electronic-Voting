package sweng.project.evoting.administrator;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RisultatiCategoricaController {
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<RowRisultatiCategorica, String> colonnaCandidatiEPartiti;

    @FXML
    private TableColumn<RowRisultatiCategorica, String> colonnaNumVotiOttenuti;

    @FXML
    private Text numeroSchedeBianche;

    @FXML
    private Text numeroTotaleVotanti;

    @FXML
    private Button okButton;

    @FXML
    private Pane pane;

    @FXML
    private TableView<RowRisultatiCategorica> tabellaRisultati;
    
    public void setInfo(final int numSchedeBianche, final int numTotVotanti) {
    	if(numSchedeBianche > numTotVotanti)
    		throw new IllegalArgumentException("Il numero di schede bianche non può essere maggiore del numero totale dei votanti");
    	if(numSchedeBianche < 0 || numTotVotanti < 0)
    		throw new IllegalArgumentException("Il numero di schede bianche o il numero totale dei votanti non può essere negativo");
    	
    	numeroSchedeBianche.setText(Integer.valueOf(numSchedeBianche).toString());
    	numeroSchedeBianche.setFill(Color.BLACK);
    	numeroTotaleVotanti.setText(Integer.valueOf(numTotVotanti).toString());
    	numeroTotaleVotanti.setFill(Color.BLACK);
    }

    @FXML
    void handleOk(ActionEvent event) {
    	Stage s = (Stage) okButton.getScene().getWindow();
    	s.close();
    }

    @FXML
    void initialize() {
        assert colonnaCandidatiEPartiti != null : "fx:id=\"colonnaCandidatiEPartiti\" was not injected: check your FXML file 'risultatiCategoricaWindow.fxml'.";
        assert colonnaNumVotiOttenuti != null : "fx:id=\"colonnaNumVotiOttenuti\" was not injected: check your FXML file 'risultatiCategoricaWindow.fxml'.";
        assert numeroSchedeBianche != null : "fx:id=\"numeroSchedeBianche\" was not injected: check your FXML file 'risultatiCategoricaWindow.fxml'.";
        assert numeroTotaleVotanti != null : "fx:id=\"numeroTotaleVotanti\" was not injected: check your FXML file 'risultatiCategoricaWindow.fxml'.";
        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'risultatiCategoricaWindow.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'risultatiCategoricaWindow.fxml'.";
        assert tabellaRisultati != null : "fx:id=\"tabellaRisultati\" was not injected: check your FXML file 'risultatiCategoricaWindow.fxml'.";

    }

	public void setTabella(final Map<String, Integer> risultatiCategorico) {
		colonnaCandidatiEPartiti.setCellValueFactory(new PropertyValueFactory<>("CandidatoPartito"));
		colonnaNumVotiOttenuti.setCellValueFactory(new PropertyValueFactory<>("NumeroVoti"));
		
		for(var entry : risultatiCategorico.entrySet()) {
			RowRisultatiCategorica rrc = new RowRisultatiCategorica(entry.getKey(), entry.getValue());
			tabellaRisultati.getItems().add(rrc);
		}
		
		if(risultatiCategorico.isEmpty()) {
			tabellaRisultati.setPlaceholder(new Label("Nessun candidato e/o partito ha ottenuto una preferenza"));
		}
	}
}
