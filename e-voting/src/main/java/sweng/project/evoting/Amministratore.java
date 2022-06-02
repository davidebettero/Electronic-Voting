package sweng.project.evoting;

import java.util.Objects;

/*
 * OVERVIEW: questa classe istanzia un oggetto scrutatore per un sistema di voto elettronico, che rappresenta l'impiegato 
 * che gestisce la configurazione del sistema per permettere la votazione. 
 */
public class Amministratore extends Utente {
	// attributi che rappresentano il nome, il cognome e il codice fiscale dell'amministratore
	private String name, surname;
	private char[] taxCode;
	
    /*
     * Effects: istanzia this affinché rappresenti un amministratore
    */
    public Amministratore(String username, String password){
    	super(username, password, "Amministratore");
    	
    	String[] info = new DigitalVotingDaoImpl().getAdministratorInfo(username, password);
    	
    	if(info[0] == null || info[1] == null || info[0].isEmpty() || info[0].isBlank() || info[1].isEmpty() || info[1].isBlank())
    		throw new IllegalArgumentException("Il nome e il cognome dell'amministrazione devono essere indicati");
    	this.name = info[0];
    	this.surname = info[1];
    	
    	if(Objects.requireNonNull(info[2]).length() != 16)
			throw new IllegalArgumentException("Codice fiscale non valido");
		this.taxCode = info[2].toCharArray();
    }
    
    public String getName() {
    	return this.name;
    }
    
    public String getSurname() {
    	return this.surname;
    }
    
    public String getTaxCode() {
		return new String(taxCode);
	}

    /*
     * Effects: permette allo scrutatore di settare la modalit� di voto (referendum / politiche)
    */
    public void setVotingMethod(){}

    /*
     * Effects: permette allo scrutatore di settare le modalit� di calcolo del vincitore
    */
    public void setWinnerCalculation(){}

    /*
     * Effects: permette allo scrutatore di settare la votazione nella sua completezza
    */
    public void configure(){}

    /*
     * Effects: permette allo scrutatore di terminare la votazione in corso
    */
    public void terminaVotazione(){}

    /*
     * Effects: permette allo scrutatore di visualizzare l'esito della votazione
    */
    public void visualizzaEsiti(){}
    
    @Override
    public String toString() {
    	return String.format("%s %s, %s, elettore", name, surname, new String(taxCode));
    }

    @Override
    public int hashCode() {
    	int result = super.hashCode();
    	result = 31 * result + name.hashCode();
    	result = 31 * result + surname.hashCode();
    	return 31 * result + new String(taxCode).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
    	if(obj instanceof Amministratore) {
    		Amministratore a = (Amministratore) obj;
    		return a.name.equals(this.name) && a.surname.equals(this.surname) && new String(a.taxCode).equals(new String(this.taxCode));
    	}
    	return false;
    }
    
}
