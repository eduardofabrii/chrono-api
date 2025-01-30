package com.chrono;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.chrono.domain.user.User;
import com.chrono.domain.user.UserRole;
import com.chrono.repository.UserRepository;

@SpringBootApplication
public class ChronoApplication implements CommandLineRunner {

	@Autowired
	private UserRepository usuarioRepository;

	public static void main(String[] args) {
		SpringApplication.run(ChronoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User usuario1 = new User(
			"Pedro da Silva",
			"pdrsilverio@gmail.com",
			"12345",
			UserRole.USER
		);

		User usuario2 = new User(
			"Julia Amaral da Costa",
			"jjamaral@gmail.com",
			"54321",
			UserRole.ADMIN
		);

		List<User> usuarios = Arrays.asList(
			usuario1, usuario2
		);
		
		usuarioRepository.saveAll(usuarios);
	}
}