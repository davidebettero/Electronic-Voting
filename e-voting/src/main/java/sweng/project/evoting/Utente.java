package sweng.project.evoting;

import java.util.Objects;

import sweng.project.evoting.login.LoginWindowView;

public class Utente {
	private String username;
	private String password;
	private String tipo;
	
	public Utente(String username, String password, String tipo) {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);
		Objects.requireNonNull(tipo);
		
		if(username.isEmpty() || username.isBlank())
			throw new IllegalArgumentException("Deve essere indicato lo username");
		if(password.isEmpty() || password.isBlank())
			throw new IllegalArgumentException("Deve essere indicata la password");
		if(tipo.isEmpty() || tipo.isBlank())
			throw new IllegalArgumentException("Deve essere indicato il tipo dell'utente");
		if(!tipo.equalsIgnoreCase("Elettore") && !tipo.equalsIgnoreCase("Amministratore"))
			throw new IllegalArgumentException("Il tipo dell'utente può essere solamente elettore o amministratore");
		
		this.username = username;
		this.password = password;
		this.tipo = tipo;
		
		assert repOk();
	}
	
	/* 
     * Effects: consente all'utente di effettuare la fase di identificazione 
    */
	public boolean login() {
		return LoginWindowView.authenticate(username, password, tipo);
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(final String username) {
		if(username == null || username.isEmpty() || username.isBlank())
			throw new IllegalArgumentException("Deve essere indicato lo username");
		
		this.username = username;
		assert repOk();
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		if(password == null || password.isEmpty() || password.isBlank())
			throw new IllegalArgumentException("Deve essere indicata la password");
		
		this.password = password;
		assert repOk();
	}
	
	public String getTipo() {
		return this.tipo;
	}
	
	public void setTipo(String tipo) {
		if(!tipo.equalsIgnoreCase("Elettore") && !tipo.equalsIgnoreCase("Amministratore"))
			throw new IllegalArgumentException("Il tipo dell'utente può essere solamente elettore o amministratore");
		
		this.tipo = tipo;
		assert repOk();
	}
	
	private boolean repOk() {
		return !username.isEmpty() && !username.isBlank() && !password.isEmpty() && !password.isBlank() && (tipo.equalsIgnoreCase("Elettore") || tipo.equalsIgnoreCase("Amministratore"));
	}
	
	@Override
    public String toString() {
		return String.format("%s, %s", username, tipo);
    }

    @Override
    public int hashCode() {
    	int result = username.hashCode();
    	result = 31 * result + password.hashCode();
    	return 31 * result + tipo.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
    	if(obj instanceof Utente) {
    		Utente u = (Utente) obj;
    		return u.username.equals(this.username) && u.password.equals(this.password) && u.tipo.equals(this.tipo);
    	}
    	return false;
    }
}
