package com.chrono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.chrono.domain.activity.Activity;
import com.chrono.domain.activity.ActivityStatus;
import com.chrono.domain.project.Project;
import com.chrono.domain.project.ProjectPriority;
import com.chrono.domain.project.ProjectStatus;
import com.chrono.domain.releasetime.ReleaseTime;
import com.chrono.domain.user.User;
import com.chrono.domain.user.UserRole;
import com.chrono.repository.ActivityRepository;
import com.chrono.repository.ProjectRepository;
import com.chrono.repository.ReleaseTimeRepository;
import com.chrono.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class ChronoApplication implements CommandLineRunner {

	private final UserRepository usuarioRepository;
	private final ProjectRepository projectRepository;
	private final ActivityRepository activityRepository;
	private final ReleaseTimeRepository releaseTimeRepository;

	public static void main(String[] args) {
		SpringApplication.run(ChronoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Users creation
		User usuario1 = new User(
			"Rodrigo Quisen",
			"rodrigoquisen@wise.com",
			"wise@system",
			UserRole.ADMIN
		);

		User usuario2 = new User(
			"Maria Silva",
			"maria.silva@gmail.com",
			"password123",
			UserRole.ADMIN
		);

		User usuario3 = new User(
			"João Souza",
			"joao.souza@gmail.com",
			"password456",
			UserRole.USER
		);

		User usuario4 = new User(
			"Carla Pereira",
			"carla.pereira@gmail.com",
			"password789",
			UserRole.USER
		);

		List<User> usuarios = Arrays.asList(
			usuario1, usuario2, usuario3, usuario4
		);
		
		usuarioRepository.saveAll(usuarios);

		// Projects creation
		Project projeto1 = new Project(
			"Aplicação web para bancos",
			"Aplicação bancária para controle financeiro",
			LocalDate.of(2023, 12, 8),
			LocalDate.of(2024, 3, 23),
			ProjectStatus.EM_ANDAMENTO,
			usuario1, 
			ProjectPriority.ALTA
		);

		Project projeto2 = new Project(
			"Aplicação de e-commerce",
			"Plataforma de vendas online",
			LocalDate.of(2023, 11, 1),
			LocalDate.of(2024, 2, 15),
			ProjectStatus.EM_ANDAMENTO,
			usuario2, 
			ProjectPriority.MEDIA
		);

		Project projeto3 = new Project(
			"Aplicação de gestão de projetos",
			"Ferramenta para gerenciamento de projetos",
			LocalDate.of(2023, 10, 5),
			LocalDate.of(2024, 1, 20),
			ProjectStatus.EM_ANDAMENTO,
			usuario2, 
			ProjectPriority.BAIXA
		);

		Project projeto4 = new Project(
			"Aplicação de rede social",
			"Plataforma para interação social",
			LocalDate.of(2023, 9, 10),
			LocalDate.of(2024, 1, 5),
			ProjectStatus.EM_ANDAMENTO,
			usuario1, 
			ProjectPriority.ALTA
		);

		List<Project> projetos = Arrays.asList(
			projeto1, projeto2, projeto3, projeto4
		);
		
		projectRepository.saveAll(projetos);

		// Activities creation
		Activity atividade1 = new Activity(
			projeto1,
			"Implementar configurações do usuario",
			"Fazer do zero tudo que precisa quanto ao usuario...",
			LocalDate.of(2023, 8, 13),
			LocalDate.of(2023, 9, 13), 
			ActivityStatus.EM_ANDAMENTO,
			usuario1
		);

		Activity atividade2 = new Activity(
			projeto2,
			"Desenvolver módulo de pagamento",
			"Implementar integração com gateways de pagamento",
			LocalDate.of(2023, 8, 20),
			LocalDate.of(2023, 9, 20), 
			ActivityStatus.EM_ANDAMENTO,
			usuario3
		);

		Activity atividade3 = new Activity(
			projeto3,
			"Criar dashboard de projetos",
			"Desenvolver painel de controle para visualização de projetos",
			LocalDate.of(2025, 8, 25),
			LocalDate.of(2025, 9, 25), 
			ActivityStatus.EM_ANDAMENTO,
			usuario3
		);

		Activity atividade4 = new Activity(
			projeto4,
			"Implementar feed de notícias",
			"Desenvolver feed para exibição de postagens dos usuários",
			LocalDate.of(2023, 8, 30),
			LocalDate.of(2023, 9, 30), 
			ActivityStatus.EM_ANDAMENTO,
			usuario4
		);

		List<Activity> atividades = Arrays.asList(
			atividade1, atividade2, atividade3, atividade4
		);

		activityRepository.saveAll(atividades);

		// Releases time creation
		ReleaseTime lancamentoHora1 = new ReleaseTime(
			atividade1,
			usuario1,
			"Implementei a conexão do usuario com o banco de dados.",
			LocalDateTime.of(2023, 8, 13, 9, 0),
			LocalDateTime.of(2023, 8, 13, 12, 0)
		);

		ReleaseTime lancamentoHora2 = new ReleaseTime(
			atividade1,
			usuario1,
			"Corrigi bugs na implementação do usuario.",
			LocalDateTime.of(2023, 8, 14, 14, 0),
			LocalDateTime.of(2023, 8, 14, 18, 0)
		);

		ReleaseTime lancamentoHora3 = new ReleaseTime(
			atividade2,
			usuario2,
			"Desenvolvi a integração com o gateway de pagamento.",
			LocalDateTime.of(2023, 8, 20, 10, 0),
			LocalDateTime.of(2023, 8, 20, 15, 0)
		);

		ReleaseTime lancamentoHora4 = new ReleaseTime(
			atividade3,
			usuario3,
			"Criei o layout do dashboard de projetos.",
			LocalDateTime.of(2023, 8, 25, 11, 0),
			LocalDateTime.of(2023, 8, 25, 13, 0)
		);

		ReleaseTime lancamentoHora5 = new ReleaseTime(
			atividade4,
			usuario4,
			"Implementei o feed de notícias.",
			LocalDateTime.of(2023, 8, 30, 9, 0),
			LocalDateTime.of(2023, 8, 30, 12, 0)
		);
		
		List<ReleaseTime> lancamentosHora = Arrays.asList(
			lancamentoHora1, lancamentoHora2, lancamentoHora3, lancamentoHora4, lancamentoHora5
		);

		releaseTimeRepository.saveAll(lancamentosHora);
	}
}