package sweng.project.evoting;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    static final String url = "jdbc:postgresql://localhost/sweng";
    static final String user = "postgres";
    static final String password = "postgres";

    /*
     * metodo che permette la connessione al database PostgreSQL
     *
     * @return un oggetto Connection
     */
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
          } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
    
    /*
     * Metodo per criptare la password passata come argomento.
     * L'algoritmo scelto è l'algoritmo Secure Hash Algorithm 1
     */
    public static String encoding (String password) {
    		try {
	    		MessageDigest md = MessageDigest.getInstance("SHA-1");
	    		md.update(password.getBytes()) ;
	    		byte byteData[] = md.digest();
	    		StringBuilder sb = new StringBuilder();
	    		for (int i=0; i<byteData.length; i++) {
	    			sb.append(Integer.toString((byteData[i] & 0xFf) + 0x100, 16).substring (1));	
	    		}
	    		
	    		return sb.toString();
	    		
    	    } catch (NoSuchAlgorithmException ex) {
	    		Logger.getLogger("SHA-1").log(Level.SEVERE, null, ex) ;
	    		return null;
    	    }  
    }
}
