package sweng.project.evoting;

import java.util.Objects;

public class Utente {
	private String username;
	private String password;
	private String tipo;
	
	public Utente(String username, String password, String tipo) {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);
		Objects.requireNonNull(tipo);
		
		this.username = username;
		this.password = password;
		this.tipo = tipo;
	}
	
	/* 
     * Effects: consente all'utente di effettuare la fase di identificazione 
    */
	public boolean login(String username, String password, String tipo) {
		return false;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getTipo() {
		return this.tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	@Override
    public String toString() {
    	return username + ", " + tipo;
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
