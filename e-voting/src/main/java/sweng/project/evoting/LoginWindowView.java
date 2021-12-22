package sweng.project.evoting;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class LoginWindowView extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("loginWindow.fxml"));
		
		primaryStage.setTitle("Votazione Elettronica - Login");
		primaryStage.setScene(new Scene(root, 600, 400));
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static void run() {
		launch();
	}
	
	/* 
	 * Effects: restituisce true se l'autenticazione mediante la finestra di login è andata a buon fine;
	 * 			false altrimenti.
	 */
	public static boolean authenticate(String user, String psw, String type) {
		return new DigitalVotingDaoImpl().isValid(user, App.encoding(psw), type);	// cripta la password inserita prima di sostituirla nella query
	}

}
