package sweng.project.evoting.voter;

import java.net.URL;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class InfoOrdinaleController {
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> colonnaCognome;

    @FXML
    private TableColumn<?, ?> colonnaNome;

    @FXML
    private Text fine;

    @FXML
    private Text inizio;

    @FXML
    private Button okButton;

    @FXML
    private Pane pane;

    @FXML
    private TableView<RowCandidatoOrdinale> tabellaCandidati;

    @FXML
    private Text tipoVotazione;
    
    public void setInfo(final String inizio, final String fine, final String[] candidati) {
    	String[] i = inizio.split(" ");
    	String[] oI = i[0].split("-");
    	this.inizio.setText(String.format("%s/%s/%s ore: %s", oI[2], oI[1], oI[0], i[1].substring(0, 5)));
    	this.inizio.setFill(Color.BLACK);
    	
    	String[] f = fine.split(" ");
    	String[] oF = f[0].split("-");
    	this.fine.setText(String.format("%s/%s/%s ore: %s", oF[2], oF[1], oF[0], f[1].substring(0, 5)));
    	this.fine.setFill(Color.BLACK);
    	
    	tipoVotazione.setText("Votazione ordinale");
    	tipoVotazione.setFill(Color.RED);
    	tipoVotazione.setTextAlignment(TextAlignment.CENTER);
    	
    	setTabella(candidati);
    }
    
    private void setTabella(final String[] candidati) {
    	colonnaNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
    	colonnaCognome.setCellValueFactory(new PropertyValueFactory<>("Cognome"));
    	
    	for(String gen : candidati) {
    		String[] nC = gen.trim().split(" ", 2);
    		RowCandidatoOrdinale rco = new RowCandidatoOrdinale(nC[0], nC[1]);
    		tabellaCandidati.getItems().add(rco);
    	}
    }

    @FXML
    void handleOk(ActionEvent event) {
    	Stage s = (Stage) okButton.getScene().getWindow();
    	s.close();
    }

    @FXML
    void initialize() {
        assert colonnaCognome != null : "fx:id=\"colonnaCognome\" was not injected: check your FXML file 'infoOrdinaleWindow.fxml'.";
        assert colonnaNome != null : "fx:id=\"colonnaNome\" was not injected: check your FXML file 'infoOrdinaleWindow.fxml'.";
        assert fine != null : "fx:id=\"fine\" was not injected: check your FXML file 'infoOrdinaleWindow.fxml'.";
        assert inizio != null : "fx:id=\"inizio\" was not injected: check your FXML file 'infoOrdinaleWindow.fxml'.";
        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'infoOrdinaleWindow.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'infoOrdinaleWindow.fxml'.";
        assert tabellaCandidati != null : "fx:id=\"tabellaCandidati\" was not injected: check your FXML file 'infoOrdinaleWindow.fxml'.";
        assert tipoVotazione != null : "fx:id=\"tipoVotazione\" was not injected: check your FXML file 'infoOrdinaleWindow.fxml'.";

    }

}
