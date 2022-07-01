package sweng.project.evoting.voter;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import sweng.project.evoting.Elettore;
import sweng.project.evoting.SessioneSingleton;

public class SchedaVotoCategoricoController {
	private String idVotazione;
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<RowSchedaCandidatoCategorico, CheckBox> colonnaCandidato;

    @FXML
    private TableColumn<RowSchedaCandidatoCategorico, CheckBox> colonnaCandidatoDiPartito1;

    @FXML
    private TableColumn<RowSchedaCandidatoCategorico, CheckBox> colonnaCandidatoDiPartito2;

    @FXML
    private TableColumn<RowSchedaCandidatoCategorico, CheckBox> colonnaCandidatoDiPartito3;

    @FXML
    private TableColumn<RowSchedaCandidatoCategorico, CheckBox> colonnaCandidatoDiPartito4;

    @FXML
    private TableColumn<RowSchedaCandidatoCategorico, CheckBox> colonnaPartito;

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
    
    private boolean partitoDiversoCandidato() {
    	ObservableList<RowSchedaCandidatoCategorico> lista = tabellaElettorale.getItems();
    	for(int i = 0; i < lista.size(); i++) {
    		for(int j = 0; j < lista.size(); j++) {
    			if(i != j && lista.get(i).getCandidato().isSelected() && lista.get(j).getPartito().isSelected()) {
    				return true;
    			}
    		}
    	}
    	
    	for(int i = 0; i < lista.size(); i++) {
    		for(int j = 0; j < lista.size(); j++) {
    			if(i != j && lista.get(i).getPartito().isSelected() && lista.get(j).getCandidato().isSelected()) {
    				return true;
    			}
    		}
    	}
    	return false;
    }
    
    private boolean piuDiUnCandidato() {
    	ObservableList<RowSchedaCandidatoCategorico> lista = tabellaElettorale.getItems();
    	boolean check = false;
    	for(int i = 0; i < lista.size(); i++) {
    		if(lista.get(i).getCandidato().isSelected() && check) {
    			return true;
    		}
    		if(lista.get(i).getCandidato().isSelected() && !check) {
    			check = true;
    		}
    	}
    	return false;
    }
    
    private boolean piuDiUnPartito() {
    	ObservableList<RowSchedaCandidatoCategorico> lista = tabellaElettorale.getItems();
    	boolean check = false;
    	for(int i = 0; i < lista.size(); i++) {
    		if(lista.get(i).getPartito().isSelected() && check) {
    			return true;
    		}
    		if(lista.get(i).getPartito().isSelected() && !check) {
    			check = true;
    		}
    	}
    	return false;
    }
    
    private boolean candidatiDiPartitoDiversi() {
    	ObservableList<RowSchedaCandidatoCategorico> lista = tabellaElettorale.getItems();
    	for(int i = 0; i < lista.size(); i++) {
    		for(int j = 0; j < lista.size(); j++) {
    			if(i != j &&
    					((lista.get(i).getCandidatoDiPartito1() != null && lista.get(i).getCandidatoDiPartito1().isSelected()) || (lista.get(i).getCandidatoDiPartito2() != null && lista.get(i).getCandidatoDiPartito2().isSelected()) || (lista.get(i).getCandidatoDiPartito3() != null && lista.get(i).getCandidatoDiPartito3().isSelected()) || (lista.get(i).getCandidatoDiPartito4() != null && lista.get(i).getCandidatoDiPartito4().isSelected())) &&
    					((lista.get(j).getCandidatoDiPartito1() != null && lista.get(j).getCandidatoDiPartito1().isSelected()) || (lista.get(j).getCandidatoDiPartito2() != null && lista.get(j).getCandidatoDiPartito2().isSelected()) || (lista.get(j).getCandidatoDiPartito3() != null && lista.get(j).getCandidatoDiPartito3().isSelected()) || (lista.get(j).getCandidatoDiPartito4() != null && lista.get(j).getCandidatoDiPartito4().isSelected())))
    			{
    				return true;
    			}
    		}
    	}
    	return false;
    }
    
