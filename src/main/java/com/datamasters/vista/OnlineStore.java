package com.datamasters.vista;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class OnlineStore extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		URL resourceUrl = OnlineStore.class.getResource("/com.datamasters/vista/ManageOSFX.fxml");

		if (resourceUrl != null) {
			System.out.println("Ruta del recurso ManageOSFX.fxml: " + resourceUrl.toExternalForm());

			FXMLLoader loader = new FXMLLoader(resourceUrl);

			Scene scene = new Scene(loader.load(), 800, 600);
			primaryStage.setTitle("Hello Datamaster");
			primaryStage.setScene(scene);
			primaryStage.show();
		} else {
			System.out.println("No se encontró el recurso ManageOSFX.fxml");
		}

	}

	public static void main(String[] args) {
		launch();
	}
}
