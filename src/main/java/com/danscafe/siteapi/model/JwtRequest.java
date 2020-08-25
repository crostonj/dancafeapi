package com.danscafe.siteapi.model;

import java.io.Serializable;

public class JwtRequest extends User implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;

	//need default constructor for JSON Parsing

	public JwtRequest() {

	}
}