    private boolean cDPDiversiDaPartitoOCandidato() {
    	ObservableList<RowSchedaCandidatoCategorico> lista = tabellaElettorale.getItems();
    	for(int i = 0; i < lista.size(); i++) {
    		for(int j = 0; j < lista.size(); j++) {
    			if(i != j &&
    					((lista.get(i).getCandidatoDiPartito1() != null && lista.get(i).getCandidatoDiPartito1().isSelected()) || (lista.get(i).getCandidatoDiPartito2() != null && lista.get(i).getCandidatoDiPartito2().isSelected()) || (lista.get(i).getCandidatoDiPartito3() != null && lista.get(i).getCandidatoDiPartito3().isSelected()) || (lista.get(i).getCandidatoDiPartito4() != null && lista.get(i).getCandidatoDiPartito4().isSelected())) &&
    					(lista.get(j).getCandidato().isSelected() || lista.get(j).getPartito().isSelected())) {
    				return true;
    			}
    		}
    	}
    	return false;
    }
    
    private int numeroCandidatiDiPartito() {
    	ObservableList<RowSchedaCandidatoCategorico> lista = tabellaElettorale.getItems();
    	int counter = 0;
    	for(int i = 0; i < lista.size(); i++) {
    		if(lista.get(i).getCandidatoDiPartito1() != null && lista.get(i).getCandidatoDiPartito1().isSelected()) {
    			counter++;
    		}
    		if(lista.get(i).getCandidatoDiPartito2() != null && lista.get(i).getCandidatoDiPartito2().isSelected()) {
    			counter++;
    		}
    		if(lista.get(i).getCandidatoDiPartito3() != null && lista.get(i).getCandidatoDiPartito3().isSelected()) {
    			counter++;
    		}
    		if(lista.get(i).getCandidatoDiPartito4() != null && lista.get(i).getCandidatoDiPartito4().isSelected()) {
    			counter++;
    		}
    	}
    	return counter;
    }
    
    private boolean isSchedaBianca() {
    	ObservableList<RowSchedaCandidatoCategorico> lista = tabellaElettorale.getItems();
    	for(int i = 0; i < lista.size(); i++) {
    		if(lista.get(i).getCandidato().isSelected() || lista.get(i).getPartito().isSelected() || (lista.get(i).getCandidatoDiPartito1() != null && lista.get(i).getCandidatoDiPartito1().isSelected()) || (lista.get(i).getCandidatoDiPartito2() != null && lista.get(i).getCandidatoDiPartito2().isSelected()) || (lista.get(i).getCandidatoDiPartito3() != null && lista.get(i).getCandidatoDiPartito3().isSelected()) || (lista.get(i).getCandidatoDiPartito4() != null && lista.get(i).getCandidatoDiPartito4().isSelected()))
    			return false;
    	}
    	return true;
    }

