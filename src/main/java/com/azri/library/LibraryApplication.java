package com.azri.library;

import com.azri.library.entity.User;
import com.azri.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryApplication implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = userRepository.findByUsername("admin").orElse(null);
		if (user == null) {
			user = new User();
			user.setUsername("admin");
			user.setPassword("admin");
			userRepository.save(user);
		}

	}
}
