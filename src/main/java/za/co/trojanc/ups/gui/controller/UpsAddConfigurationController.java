/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.trojanc.ups.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author charl
 */
public class UpsAddConfigurationController implements Initializable {
	@FXML
	private BorderPane root;
	@FXML
	private TextField upsHost;
	@FXML
	private TextField upsPort;
	@FXML
	private TextField upsName;
	@FXML
	private TextField upsUsername;
	@FXML
	private TextField upsPassword;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}    

}
