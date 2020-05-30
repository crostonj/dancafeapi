package com.danscafe.siteapi.model;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class JwtRequest extends UserEntity implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;

	//need default constructor for JSON Parsing

	public JwtRequest() {

	}
}
