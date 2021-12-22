package sweng.project.evoting;

import java.util.GregorianCalendar;

/*
 * OVERVIEW: questa classe istanzia un oggetto di tipo Elettore che fa parte di un sistema di voto elettronico.
 * Esso rappresenta il cittadino elettore che deve votare.
 */
public class Elettore extends Utente {
	private String name, surname;
	/*
	 * Attributi che rappresentano giorno, mese e anno dell'elettore
	*/
	private int birthDay, birthMonth, birthYear;
    /*
     * L'attributo booleano hasVoted indica se l'elettore ha gi� votato (true) oppure no (false),
     * city15K indica se l'elettore vive in un comune con pi� di 15.000 abitanti (true) o no (false),
     * ofAge indica se l'elettore � maggiorenne (true) oppure no (false),
     * birthDateValid indica se la data di nascita � una data valida.
    */
    private boolean birthDateValid, ofAge, hasVoted, city15K;
    /*
	 * Attributi che rappresentano il paese e la citt� di nascita dell'elettore
	*/
    private String birthPlace, countryOfBirth;
    /*
	 * Attributi che rappresentano il genere e il codice fiscale dell'elettore
	*/
    private char[] taxCode;
    private char gender;

    /*
     * Effects: inizializza this affinch� rappresenti un elettore che � identificato da nome, cognome 
     * 			e codice della carta d'identit� passati come parametro al costruttore stesso.
     * 			Se name, surname e/o documentID sono null viene sollevata un'eccezione di tipo NullPointerException.
    */
    
    public Elettore(String name, String surname, String username, String password, char gender, int birthDay, int birthMonth, int birthYear, String countryOfBirth, String birthPlace, String codiceFiscale){
		super(username, password, "Elettore");
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.birthDay = birthDay;
		this.birthMonth = birthMonth;
		this.birthYear = birthYear;
		this.birthDateValid = this.isDateOfBirthValid();
		this.ofAge = this.isOfAge();
		this.hasVoted = false;
		this.countryOfBirth = countryOfBirth;
		this.birthPlace = birthPlace;
		this.taxCode = codiceFiscale.toCharArray();
	}
    
    public String getName() {
    	return this.name;
    }
    
    public String getSurname() {
    	return this.surname;
    }
    
    /* Effects: restituisce true se la data di nascita � valida (ovvero non successiva alla data corrente),
	 * 			false altrimenti.
	 */
    public boolean isDateOfBirthValid(){
		GregorianCalendar birthDate = new GregorianCalendar(birthYear, birthMonth - 1, birthDay);
        GregorianCalendar today = new GregorianCalendar();
        return birthDate.before(today);
	}
    
    /* Effects: restituisce true se l'elettore � maggiorenne (ovvero ha 18 o pi� anni),
	 * 			false altrimenti.
	 */
	public boolean isOfAge(){
		GregorianCalendar birthDate = new GregorianCalendar(birthYear + 18, birthMonth - 1, birthDay);
        GregorianCalendar today = new GregorianCalendar();
        return birthDate.before(today);
	}

    /*
     * Effects: permette all'elettore di votare
    */
    public void esprimi_voto(){
    	hasVoted = true;
    }

    /*
     * Effects: restituisce true se l'elettore ha gi� effettuato la votazione,
     *          false altrimenti.
    */
    public boolean checkAlreadyVoted(){
    	return false;
    }
    
    @Override
    public String toString() {
    	return "";
    }

    @Override
    public int hashCode() {
    	return 0;
    }

    @Override
    public boolean equals(Object obj) {
    	return false;
    }
}
