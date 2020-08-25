package com.danscafe.siteapi.controller;

import com.danscafe.siteapi.config.JwtTokenUtil;
import com.danscafe.siteapi.model.JwtRequest;
import com.danscafe.siteapi.model.JwtResponse;
import com.danscafe.siteapi.model.User;
import com.danscafe.siteapi.repository.UserRepository;
import com.danscafe.siteapi.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/auth")
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

	@Autowired
	UserRepository users;

	@Autowired
	private CustomUserDetailsService userService;

    @PostMapping(value = "/authenticate")
	@CrossOrigin
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userService
				.loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @CrossOrigin
	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	@PostMapping("/register")
	@CrossOrigin
	public ResponseEntity register(@RequestBody User user) {
		Optional<User> userExists = userService.findUser(user.getUsername());
		if (userExists.isPresent()) {
			throw new BadCredentialsException("User with username: " + user.getUsername() + " already exists");
		}
		userService.saveUser(user);
		Map<Object, Object> model = new HashMap<>();
		model.put("message", "User registered successfully");
		return ok(model);
	}

}
