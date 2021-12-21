package sweng.project.evoting;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class LoginWindowLaunch extends Application {
	
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

}
