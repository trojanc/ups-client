package za.co.trojanc.ups.gui;

import java.awt.SystemTray;
import java.awt.TrayIcon;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import za.co.trojanc.ups.gui.service.UpsService;

public class MainApp extends Application {

	private TrayIcon trayIcon;
	SystemTray tray;
	private Stage stage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;

		UpsService.get().init();
		Platform.setImplicitExit(false);

		stage.setTitle("UPS GUI");

		showStartScene();
		primaryStage.show();

	}

	private void showStartScene(){
		Scene statusScene = UpsScenes.getScene(UpsScenes.SCENE_STATUS_TABLE);
		stage.setScene(statusScene);
		//stage.setScene(scene);

	}

	/**
	 * The main() method is ignored in correctly deployed JavaFX application.
	 * main() serves only as fallback in case the application can not be
	 * launched through deployment artifacts, e.g., in IDEs with limited FX
	 * support. NetBeans ignores main().
	 *
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
