package sweng.project.evoting.administrator;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class ReferendumSettingsController {
	// variabile che vale true se il referendum è CON quorum, false altrimenti. Di default è settata a false.
	private boolean quorum = false;
	
	@FXML
    private Text errorMsg;
	
	@FXML
    private DatePicker data;
	
	@FXML
    private ChoiceBox<String> minutiFine;

    @FXML
    private ChoiceBox<String> minutiInizio;
    
    @FXML
    private ChoiceBox<String> oraFine;

    @FXML
    private ChoiceBox<String> oraInizio;
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button okButton;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextArea referendumText;

    @FXML
    private Button undoButton;
    
    // metodo che assegna true alla variabile booleana quorum
    public void setQuorum() {
    	quorum = true;
    }
    
    // restituisce true se ora di inizio è antecedente all'ora di fine, false altrimenti
    private boolean isTimeOk() {
    	int oI = Integer.parseInt(oraInizio.getSelectionModel().getSelectedItem());
    	int oF = Integer.parseInt(oraFine.getSelectionModel().getSelectedItem());
    	int mI = Integer.parseInt(minutiInizio.getSelectionModel().getSelectedItem());
    	int mF = Integer.parseInt(minutiFine.getSelectionModel().getSelectedItem());
    	
    	if(oI > oF) return false;
    	if(oI == oF && mI >= mF) return false;
    	
    	return true;
    }

    @FXML
    private void handleOk(ActionEvent event) throws IOException {
    	if(data.getValue() == null) {
    		errorMsg.setText("Data della votazione NON inserita");
    	}
    	else if(!isTimeOk()) {
    		errorMsg.setText("L'ora di inizio NON può essere successiva o identica all'ora di fine!");
    	} else {
	    	String text = referendumText.getText();
	    	FXMLLoader next = new FXMLLoader(getClass().getResource("..//administrator//riepilogoReferendumWindow.fxml"));
	    	Parent root = next.load();
	    	RiepilogoReferendumController rrc = next.getController();
	    	// se quorum = true allora il referendum sarà con quorum, senza altrimenti.
	    	if(quorum) rrc.insertType("CON"); else rrc.insertType("SENZA");
	    	rrc.insertText(text);
	    	rrc.insertData(data.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
	    	rrc.insertOraInizio(oraInizio.getSelectionModel().getSelectedItem() + ":" + minutiInizio.getSelectionModel().getSelectedItem());
	    	rrc.insertOraFine(oraFine.getSelectionModel().getSelectedItem() + ":" + minutiFine.getSelectionModel().getSelectedItem());
	    	pane.getChildren().removeAll();
	    	pane.getChildren().setAll(root);
    	}
    }

    @FXML
    private void handleReferendumText(MouseEvent event) {
    	
    }
    
    @FXML
    private void handleData(ActionEvent event) {

    }

    @FXML
    private void handleMinutiFine(MouseEvent event) {

    }

    @FXML
    private void handleMinutiInizio(MouseEvent event) {

    }
    
    @FXML
    private void handleOraFine(MouseEvent event) {

    }

    @FXML
    private void handleOraInizio(MouseEvent event) {

    }

    @FXML
    private void hanldeUndo(ActionEvent event) throws IOException {
    	AnchorPane next = FXMLLoader.load(getClass().getResource("..//administrator//modalitaCalcoloVincitoreReferendumWindow.fxml"));
    	pane.getChildren().removeAll();
    	pane.getChildren().setAll(next);
    }

    @FXML
    private void initialize() {
    	assert data != null : "fx:id=\"data\" was not injected: check your FXML file 'referendumSettingsWindow.fxml'.";
        assert minutiFine != null : "fx:id=\"minutiFine\" was not injected: check your FXML file 'referendumSettingsWindow.fxml'.";
        assert minutiInizio != null : "fx:id=\"minutiInizio\" was not injected: check your FXML file 'referendumSettingsWindow.fxml'.";
        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'referendumSettingsWindow.fxml'.";
        assert oraFine != null : "fx:id=\"oraFine\" was not injected: check your FXML file 'referendumSettingsWindow.fxml'.";
        assert oraInizio != null : "fx:id=\"oraInizio\" was not injected: check your FXML file 'referendumSettingsWindow.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'referendumSettingsWindow.fxml'.";
        assert referendumText != null : "fx:id=\"referendumText\" was not injected: check your FXML file 'referendumSettingsWindow.fxml'.";
        assert undoButton != null : "fx:id=\"undoButton\" was not injected: check your FXML file 'referendumSettingsWindow.fxml'.";

        ObservableList<String> ore = FXCollections.observableArrayList("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
    	oraInizio.setItems(ore);
    	oraInizio.setValue(ore.get(0));
    	oraFine.setItems(ore);
    	oraFine.setValue(ore.get(0));
    	
    	ObservableList<String> minuti = FXCollections.observableArrayList(
    			"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23",
    			"24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47",
    			"48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59");
    	minutiInizio.setItems(minuti);
    	minutiInizio.setValue(minuti.get(0));
    	minutiFine.setItems(minuti);
    	minutiFine.setValue(minuti.get(0));
    }
}
