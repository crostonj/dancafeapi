package com.danscafe.siteapi.model;

import java.io.Serializable;

public class JwtRequest extends UserEntity implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;

	//need default constructor for JSON Parsing
	public JwtRequest()
	{
	}

    public JwtRequest(String username, String password) {
		this.setUsername(username);
        this.setPassword(password);
    }

}
