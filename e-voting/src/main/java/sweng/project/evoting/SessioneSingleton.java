package sweng.project.evoting;

import java.util.Objects;

// classe che può essere usata in qualsiasi parte del programma come contenitore in quanto può avere solamente un'istanza
public class SessioneSingleton {
	private static SessioneSingleton sessione = null;
	private Utente user;
	
	private SessioneSingleton() {
		super();
	}
	
	public static SessioneSingleton getSessioneSingleton() {
		if(sessione == null)
			sessione = new SessioneSingleton();
		
		return sessione;
	}
	
	public void setUser(Utente user) {
		this.user = Objects.requireNonNull(user);
	}
	
	public Utente getUser() {
		return user;
	}
	
	public void logoutUser() {
		user = null;
	}
}
