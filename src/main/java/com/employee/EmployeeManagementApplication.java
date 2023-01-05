package com.employee;

import com.employee.config.FileUploadProperties;
import com.employee.dao.RoleRepository;
import com.employee.entity.ERole;
import com.employee.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableConfigurationProperties(FileUploadProperties.class)
@CrossOrigin(origins = "http://34.71.209.127:80/*")
public class EmployeeManagementApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementApplication.class, args);
	}

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception {
		Role userRole =new Role();
		userRole.setName(ERole.ROLE_USER);
		Role adminRole =new Role();
		adminRole.setName(ERole.ROLE_ADMIN);

		Role moderateRole =new Role();
		moderateRole.setName(ERole.ROLE_MODERATOR);
		roleRepository.save(userRole);
		roleRepository.save(adminRole);
		roleRepository.save(moderateRole);

	}
}
