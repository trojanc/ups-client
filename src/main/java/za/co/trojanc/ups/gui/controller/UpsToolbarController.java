/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.trojanc.ups.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import za.co.trojanc.ups.gui.UpsScenes;

/**
 * FXML Controller class
 *
 * @author charl
 */
public class UpsToolbarController implements Initializable {
	@FXML
	private Button btnInstances;
	@FXML
	private Button btnInformation;
	@FXML
	private Button btnCommands;
	@FXML
	private ComboBox<?> listUps;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}    

	@FXML
	private void onShowInstances(ActionEvent event) {
		// hellow
		System.out.println("Need to show instances");
		event.getSource();
	}

	@FXML
	private void onShowInformation(ActionEvent event) {
		System.out.println("Need to show information");
		Scene scene = UpsScenes.getScene(UpsScenes.SCENE_STATUS_TABLE);
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();

	}

	@FXML
	private void onShowCommands(ActionEvent event) { 
		System.out.println("Need to show commands");

		Scene scene = UpsScenes.getScene(UpsScenes.SCENE_COMMANDS);
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	private void onUpsChanged(ActionEvent event) {
	}


}
