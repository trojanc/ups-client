/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.trojanc.ups.gui.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.networkupstools.jnut.Client;
import org.networkupstools.jnut.Device;
import org.networkupstools.jnut.NutException;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import za.co.trojanc.ups.gui.entity.UPSConfig;

/**
 *
 * @author charl
 */
public class UpsService {

	private List<UPSConfig> configs = new ArrayList<>();

	private static final UpsService singleton = new UpsService();

	private UpsService(){}

	public static UpsService get(){
		return singleton;
	}

	public void init(){
		System.out.println("Home" + System.getProperty("user.home"));

		loadConfigurationFile();
	}

	private void loadConfigurationFile(){
		try{
			Path upsClientHome = Paths.get(System.getProperty("user.home"), ".ups-client");

			// If the file doesnt exist, we create one
			if(!Files.exists(upsClientHome)){
				Files.createDirectories(upsClientHome);
			}

			Path upsInstanceConfiguration = upsClientHome.resolve("instances.json");
			if(!Files.exists(upsInstanceConfiguration)){
				Files.createFile(upsInstanceConfiguration);
			}

			loadInstances(upsInstanceConfiguration);

		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}

	private void loadInstances(Path filePath){
		JSONArray instances = readJson(filePath);
		int size = instances.size();
		for(int idx = 0 ; idx < size ; idx++){
			JSONObject jo = instances.getJSONObject(0);
			UPSConfig config = (UPSConfig)JSONObject.toBean(jo, UPSConfig.class);
			configs.add(config);
		}
	}

	private void writeJson(JSON json, Path filePath){
		Charset charset = Charset.forName("UTF-8");
		String s = json.toString(4);
		try (BufferedWriter writer = Files.newBufferedWriter(filePath, charset)) {
			writer.write(s, 0, s.length());
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}
	}

	private JSONArray readJson(Path instanceConfig){
		Charset charset = Charset.forName("UTF-8");
		StringBuilder sb = new StringBuilder();
		try (BufferedReader reader = Files.newBufferedReader(instanceConfig, charset)) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}
		String data = sb.toString();
		if(data.length() <2){
			return null;
		}

		return JSONArray.fromObject(data);
	}

	public Device getClient(int upsId){
		try {UPSConfig config = configs.get(upsId);
		Client client = new Client(config.getHost(), config.getPort(), config.getLogin(), config.getPasswd());
		return client.getDevice(config.getUpsName());
		} catch (IOException | NutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void addUps(UPSConfig config){

	}
}
