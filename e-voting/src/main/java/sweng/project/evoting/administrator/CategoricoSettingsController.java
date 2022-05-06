package sweng.project.evoting.administrator;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import sweng.project.evoting.votazione.VotazioneCategorica;

public class CategoricoSettingsController {
	private boolean conPreferenze = false;
	private boolean maggioranzaAssoluta = false;
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker data;

    @FXML
    private Text errorMsg;

    @FXML
    private ChoiceBox<String> minutiFine;

    @FXML
    private ChoiceBox<String> minutiInizio;

    @FXML
    private Button okButton;

    @FXML
    private ChoiceBox<String> oraFine;

    @FXML
    private ChoiceBox<String> oraInizio;

    @FXML
    private AnchorPane pane;

    @FXML
    private Button undoButton;
    
    public void setPreferenze() {
    	this.conPreferenze = true;
    }
    
    public void setAssoluta() {
    	this.maggioranzaAssoluta = true;
    }

    @FXML
    void handleData(ActionEvent event) {

    }

    @FXML
    void handleMinutiFine(MouseEvent event) {

    }

    @FXML
    void handleMinutiInizio(MouseEvent event) {

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
    void handleOk(ActionEvent event) throws ParseException, IOException {
    	if(data.getValue() == null) {
    		errorMsg.setText("Data della votazione NON inserita");
    	} else if(!isTimeOk()) {
    		errorMsg.setText("L'ora di inizio NON può essere successiva o identica all'ora di fine!");
    	} else {
    		// inserisco la votazione nel database
    		final String id = UUID.randomUUID().toString(); 
    		String[] d = data.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString().split("-");
        	String[] hI = (oraInizio.getSelectionModel().getSelectedItem() + ":" + minutiInizio.getSelectionModel().getSelectedItem()).toString().split(":");
        	
        	String start = d[0] + "/" + d[1] + "/" + d[2] + " " + hI[0] + ":" + hI[1] + ":00";
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        	Date dateI = sdf.parse(start);
        	long millisStart = dateI.getTime();
    		final Timestamp inizio = new Timestamp(millisStart);
    		
        	String[] hF = (oraFine.getSelectionModel().getSelectedItem() + ":" + minutiFine.getSelectionModel().getSelectedItem()).toString().split(":");
        	String end = d[0] + "/" + d[1] + "/" + d[2] + " " + hF[0] + ":" + hF[1] + ":00";
        	Date dateF = sdf.parse(end);
        	long millisEnd = dateF.getTime();
    		final Timestamp fine = new Timestamp(millisEnd);
    		
    		//CREARE VOTAZIONE CATEGORICA E INSERIRLA NEL DATABASE
    		VotazioneCategorica v = new VotazioneCategorica(id, inizio, fine, conPreferenze, ((maggioranzaAssoluta) ? "maggioranza assoluta" : "maggioranza"));
        	v.insertVotazione();
    		
        	FXMLLoader next = new FXMLLoader(getClass().getResource("..//administrator//inserimentoCandidatiWindow.fxml"));
        	Parent root = next.load();
        	InserimentoCandidatiController icc = next.getController();
        	icc.setId(id);
        	icc.setVotazione(v);
        	if(conPreferenze) icc.setPreferenze();
        	if(maggioranzaAssoluta) icc.setAssoluta();
        	pane.getChildren().removeAll();
        	pane.getChildren().setAll(root);
        	
    	}
    }

    @FXML
    void handleOraFine(MouseEvent event) {

    }

    @FXML
    void handleOraInizio(MouseEvent event) {

    }

    @FXML
    void hanldeUndo(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert data != null : "fx:id=\"data\" was not injected: check your FXML file 'categoricoSettingsWindow.fxml'.";
        assert errorMsg != null : "fx:id=\"errorMsg\" was not injected: check your FXML file 'categoricoSettingsWindow.fxml'.";
        assert minutiFine != null : "fx:id=\"minutiFine\" was not injected: check your FXML file 'categoricoSettingsWindow.fxml'.";
        assert minutiInizio != null : "fx:id=\"minutiInizio\" was not injected: check your FXML file 'categoricoSettingsWindow.fxml'.";
        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'categoricoSettingsWindow.fxml'.";
        assert oraFine != null : "fx:id=\"oraFine\" was not injected: check your FXML file 'categoricoSettingsWindow.fxml'.";
        assert oraInizio != null : "fx:id=\"oraInizio\" was not injected: check your FXML file 'categoricoSettingsWindow.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'categoricoSettingsWindow.fxml'.";
        assert undoButton != null : "fx:id=\"undoButton\" was not injected: check your FXML file 'categoricoSettingsWindow.fxml'.";

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
