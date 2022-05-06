package sweng.project.evoting;

/*
 * OVERVIEW: questa classe istanzia un oggetto scrutatore per un sistema di voto elettronico, che rappresenta l'impiegato 
 * che gestisce la configurazione del sistema per permettere la votazione. 
 */
public class Amministratore extends Utente {
	private String name, surname;
	
    /*
     * Effects: istanzia this affinché rappresenti un amministratore
    */
    public Amministratore(String name, String surname, String username, String password){
    	super(username, password, "Amministratore");
    	
    	if(name.isEmpty() || name.isBlank() || surname.isEmpty() || surname.isBlank())
    		throw new IllegalArgumentException("Il nome e il cognome dell'amministrazione devono essere indicati");
    	this.name = name;
    	this.surname = surname;
    }
    
    public String getName() {
    	return this.name;
    }
    
    public String getSurname() {
    	return this.surname;
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
    	return String.format("Amministratore: %s %s", name, surname);
    }

    @Override
    public int hashCode() {
    	int result = super.hashCode();
    	result = 31 * result + name.hashCode();
    	result = 31 * result + surname.hashCode();
    	return result;
    }

    @Override
    public boolean equals(Object obj) {
    	if(obj instanceof Amministratore) {
    		Amministratore a = (Amministratore) obj;
    		return a.name.equals(name) && a.surname.equals(surname) && a.getUsername().equals(this.getUsername()) && a.getPassword().equals(this.getPassword());
    	}
    	return false;
    }
    
}
