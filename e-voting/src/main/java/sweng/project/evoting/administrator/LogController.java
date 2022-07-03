package sweng.project.evoting.administrator;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LogController {
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button chiudiButton;

    @FXML
    private TableColumn<RowLog, String> colonnaAzione;

    @FXML
    private TableColumn<RowLog, String> colonnaTimestamp;

    @FXML
    private TableColumn<RowLog, String> colonnaUtente;

    @FXML
    private TableView<RowLog> logTable;

    @FXML
    private Pane pane;
    
    public void setTabella(final List<String[]> info) {
    	colonnaTimestamp.setCellValueFactory(new PropertyValueFactory<>("Data"));
    	colonnaUtente.setCellValueFactory(new PropertyValueFactory<>("TaxCode"));
    	colonnaAzione.setCellValueFactory(new PropertyValueFactory<>("Azione"));
    	
    	for(int i = 0; i < info.size(); i++) {
    		String[] s = info.get(i);
    		RowLog rl = new RowLog(s[0].substring(0, 19), s[1], s[2]);
    		logTable.getItems().add(rl);
    	}
    	if(info.size() == 0) {
    		logTable.setPlaceholder(new Label("Non c'è nessun evento nel log"));
    	}
    }

    @FXML
    void handleChiudi(ActionEvent event) {
    	Stage s = (Stage) chiudiButton.getScene().getWindow();
    	s.close();
    }

    @FXML
    void initialize() {
        assert chiudiButton != null : "fx:id=\"chiudiButton\" was not injected: check your FXML file 'logWindow.fxml'.";
        assert colonnaAzione != null : "fx:id=\"colonnaAzione\" was not injected: check your FXML file 'logWindow.fxml'.";
        assert colonnaTimestamp != null : "fx:id=\"colonnaTimestamp\" was not injected: check your FXML file 'logWindow.fxml'.";
        assert colonnaUtente != null : "fx:id=\"colonnaUtente\" was not injected: check your FXML file 'logWindow.fxml'.";
        assert logTable != null : "fx:id=\"logTable\" was not injected: check your FXML file 'logWindow.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'logWindow.fxml'.";

    }
}
