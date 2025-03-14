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

		// // Users creation
		// User usuario1 = new User(
		// 	"Rodrigo Quisen",
		// 	"rodrigoquisen@wise.com",
		// 	"wise@system",
		// 	UserRole.ADMIN
		// );

		// User usuario2 = new User(
		// 	"Maria Silva",
		// 	"maria.silva@gmail.com",
		// 	"wise@system",
		// 	UserRole.USER
		// );

		// User usuario3 = new User(
		// 	"João Souza",
		// 	"joao.souza@gmail.com",
		// 	"wise@system",
		// 	UserRole.USER
		// );

		// User usuario4 = new User(
		// 	"Carla Pereira",
		// 	"carla.pereira@gmail.com",
		// 	"wise@system",
		// 	UserRole.USER
		// );

		// User usuario5 = new User(
		// 	"Graziele Tieppo",
		// 	"gratieppo@gmail.com",
		// 	"wise@system",
		// 	UserRole.USER
		// );

		// User usuario6 = new User(
		// 	"Caio Lima",
		// 	"caiolima@wise.com",
		// 	"wise@system",
		// 	UserRole.ADMIN
		// );

		// User usuario7 = new User(
		// 	"Erica Almeida",
		// 	"erica.almeida@wise.com",
		// 	"wise@system",
		// 	UserRole.ADMIN
		// );

		// List<User> usuarios = Arrays.asList(
		// 	usuario1, usuario2, usuario3, usuario4, usuario5, usuario6, usuario7
		// );
		
		// usuarioRepository.saveAll(usuarios);

		// // Projects creation
		// List<Project> projetos = Arrays.asList(
		// 	new Project(
		// 		null,
		// 		"Banco Next",
		// 		"Aplicação bancária para controle financeiro",
		// 		LocalDate.of(2024, 1, 1),
		// 		LocalDate.of(2024, 6, 30),
		// 		ProjectStatus.EM_ANDAMENTO,
		// 		usuario1, // ADMIN user
		// 		null,
		// 		ProjectPriority.ALTA
		// 	),
		// 	new Project(
		// 		null,
		// 		"Mercalivre E-commerce",
		// 		"Plataforma de vendas online",
		// 		LocalDate.of(2024, 2, 1),
		// 		LocalDate.of(2024, 7, 31),
		// 		ProjectStatus.EM_ANDAMENTO,
		// 		 usuario1,
		// 		null,
		// 		ProjectPriority.MEDIA
		// 	),
		// 	new Project(
		// 		null,
		// 		"Nextime Gerenciamento de Projetos",
		// 		"Ferramenta para gerenciamento de projetos",
		// 		LocalDate.of(2024, 3, 1),
		// 		LocalDate.of(2024, 8, 31),
		// 		ProjectStatus.EM_ANDAMENTO,
		// 		 usuario7, 
		// 		null,
		// 		ProjectPriority.BAIXA
		// 	),
		// 	new Project(
		// 		null,
		// 		"Beegram",
		// 		"Plataforma para interação social",
		// 		LocalDate.of(2024, 4, 1),
		// 		LocalDate.of(2024, 9, 30),
		// 		ProjectStatus.CANCELADO,
		// 		usuario1, 
		// 		null,
		// 		ProjectPriority.ALTA
		// 	),
		// 	new Project(
		// 		null,
		// 		"Food for Save",
		// 		"Plataforma para entrega de alimentos",
		// 		LocalDate.of(2024, 5, 1),
		// 		LocalDate.of(2024, 10, 31),
		// 		ProjectStatus.EM_ANDAMENTO,
		// 		 usuario6,
		// 		null,
		// 		ProjectPriority.MEDIA
		// 	),
		// 	new Project(
		// 		null,
		// 		"Videoflix Streaming",
		// 		"Plataforma para streaming de vídeos",
		// 		LocalDate.of(2024, 6, 1),
		// 		LocalDate.of(2024, 11, 30),
		// 		ProjectStatus.EM_ANDAMENTO,
		// 		 usuario7,
		// 		null,
		// 		ProjectPriority.ALTA
		// 	),
		// 	new Project(
		// 		null,
		// 		"Muscleway Fitness",
		// 		"Plataforma para acompanhamento de exercícios",
		// 		LocalDate.of(2024, 7, 1),
		// 		LocalDate.of(2024, 12, 31),
		// 		ProjectStatus.EM_ANDAMENTO,
		// 		usuario1, // ADMIN user
		// 		null,
		// 		ProjectPriority.BAIXA
		// 	),
		// 	new Project(
		// 		null,
		// 		"Aplicação de viagens",
		// 		"Plataforma para reservas de viagens",
		// 		LocalDate.of(2024, 8, 1),
		// 		LocalDate.of(2025, 1, 31),
		// 		ProjectStatus.EM_ANDAMENTO,
		// 		 usuario6, // Changed from usuario2 (USER) to usuario6 (ADMIN)
		// 		null,
		// 		ProjectPriority.MEDIA
		// 	),
		// 	new Project(
		// 		null,
		// 		"Edulab Cursos",
		// 		"Plataforma para cursos online",
		// 		LocalDate.of(2024, 9, 1),
		// 		LocalDate.of(2025, 2, 28),
		// 		ProjectStatus.EM_ANDAMENTO,
		// 		 usuario7, // Changed from usuario3 (USER) to usuario7 (ADMIN)
		// 		null,
		// 		ProjectPriority.ALTA
		// 	),
		// 	new Project(
		// 		null,
		// 		"Sebrae Consultas",
		// 		"Plataforma para agendamento de consultas",
		// 		LocalDate.of(2024, 10, 1),
		// 		LocalDate.of(2025, 3, 31),
		// 		ProjectStatus.EM_ANDAMENTO,
		// 		 usuario6, // Changed from usuario4 (USER) to usuario6 (ADMIN)
		// 		null,
		// 		ProjectPriority.MEDIA
		// 	)
		// );
		
		// projectRepository.saveAll(projetos);

		// // Activities creation
		// List<Activity> atividades = Arrays.asList(
		// 	new Activity(
		// 		null,
		// 		projetos.get(0),
		// 		"Implementar configurações do usuario",
		// 		"Fazer do zero tudo que precisa quanto ao usuario...",
		// 		LocalDate.of(2024, 1, 10),
		// 		LocalDate.of(2024, 1, 20), 
		// 		ActivityStatus.EM_ANDAMENTO,
		// 		usuario1,
		// 		null
		// 	),
		// 	new Activity(
		// 		null,
		// 		projetos.get(0),
		// 		"Desenvolver módulo de pagamento",
		// 		"Implementar integração com gateways de pagamento",
		// 		LocalDate.of(2024, 1, 15),
		// 		LocalDate.of(2024, 1, 25), 
		// 		ActivityStatus.EM_ANDAMENTO,
		// 		usuario3,
		// 		null
		// 	),
		// 	new Activity(
		// 		null,
		// 		projetos.get(1),
		// 		"Criar dashboard de projetos",
		// 		"Desenvolver painel de controle para visualização de projetos",
		// 		LocalDate.of(2024, 2, 5),
		// 		LocalDate.of(2024, 2, 15), 
		// 		ActivityStatus.EM_ANDAMENTO,
		// 		usuario3,
		// 		null
		// 	),
		// 	new Activity(
		// 		null,
		// 		projetos.get(1),
		// 		"Implementar feed de notícias",
		// 		"Desenvolver feed para exibição de postagens dos usuários",
		// 		LocalDate.of(2024, 2, 10),
		// 		LocalDate.of(2024, 2, 20), 
		// 		ActivityStatus.EM_ANDAMENTO,
		// 		usuario4,
		// 		null
		// 	),
		// 	new Activity(
		// 		null,
		// 		projetos.get(2),
		// 		"Desenvolver módulo de autenticação",
		// 		"Implementar sistema de login e registro",
		// 		LocalDate.of(2024, 3, 10),
		// 		LocalDate.of(2024, 3, 20), 
		// 		ActivityStatus.EM_ANDAMENTO,
		// 		usuario2,
		// 		null
		// 	),
		// 	new Activity(
		// 		null,
		// 		projetos.get(2),
		// 		"Criar sistema de notificações",
		// 		"Desenvolver sistema de notificações em tempo real",
		// 		LocalDate.of(2024, 3, 15),
		// 		LocalDate.of(2024, 3, 25), 
		// 		ActivityStatus.EM_ANDAMENTO,
		// 		usuario1,
		// 		null
		// 	),
		// 	new Activity(
		// 		null,
		// 		projetos.get(3),
		// 		"Implementar sistema de mensagens",
		// 		"Desenvolver chat para comunicação entre usuários",
		// 		LocalDate.of(2024, 4, 15),
		// 		LocalDate.of(2024, 4, 25), 
		// 		ActivityStatus.EM_ANDAMENTO,
		// 		usuario3,
		// 		null
		// 	),
		// 	new Activity(
		// 		null,
		// 		projetos.get(3),
		// 		"Criar sistema de perfis",
		// 		"Desenvolver páginas de perfil dos usuários",
		// 		LocalDate.of(2024, 4, 20),
		// 		LocalDate.of(2024, 4, 30), 
		// 		ActivityStatus.EM_ANDAMENTO,
		// 		usuario4,
		// 		null
		// 	),
		// 	new Activity(
		// 		null,
		// 		projetos.get(4),
		// 		"Desenvolver módulo de pedidos",
		// 		"Implementar sistema de pedidos e pagamentos",
		// 		LocalDate.of(2024, 5, 5),
		// 		LocalDate.of(2024, 5, 15), 
		// 		ActivityStatus.EM_ANDAMENTO,
		// 		usuario1,
		// 		null
		// 	),
		// 	new Activity(
		// 		null,
		// 		projetos.get(4),
		// 		"Criar sistema de avaliações",
		// 		"Desenvolver sistema de avaliações de restaurantes",
		// 		LocalDate.of(2024, 5, 10),
		// 		LocalDate.of(2024, 5, 20), 
		// 		ActivityStatus.EM_ANDAMENTO,
		// 		usuario2,
		// 		null
		// 	),
		// 	new Activity(
		// 		null,
		// 		projetos.get(5),
		// 		"Implementar player de vídeo",
		// 		"Desenvolver player para streaming de vídeos",
		// 		LocalDate.of(2024, 6, 20),
		// 		LocalDate.of(2024, 6, 30), 
		// 		ActivityStatus.EM_ANDAMENTO,
		// 		usuario3,
		// 		null
		// 	),
		// 	new Activity(
		// 		null,
		// 		projetos.get(5),
		// 		"Criar sistema de recomendações",
		// 		"Desenvolver algoritmo de recomendações de vídeos",
		// 		LocalDate.of(2024, 6, 25),
		// 		LocalDate.of(2024, 7, 5), 
		// 		ActivityStatus.EM_ANDAMENTO,
		// 		usuario4,
		// 		null
		// 	),
		// 	new Activity(
		// 		null,
		// 		projetos.get(6),
		// 		"Desenvolver módulo de exercícios",
		// 		"Implementar sistema de acompanhamento de exercícios",
		// 		LocalDate.of(2024, 7, 25),
		// 		LocalDate.of(2024, 8, 5), 
		// 		ActivityStatus.EM_ANDAMENTO,
		// 		usuario1,
		// 		null
		// 	),
		// 	new Activity(
		// 		null,
		// 		projetos.get(6),
		// 		"Criar sistema de metas",
		// 		"Desenvolver sistema de definição de metas fitness",
		// 		LocalDate.of(2024, 7, 30),
		// 		LocalDate.of(2024, 8, 10), 
		// 		ActivityStatus.EM_ANDAMENTO,
		// 		usuario2,
		// 		null
		// 	),
		// 	new Activity(
		// 		null,
		// 		projetos.get(7),
		// 		"Implementar sistema de reservas",
		// 		"Desenvolver sistema de reservas de viagens",
		// 		LocalDate.of(2024, 8, 15),
		// 		LocalDate.of(2024, 8, 25), 
		// 		ActivityStatus.EM_ANDAMENTO,
		// 		usuario3,
		// 		null
		// 	),
		// 	new Activity(
		// 		null,
		// 		projetos.get(7),
		// 		"Criar sistema de avaliações",
		// 		"Desenvolver sistema de avaliações de hotéis",
		// 		LocalDate.of(2024, 8, 20),
		// 		LocalDate.of(2024, 8, 30), 
		// 		ActivityStatus.EM_ANDAMENTO,
		// 		usuario4,
		// 		null
		// 	),
		// 	new Activity(
		// 		null,
		// 		projetos.get(8),
		// 		"Desenvolver módulo de cursos",
		// 		"Implementar sistema de criação e gerenciamento de cursos",
		// 		LocalDate.of(2024, 9, 10),
		// 		LocalDate.of(2024, 9, 20), 
		// 		ActivityStatus.EM_ANDAMENTO,
		// 		usuario1,
		// 		null
		// 	),
		// 	new Activity(
		// 		null,
		// 		projetos.get(8),
		// 		"Criar sistema de certificados",
		// 		"Desenvolver sistema de emissão de certificados",
		// 		LocalDate.of(2024, 9, 15),
		// 		LocalDate.of(2024, 9, 25), 
		// 		ActivityStatus.EM_ANDAMENTO,
		// 		usuario2,
		// 		null
		// 	),
		// 	new Activity(
		// 		null,
		// 		projetos.get(9),
		// 		"Implementar sistema de agendamento",
		// 		"Desenvolver sistema de agendamento de consultas",
		// 		LocalDate.of(2024, 10, 5),
		// 		LocalDate.of(2024, 10, 15), 
		// 		ActivityStatus.EM_ANDAMENTO,
		// 		usuario3,
		// 		null
		// 	),
		// 	new Activity(
		// 		null,
		// 		projetos.get(9),
		// 		"Criar sistema de lembretes",
		// 		"Desenvolver sistema de lembretes para consultas",
		// 		LocalDate.of(2024, 10, 10),
		// 		LocalDate.of(2024, 10, 20), 
		// 		ActivityStatus.EM_ANDAMENTO,
		// 		usuario4,
		// 		null
		// 	)
		// );

		// activityRepository.saveAll(atividades);

		// // Releases time creation
		// List<ReleaseTime> lancamentosHora = Arrays.asList(
		// 	new ReleaseTime(
		// 		null,
		// 		atividades.get(0),
		// 		usuario1,
		// 		"Implementei a conexão do usuario com o banco de dados.",
		// 		LocalDateTime.of(2024, 1, 12, 9, 0),
		// 		LocalDateTime.of(2024, 1, 12, 12, 0),
		// 		null
		// 	),
		// 	new ReleaseTime(
		// 		null,
		// 		atividades.get(0),
		// 		usuario1,
		// 		"Corrigi bugs na implementação do usuario.",
		// 		LocalDateTime.of(2024, 1, 14, 14, 0),
		// 		LocalDateTime.of(2024, 1, 14, 18, 0),
		// 		null
		// 	),
		// 	new ReleaseTime(
		// 		null,
		// 		atividades.get(1),
		// 		usuario3,
		// 		"Desenvolvi a integração com o gateway de pagamento.",
		// 		LocalDateTime.of(2024, 1, 17, 10, 0),
		// 		LocalDateTime.of(2024, 1, 17, 15, 0),
		// 		null
		// 	),
		// 	new ReleaseTime(
		// 		null,
		// 		atividades.get(2),
		// 		usuario3,
		// 		"Criei o layout do dashboard de projetos.",
		// 		LocalDateTime.of(2024, 2, 7, 11, 0),
		// 		LocalDateTime.of(2024, 2, 7, 13, 0),
		// 		null
		// 	),
		// 	new ReleaseTime(
		// 		null,
		// 		atividades.get(3),
		// 		usuario4,
		// 		"Implementei o feed de notícias.",
		// 		LocalDateTime.of(2024, 2, 12, 9, 0),
		// 		LocalDateTime.of(2024, 2, 12, 12, 0),
		// 		null
		// 	),
		// 	new ReleaseTime(
		// 		null,
		// 		atividades.get(4),
		// 		usuario2,
		// 		"Desenvolvi o sistema de login e registro.",
		// 		LocalDateTime.of(2024, 3, 12, 10, 0),
		// 		LocalDateTime.of(2024, 3, 12, 14, 0),
		// 		null
		// 	),
		// 	new ReleaseTime(
		// 		null,
		// 		atividades.get(5),
		// 		usuario1,
		// 		"Criei o sistema de notificações em tempo real.",
		// 		LocalDateTime.of(2024, 3, 17, 11, 0),
		// 		LocalDateTime.of(2024, 3, 17, 13, 0),
		// 		null
		// 	),
		// 	new ReleaseTime(
		// 		null,
		// 		atividades.get(6),
		// 		usuario3,
		// 		"Implementei o sistema de mensagens.",
		// 		LocalDateTime.of(2024, 4, 17, 9, 0),
		// 		LocalDateTime.of(2024, 4, 17, 12, 0),
		// 		null
		// 	),
		// 	new ReleaseTime(
		// 		null,
		// 		atividades.get(7),
		// 		usuario4,
		// 		"Criei as páginas de perfil dos usuários.",
		// 		LocalDateTime.of(2024, 4, 22, 14, 0),
		// 		LocalDateTime.of(2024, 4, 22, 18, 0),
		// 		null
		// 	),
		// 	new ReleaseTime(
		// 		null,
		// 		atividades.get(8),
		// 		usuario1,
		// 		"Desenvolvi o sistema de pedidos e pagamentos.",
		// 		LocalDateTime.of(2024, 5, 7, 10, 0),
		// 		LocalDateTime.of(2024, 5, 7, 15, 0),
		// 		null
		// 	),
		// 	new ReleaseTime(
		// 		null,
		// 		atividades.get(9),
		// 		usuario2,
		// 		"Criei o sistema de avaliações de restaurantes.",
		// 		LocalDateTime.of(2024, 5, 12, 11, 0),
		// 		LocalDateTime.of(2024, 5, 12, 13, 0),
		// 		null
		// 	),
		// 	new ReleaseTime(
		// 		null,
		// 		atividades.get(10),
		// 		usuario3,
		// 		"Implementei o player de vídeo.",
		// 		LocalDateTime.of(2024, 6, 22, 9, 0),
		// 		LocalDateTime.of(2024, 6, 22, 12, 0),
		// 		null
		// 	),
		// 	new ReleaseTime(
		// 		null,
		// 		atividades.get(11),
		// 		usuario4,
		// 		"Desenvolvi o algoritmo de recomendações de vídeos.",
		// 		LocalDateTime.of(2024, 6, 27, 14, 0),
		// 		LocalDateTime.of(2024, 6, 27, 18, 0),
		// 		null
		// 	),
		// 	new ReleaseTime(
		// 		null,
		// 		atividades.get(12),
		// 		usuario1,
		// 		"Implementei o sistema de acompanhamento de exercícios.",
		// 		LocalDateTime.of(2024, 7, 27, 10, 0),
		// 		LocalDateTime.of(2024, 7, 27, 14, 0),
		// 		null
		// 	),
		// 	new ReleaseTime(
		// 		null,
		// 		atividades.get(13),
		// 		usuario2,
		// 		"Criei o sistema de definição de metas fitness.",
		// 		LocalDateTime.of(2024, 8, 1, 11, 0),
		// 		LocalDateTime.of(2024, 8, 1, 13, 0),
		// 		null
		// 	),
		// 	new ReleaseTime(
		// 		null,
		// 		atividades.get(14),
		// 		usuario3,
		// 		"Desenvolvi o sistema de reservas de viagens.",
		// 		LocalDateTime.of(2024, 8, 17, 9, 0),
		// 		LocalDateTime.of(2024, 8, 17, 12, 0),
		// 		null
		// 	),
		// 	new ReleaseTime(
		// 		null,
		// 		atividades.get(15),
		// 		usuario4,
		// 		"Criei o sistema de avaliações de hotéis.",
		// 		LocalDateTime.of(2024, 8, 22, 14, 0),
		// 		LocalDateTime.of(2024, 8, 22, 18, 0),
		// 		null
		// 	),
		// 	new ReleaseTime(
		// 		null,
		// 		atividades.get(16),
		// 		usuario1,
		// 		"Implementei o sistema de criação e gerenciamento de cursos.",
		// 		LocalDateTime.of(2024, 9, 12, 10, 0),
		// 		LocalDateTime.of(2024, 9, 12, 15, 0),
		// 		null
		// 	),
		// 	new ReleaseTime(
		// 		null,
		// 		atividades.get(17),
		// 		usuario2,
		// 		"Criei o sistema de emissão de certificados.",
		// 		LocalDateTime.of(2024, 9, 17, 11, 0),
		// 		LocalDateTime.of(2024, 9, 17, 13, 0),
		// 		null
		// 	),
		// 	new ReleaseTime(
		// 		null,
		// 		atividades.get(18),
		// 		usuario3,
		// 		"Desenvolvi o sistema de agendamento de consultas.",
		// 		LocalDateTime.of(2024, 10, 7, 9, 0),
		// 		LocalDateTime.of(2024, 10, 7, 12, 0),
		// 		null
		// 	),
		// 	new ReleaseTime(
		// 		null,
		// 		atividades.get(19),
		// 		usuario4,
		// 		"Criei o sistema de lembretes para consultas.",
		// 		LocalDateTime.of(2024, 10, 12, 14, 0),
		// 		LocalDateTime.of(2024, 10, 12, 18, 0),
		// 		null
		// 	)
		// );
		
		// releaseTimeRepository.saveAll(lancamentosHora);
	}
}
