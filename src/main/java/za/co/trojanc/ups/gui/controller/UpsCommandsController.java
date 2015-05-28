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
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import za.co.trojanc.ups.gui.service.UpsService;

import org.networkupstools.jnut.Client;
import org.networkupstools.jnut.Command;
import org.networkupstools.jnut.Device;
import org.networkupstools.jnut.NutException;
import org.networkupstools.jnut.Variable;

/**
 * FXML Controller class
 *
 * @author charl
 */
public class UpsCommandsController implements Initializable {
	@FXML
	private TableView statusTable;
	@FXML
	private TableColumn columnCommand;
	@FXML
	private TableColumn columnDescription;
	@FXML
	private TableColumn<Command, Boolean> columnExecute;

	@FXML
	private Button btnCommands;

	@FXML
	private Parent root;

	public Parent getRoot() {
		return root;
	}

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		Client client;
		try {
			Device device = UpsService.get().getClient(0);


			columnCommand.setCellValueFactory(new PropertyValueFactory<Command, String>("name"));
			columnDescription.setCellValueFactory(new PropertyValueFactory<Command, String>("description"));

			ObservableList<Command> data = FXCollections.observableArrayList(device.getCommandList());
			statusTable.setItems(data);
		} catch (IOException ex) {
			Logger.getLogger(UpsCommandsController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (NutException ex) {
			Logger.getLogger(UpsCommandsController.class.getName()).log(Level.SEVERE, null, ex);
		}


		columnExecute.setSortable(false);

		columnExecute.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Command, Boolean>, ObservableValue<Boolean>>() {
			@Override public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Command, Boolean> features) {
				return new SimpleBooleanProperty(features.getValue() != null);
			}
		});

		// create a cell value factory with an add button for each row in the table.
		columnExecute.setCellFactory(new Callback<TableColumn<Command, Boolean>, TableCell<Command, Boolean>>() {
			@Override public TableCell<Command, Boolean> call(TableColumn<Command, Boolean> personBooleanTableColumn) {
				return new AddPersonCell();
			}
		});
		//                new Callback<TableColumn.CellDataFeatures<Command, Boolean>, ObservableValue<Boolean>>() {
		//            
		//            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Command, Boolean> p) {
		//                return new SimpleBooleanProperty(p.getValue() != null);
		//            }
		//        });

	}    

	/** A table cell containing a button for adding a new person. */
	private class AddPersonCell extends TableCell<Command, Boolean> {
		// a button for adding a new person.
		final Button addButton       = new Button("Execute");
		// pads and centers the add button in the cell.
		final StackPane paddedButton = new StackPane();
		// records the y pos of the last button press so that the add person dialog can be shown next to the cell.
		final DoubleProperty buttonY = new SimpleDoubleProperty();

		/**
		 * AddPersonCell constructor
		 * @param stage the stage in which the table is placed.
		 * @param table the table to which a new person can be added.
		 */
		AddPersonCell() {
			paddedButton.getChildren().add(addButton);
			addButton.setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override public void handle(MouseEvent mouseEvent) {
					buttonY.set(mouseEvent.getScreenY());
				}
			});
			addButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent actionEvent) {

				}
			});
		}

		/** places an add button in the row only if the row is not empty. */
		@Override protected void updateItem(Boolean item, boolean empty) {
			super.updateItem(item, empty);
			if (!empty) {
				setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
				setGraphic(paddedButton);
			} else {
				setGraphic(null);
			}
		}
	}

}
