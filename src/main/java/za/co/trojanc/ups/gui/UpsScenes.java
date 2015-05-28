/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.trojanc.ups.gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author charl
 */
public class UpsScenes {
	public static final String SCENE_STATUS_TABLE   = "/fxml/StatusTable.fxml";
	public static final String SCENE_COMMANDS       = "/fxml/UpsCommands.fxml";

	private static UpsScenes singleton;

	private static UpsScenes getSingleton(){
		if(singleton == null){
			singleton = new UpsScenes();
		}
		return singleton;
	}

	public static Scene getScene(String scene){
		Parent parent = null;
		try {
			parent = FXMLLoader.load(getSingleton().getClass().getResource(scene));
		} catch (IOException ex) {
			Logger.getLogger(UpsScenes.class.getName()).log(Level.SEVERE, null, ex);
		}
		return new Scene(parent);
	}

}
