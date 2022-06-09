package sweng.project.evoting;

import java.util.GregorianCalendar;
import java.util.Objects;

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
     * city15K indica se l'elettore vive in un comune con pi� di 15.000 abitanti (true) o no (false)
    */
    private boolean city15K;
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
    
    public Elettore(String username, String password){
		super(username, password, "Elettore");
		
		String[] info = new DigitalVotingDaoImpl().getVoterInfo(username, password);
		
		this.name = Objects.requireNonNull(info[0]);
		this.surname = Objects.requireNonNull(info[1]);
		this.gender = Character.valueOf(info[2].charAt(0));
		
		int birthDay, birthMonth, birthYear;
		String[] nascita = info[3].split("-");
		birthDay = Integer.parseInt(nascita[2]);
		birthMonth = Integer.parseInt(nascita[1]);
		birthYear = Integer.parseInt(nascita[0]);
		
		if(birthDay <= 0 || birthDay > 31 || birthMonth <= 0 || birthMonth > 12 || birthYear <= 0 || !isDateOfBirthValid())
			throw new IllegalArgumentException("Data di nascita non valida");
		
		switch(birthMonth) {
			case 11:
			case 4:
			case 6:
			case 9:
				if(birthDay == 31) throw new IllegalArgumentException("Data di nascita non valida");
				break;
			case 2:
				if((!isLeapYear(birthYear) && birthDay > 28) || (isLeapYear(birthYear) && birthDay > 29))
					throw new IllegalArgumentException("Data di nascita non valida");
				break;
			default:
				break;
		}
		
		this.birthDay = birthDay;
		this.birthMonth = birthMonth;
		this.birthYear = birthYear;
		
		if(Objects.requireNonNull(info[4]).isEmpty() || Objects.requireNonNull(info[4]).isBlank())
			throw new IllegalArgumentException("Paese di nascita non indicato");
		if(Objects.requireNonNull(info[5]).isEmpty() || Objects.requireNonNull(info[5]).isBlank())
			throw new IllegalArgumentException("Nazione di nascita non indicata");
		
		this.countryOfBirth = info[5];
		this.birthPlace = info[4];
		this.city15K = (info[7].equalsIgnoreCase("false")) ? false : true;
		
		if(Objects.requireNonNull(info[6]).length() != 16)
			throw new IllegalArgumentException("Codice fiscale non valido");
		this.taxCode = info[6].toCharArray();
	}
    
    private boolean isLeapYear(int anno) {
    	if(anno % 4 == 0) {
    		if(anno % 100 == 0 && anno % 400 != 0) {
    			return false;
    		}
    		return true;
    	}
    	return false;
    }
    
    public String getName() {
    	return this.name;
    }
    
    public String getSurname() {
    	return this.surname;
    }
    
    public char getGender() {
    	return this.gender;
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
	
	public String getTaxCode() {
		return new String(taxCode);
	}
	
	public String getBirthDate() {
		return String.format("%d/%d/%d", birthDay, birthMonth, birthYear);
	}
	
	public String getLuogoDiNascita() {
		return String.format("%s, %s", birthPlace, countryOfBirth);
	}
	
	public boolean hasTheCityMoreThan15KPeople() {
		return city15K;
	}

    /*
     * Effects: permette all'elettore di votare per la votazione avente lo stesso id passato come argomento
    */
    public void esprimi_voto(final String idVotazione){
    	if(idVotazione == null || idVotazione.isEmpty() || idVotazione.isBlank()) throw new IllegalArgumentException("ID della votazione non valido");
    	
    	if(isOfAge()) {
    		new DigitalVotingDaoImpl().insertVotanteReferendum(idVotazione, getTaxCode(), getUsername());
    	} else {
    		throw new IllegalArgumentException("L'elettore non può votare in quanto non maggiorenne");
    	}
    }

    /*
     * Effects: restituisce true se l'elettore ha gi� effettuato la votazione avente lo stesso id passato come argomento,
     *          false altrimenti.
    */
    public boolean checkAlreadyVoted(final String idVotazione){
    	if(idVotazione == null || idVotazione.isEmpty() || idVotazione.isBlank())
    		throw new IllegalArgumentException("ID della votazione non valido");
    	
    	return new DigitalVotingDaoImpl().hasAlreadyVoted(idVotazione, getTaxCode(), getUsername());
    }
    
    @Override
    public String toString() {
    	return String.format("%s %s, %s, elettore", name, surname, new String(taxCode));
    }

    @Override
    public int hashCode() {
    	int result = super.hashCode();
    	result = 31 * result + name.hashCode();
    	result = 31 * result + surname.hashCode();
    	result = 31 * result + Character.hashCode(gender);
    	result = 31 * result + countryOfBirth.hashCode();
    	result = 31 * result + birthPlace.hashCode();
    	result = 31 * result + String.format("%d/%d/%d", birthDay, birthMonth, birthYear).hashCode();
    	return 31 * result + new String(taxCode).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
    	if(obj instanceof Elettore) {
    		Elettore e = (Elettore) obj;
    		return e.name.equals(this.name) && e.surname.equals(this.surname) && new String(e.taxCode).equals(new String(this.taxCode))
    				&& e.gender == this.gender && e.countryOfBirth.equals(this.countryOfBirth) && e.birthPlace.equals(this.birthPlace) &&
    				e.birthDay == this.birthDay && e.birthMonth == this.birthMonth && e.birthYear == this.birthYear && e.city15K == this.city15K;
    	}
    	return false;
    }
}
