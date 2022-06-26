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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sweng.project.evoting.administrator.RowCandidatoCategorico;

public class InfoCategoricaController {
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
    
    public void setInfo(final String oI, final String oF, final String dI, final String dF, final String conPreferenze, final String modCalcoloVincitore) {
    	oraInizio.setText(Objects.requireNonNull(oI));
    	oraInizio.setFill(Color.BLACK);
    	
    	oraFine.setText(Objects.requireNonNull(oF));
    	oraFine.setFill(Color.BLACK);
    	
    	dataInizio.setText(Objects.requireNonNull(dI));
    	dataInizio.setFill(Color.BLACK);
    	
    	dataFine.setText(Objects.requireNonNull(dF));
    	dataFine.setFill(Color.BLACK);
    	
    	preferenze.setText(Objects.requireNonNull(conPreferenze));
    	preferenze.setFill(Color.BLACK);
    	
    	calcoloVincitore.setText(Objects.requireNonNull(modCalcoloVincitore));
    	calcoloVincitore.setFill(Color.BLACK);
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
    void handleOk(ActionEvent event) {
    	Stage s = (Stage) okButton.getScene().getWindow();
    	s.close();
    }

    @FXML
    void initialize() {
        assert calcoloVincitore != null : "fx:id=\"calcoloVincitore\" was not injected: check your FXML file 'infoCategoricaWindow.fxml'.";
        assert colonnaCanDiPartito1 != null : "fx:id=\"colonnaCanDiPartito1\" was not injected: check your FXML file 'infoCategoricaWindow.fxml'.";
        assert colonnaCanDiPartito2 != null : "fx:id=\"colonnaCanDiPartito2\" was not injected: check your FXML file 'infoCategoricaWindow.fxml'.";
        assert colonnaCanDiPartito3 != null : "fx:id=\"colonnaCanDiPartito3\" was not injected: check your FXML file 'infoCategoricaWindow.fxml'.";
        assert colonnaCanDiPartito4 != null : "fx:id=\"colonnaCanDiPartito4\" was not injected: check your FXML file 'infoCategoricaWindow.fxml'.";
        assert colonnaCandidati != null : "fx:id=\"colonnaCandidati\" was not injected: check your FXML file 'infoCategoricaWindow.fxml'.";
        assert colonnaPartiti != null : "fx:id=\"colonnaPartiti\" was not injected: check your FXML file 'infoCategoricaWindow.fxml'.";
        assert dataFine != null : "fx:id=\"dataFine\" was not injected: check your FXML file 'infoCategoricaWindow.fxml'.";
        assert dataInizio != null : "fx:id=\"dataInizio\" was not injected: check your FXML file 'infoCategoricaWindow.fxml'.";
        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'infoCategoricaWindow.fxml'.";
        assert oraFine != null : "fx:id=\"oraFine\" was not injected: check your FXML file 'infoCategoricaWindow.fxml'.";
        assert oraInizio != null : "fx:id=\"oraInizio\" was not injected: check your FXML file 'infoCategoricaWindow.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'infoCategoricaWindow.fxml'.";
        assert preferenze != null : "fx:id=\"preferenze\" was not injected: check your FXML file 'infoCategoricaWindow.fxml'.";
        assert tabellaCandidati != null : "fx:id=\"tabellaCandidati\" was not injected: check your FXML file 'infoCategoricaWindow.fxml'.";

    }
}
