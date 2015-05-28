/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.trojanc.ups.gui.entity;

/**
 *
 * @author charl
 */
public class UPSConfig {

	/**
	 * Host to which connect.
	 * Network name or IP.
	 * Default to "127.0.0.1"
	 */
	private String host = "127.0.0.1";

	/**
	 * IP port.
	 * Default to 3493
	 */
	private int    port = 3493;

	/**
	 * Login to use to connect to UPSD.
	 */
	private String login = null;

	/**
	 * Password to use to connect to UPSD.
	 */
	private String passwd = null;

	/**
	 * Name of the UPS on the client
	 */
	private String upsName;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getUpsName() {
		return upsName;
	}

	public void setUpsName(String upsName) {
		this.upsName = upsName;
	}



}
