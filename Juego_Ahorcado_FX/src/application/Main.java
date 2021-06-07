package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		
	
		
		FXMLLoader fxmlLoader = new FXMLLoader(InterfaceController.class.getResource("interface.fxml"));

		BorderPane root = fxmlLoader.load();

		// Podemos obtener una referencia al controlador por si necesitamos pasarle algo
		// ejecutando algún método suyo desde aquí.
		final InterfaceController controlador = fxmlLoader.getController();


		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("El Juego del Ahorcado");
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
