package sweng.project.evoting;

import java.util.Objects;

public class Utente {
	private String name;
	private String surname;
	private String password;
	private String tipo;
	
	public Utente(String name, String surname, String password, String tipo) {
		Objects.requireNonNull(name);
		Objects.requireNonNull(surname);
		Objects.requireNonNull(password);
		Objects.requireNonNull(tipo);
		
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.tipo = tipo;
	}
	
	/* 
     * Effects: consente all'utente di effettuare la fase di identificazione 
    */
	public boolean login(String username, String password, String tipo) {
		return false;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getSurname() {
		return this.surname;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getTipo() {
		return this.tipo;
	}
}
