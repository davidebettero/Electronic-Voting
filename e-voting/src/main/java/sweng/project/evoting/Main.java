package sweng.project.evoting;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("loginWindow.fxml"));
		
		primaryStage.setTitle("Login Window");
		primaryStage.setScene(new Scene(root, 400, 300));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