    @FXML
    void handleConferma(ActionEvent event) throws IOException {
    	Elettore e = (Elettore) SessioneSingleton.getSessioneSingleton().getUser();
    	if(partitoDiversoCandidato()) {
    		votoNonValido.setText("Non puoi votare per un candidato e un partito non associati");
        	votoNonValido.setFill(Color.RED);
        	votoNonValido.setTextAlignment(TextAlignment.CENTER);
    	}else if(piuDiUnCandidato()) {
    		votoNonValido.setText("Non puoi votare per più di un candidato principale");
        	votoNonValido.setFill(Color.RED);
        	votoNonValido.setTextAlignment(TextAlignment.CENTER);
    	}else if(piuDiUnPartito()) {
    		votoNonValido.setText("Non puoi votare per più di un partito");
        	votoNonValido.setFill(Color.RED);
        	votoNonValido.setTextAlignment(TextAlignment.CENTER);
    	}else if(candidatiDiPartitoDiversi()) {
    		votoNonValido.setText("Non puoi votare per candidati di partito diversi");
        	votoNonValido.setFill(Color.RED);
        	votoNonValido.setTextAlignment(TextAlignment.CENTER);
    	}else if(cDPDiversiDaPartitoOCandidato()) {
    		votoNonValido.setText("Non puoi votare per candidati di partito non associati al partito o al candidato principale");
        	votoNonValido.setFill(Color.RED);
        	votoNonValido.setTextAlignment(TextAlignment.CENTER);
    	}else if(numeroCandidatiDiPartito() > 2 && e.hasTheCityMoreThan15KPeople()) {
    		votoNonValido.setText("Non puoi votare per più di 2 candidati di partito");
        	votoNonValido.setFill(Color.RED);
        	votoNonValido.setTextAlignment(TextAlignment.CENTER);
    	}else if(numeroCandidatiDiPartito() > 1 && !e.hasTheCityMoreThan15KPeople()) {
    		votoNonValido.setText("Non puoi votare per più di 1 candidato di partito");
        	votoNonValido.setFill(Color.RED);
        	votoNonValido.setTextAlignment(TextAlignment.CENTER);
    	}else {
    		// il voto è valido: mostrare la schermata di conferma
    		if(isSchedaBianca()) {
    			FXMLLoader next = new FXMLLoader(getClass().getResource("..//voter//confermaSchedaBiancaCategoricaWindow.fxml"));
    	    	Parent root = next.load();
    			ConfermaSchedaBiancaCategoricaController csbcc = next.getController();
    			csbcc.setId(idVotazione);
    			pane.getChildren().removeAll();
    	    	pane.getChildren().setAll(root);
    		} else {
    			String preferenze = new String();
    			String candidato = "", partito = "", cDP1 = "", cDP2 = "";
    			ObservableList<RowSchedaCandidatoCategorico> lista = tabellaElettorale.getItems();
    			for(int i = 0; i < lista.size(); i++) {
    				if(lista.get(i).getCandidato().isSelected()) {
    					preferenze += "Candidato principale: " + lista.get(i).getCandidato().getText().toString() + "\n";
    					candidato = lista.get(i).getCandidato().getText().toString();
    				}
    				if(lista.get(i).getPartito().isSelected()) {
    					preferenze += "Partito: " + lista.get(i).getPartito().getText().toString() + "\n";
    					partito = lista.get(i).getPartito().getText().toString();
    				}
    				if(lista.get(i).getCandidatoDiPartito1() != null && lista.get(i).getCandidatoDiPartito1().isSelected()) {
    					preferenze += "Candidato di partito: " + lista.get(i).getCandidatoDiPartito1().getText().toString() + "\n";
    					cDP1 = lista.get(i).getCandidatoDiPartito1().getText().toString();
    				}
    				if(lista.get(i).getCandidatoDiPartito2() != null && lista.get(i).getCandidatoDiPartito2().isSelected()) {
    					preferenze += "Candidato di partito: " + lista.get(i).getCandidatoDiPartito2().getText().toString() + "\n";
    					if(cDP1.isEmpty()) cDP1 = lista.get(i).getCandidatoDiPartito2().getText().toString();
    					else cDP2 = lista.get(i).getCandidatoDiPartito2().getText().toString();
    				}
    				if(lista.get(i).getCandidatoDiPartito3() != null && lista.get(i).getCandidatoDiPartito3().isSelected()) {
    					preferenze += "Candidato di partito: " + lista.get(i).getCandidatoDiPartito3().getText().toString() + "\n";
    					if(cDP1.isEmpty()) cDP1 = lista.get(i).getCandidatoDiPartito3().getText().toString();
    					else cDP2 = lista.get(i).getCandidatoDiPartito3().getText().toString();
    				}
    				if(lista.get(i).getCandidatoDiPartito4() != null && lista.get(i).getCandidatoDiPartito4().isSelected()) {
    					preferenze += "Candidato di partito: " + lista.get(i).getCandidatoDiPartito4().getText().toString() + "\n";
    					if(cDP1.isEmpty()) cDP1 = lista.get(i).getCandidatoDiPartito4().getText().toString();
    					else cDP2 = lista.get(i).getCandidatoDiPartito4().getText().toString();
    				}
    			}
    			
    			FXMLLoader next = new FXMLLoader(getClass().getResource("..//voter//confermaVotazioneCategoricaWindow.fxml"));
    	    	Parent root = next.load();
    	    	ConfermaVotazioneCategoricaController cvcc = next.getController();
    	    	cvcc.setText(idVotazione, preferenze, candidato, partito, cDP1, cDP2);
    			pane.getChildren().removeAll();
    	    	pane.getChildren().setAll(root);
    		}
    	}
    }

    @FXML
    void handleSchedaBianca(ActionEvent event) throws IOException {
    	FXMLLoader next = new FXMLLoader(getClass().getResource("..//voter//confermaSchedaBiancaCategoricaWindow.fxml"));
    	Parent root = next.load();
		ConfermaSchedaBiancaCategoricaController csbcc = next.getController();
		csbcc.setId(idVotazione);
		pane.getChildren().removeAll();
    	pane.getChildren().setAll(root);
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
