/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.trojanc.ups.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import za.co.trojanc.ups.gui.service.UpsService;

import org.networkupstools.jnut.Client;
import org.networkupstools.jnut.Device;
import org.networkupstools.jnut.NutException;
import org.networkupstools.jnut.Variable;

/**
 * FXML Controller class
 *
 * @author charl
 */
public class StatusTableController implements Initializable {

	@FXML
	private TableView statusTable;

	@FXML
	private Parent root;

	@FXML
	private TableColumn columnProperty;


	@FXML
	private TableColumn columnValue;

	@FXML // included component  fx:id="includedComponent1"
	private Node upsToolbar;

	@FXML
	private UpsToolbarController upsToolbarController; 

	public Parent getRoot() {
		return root;
	}


	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			Device device = UpsService.get().getClient(0);

			columnProperty.setCellValueFactory(new PropertyValueFactory<Variable, String>("description"));
			columnValue.setCellValueFactory(new PropertyValueFactory<Variable, String>("value"));

			ObservableList<Variable> data = FXCollections.observableArrayList(device.getVariableList());
			statusTable.setItems(data);
			System.out.println(upsToolbarController.getClass());
		} catch (IOException ex) {
			Logger.getLogger(StatusTableController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (NutException ex) {
			Logger.getLogger(StatusTableController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}    

}
