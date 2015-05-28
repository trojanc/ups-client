/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.trojanc.ups.gui;

import java.util.List;
import org.junit.Test;
import org.networkupstools.jnut.*;

import za.co.trojanc.ups.gui.service.UpsService;

/**
 *
 * @author charl
 */
public class TestUps {

	@Test
	public void testGetStatus() throws Exception{
		UpsService.get().init();
		Device device = UpsService.get().getClient(0);
		System.out.println(device.getDescription());

		Command[] commands = device.getCommandList();
		System.out.println("--------------------------------------------");
		for(Command command: commands){
			System.out.println(command.getName());
		}


		System.out.println("--------------------------------------------");
		Variable[] variables = device.getVariableList();
		for(Variable variable: variables){
			System.out.println(variable.getName() + "=" + variable.getValue() + " | " + variable.getDescription());
		}


		System.out.println("--------------------------------------------");
		variables = device.getRWVariableList();
		for(Variable variable: variables){
			System.out.println(variable.getName() + "=" + variable.getValue() + " | " + variable.getDescription());
		}

	}

}
