package net.idea.restnet.aa.opensso;

import java.io.Serializable;

import org.opentox.aa.IOpenToxUser;
import org.restlet.security.User;

public class OpenSSOUser extends User implements IOpenToxUser, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1578876344936555656L;
	protected String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String getPassword() {
		return getSecret().toString();
	}

	@Override
	public String getUsername() {
		return getIdentifier();
	}

	@Override
	public void setPassword(String secret) {
		setSecret(secret.toCharArray());
	}

	@Override
	public void setUserName(String name) {
		super.setIdentifier(name);

	}

}
