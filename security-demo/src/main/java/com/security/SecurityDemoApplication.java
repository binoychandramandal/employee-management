package com.security;

import com.security.entity.Role;
import com.security.entity.User;
import com.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SecurityDemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SecurityDemoApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public void run(String... args) throws Exception {
		//userRepository.save(new User(1l,"dipen",passwordEncoder.encode("dipen"), Role.ROLE_ADMIN));
		//userRepository.save(new User(2l,"rabin",passwordEncoder.encode("rabin"), Role.ROLE_USER));
		//userRepository.save(new User(3l,"krishna","krishna", Role.ROLE_ADMIN));
	}
}
