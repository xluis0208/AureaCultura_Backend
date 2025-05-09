package backend.project;

import backend.project.entities.*;
import backend.project.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;


@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner mappingDemo(
			PromoterRepository promoterRepository,
			UserRepository userRepository,
			AuthorityRepository authorityRepository,
			ClientRepository clientRepository,
			CulturalEventRepository culturalEventRepository,
			EventTypeRepository eventTypeRepository,
			FavoriteRepository favoriteRepository,
			TransactionRepository transactionRepository,
			PurchasedTicketsRepository purchasedTicketsRepository,
			CityRepository cityRepository,
			TicketTypeRepository ticketTypeRepository,
			ClaimsRepository claimrepository,
			SponsorRepository sponsorRepository,
			ExpositorRepository expositorRepository,
			CommentRepository commentRepository

	) {
		return args -> {

			// Authority
			Authority authorityAdmin = authorityRepository.save(new Authority(null, "ADMIN", null));
			Authority authorityUser = authorityRepository.save(new Authority(null, "USUARIO", null));

			// Users

			User user1 = new User(null, "juanDom", new BCryptPasswordEncoder().encode("password123"), true, authorityAdmin);
			User user2 = new User(null, "miriamSa", new BCryptPasswordEncoder().encode("password456"), true, authorityUser);
			user1 = userRepository.save(user1);
			user2 = userRepository.save(user2);




			// Clients
			Client client1 = clientRepository.save(new Client(null, "Juan", "Domo", "M", 30, "123456789", "987654321", user1, ""));
			Client client2 = clientRepository.save(new Client(null, "Miriam", "Sanchez", "F", 28, "987654321", "123456789", user2, ""));


			// Claims
			claimrepository.save(new Claims(null, "La pagina es defectuoso", "El boton de agregar evento no funciona", client1));
			claimrepository.save(new Claims(null, "Evento cancelado", "El evento fue cancelado sin aviso", client2));

			// Cities
			City city1 = cityRepository.save(new City(null, "Lima Metropolitana"));
			City city2 = cityRepository.save(new City(null, "Callao"));

			// Event Types
			EventType eventType1 = eventTypeRepository.save(new EventType(null, "Music", "Evento musical en vivo", "Concert"));
			EventType eventType2 = eventTypeRepository.save(new EventType(null, "Performing arts", "Evento de artes escenicas", "Arts and Body"));

			// Promoters
			Promoter promoter1 = promoterRepository.save(new Promoter(null, "Promotor 1"));
			Promoter promoter2 = promoterRepository.save(new Promoter(null, "Promotor 2"));

			// Sponsors
			Sponsor sponsor1 = sponsorRepository.save(new Sponsor(null, "Coca Cola"));
			Sponsor sponsor2 = sponsorRepository.save(new Sponsor(null, "Red Bull"));

			// Expositores
			Expositor expositor1 = expositorRepository.save(new Expositor(null, "Carlos Rivera"));
			Expositor expositor2 = expositorRepository.save(new Expositor(null, "Ana Torres"));


			// Eventos Culturales
			CulturalEvent event1 = culturalEventRepository.save(new CulturalEvent(
					null, 1000, LocalDateTime.now(), LocalDateTime.now().plusDays(1),
					"MALI", "AC123", "evento1.jpg", "Concierto Musica clasica Frédéric Chopin",
					"Una noche inolvidable", city1, eventType1, promoter1, sponsor1, expositor1));

			CulturalEvent event2 = culturalEventRepository.save(new CulturalEvent(
					null, 500, LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(3),
					"Teatro Municipal", "BC456", "evento2.jpg", "Hamlet",
					"Ven y disfruta de esta obra de arte", city2, eventType2, promoter2, sponsor2, expositor2));



			expositor1.getEvents().add(event1);
			expositor2.getEvents().add(event2);
			expositorRepository.save(expositor1);
			expositorRepository.save(expositor2);

			sponsor1.getEvents().add(event1);
			sponsor2.getEvents().add(event2);
			sponsorRepository.save(sponsor1);
			sponsorRepository.save(sponsor2);

			// Favoritos
			favoriteRepository.save(new Favorite(null, client1, event1));
			favoriteRepository.save(new Favorite(null, client2, event2));

			// Comentarios
			Comment comment1 = new Comment(null, "Sugerencia", "Me encantó el evento, pero faltó organización al ingreso.", client1);
			Comment comment2 = new Comment(null, "Reclamo", "No se respetaron los horarios anunciados.", client2);
			Comment comment3 = new Comment(null, "Felicitación", "Excelente organización y contenido del evento.", client1);
			commentRepository.save(comment1);
			commentRepository.save(comment2);
			commentRepository.save(comment3);

			// Transacciones
			Transaction transaction1 = transactionRepository.save(new Transaction(null, LocalDate.now(), 200.50, 2, client1));
			Transaction transaction2 = transactionRepository.save(new Transaction(null, LocalDate.now().minusDays(1), 150.75, 1, client2));

			// Tipos de Entrada
			TicketType ticketType1 = ticketTypeRepository.save(new TicketType(null, "VIP", 500.00, 100, event1));
			TicketType ticketType2 = ticketTypeRepository.save(new TicketType(null, "General", 100.00, 500, event2));

			// Entradas compradas
			purchasedTicketsRepository.save(new PurchasedTickets(null, 500.00, LocalDateTime.now(), ticketType1, transaction1));
			purchasedTicketsRepository.save(new PurchasedTickets(null, 100.00, LocalDateTime.now().plusHours(1), ticketType2, transaction2));

			// Impresión en consola (opcional)
			clientRepository.findAll().forEach(c -> System.out.println("Cliente: " + c.getFirstName() + " " + c.getLastName()));
			culturalEventRepository.findAll().forEach(e -> System.out.println("Evento: " + e.getNombre() + " en " + e.getAddress()));
			transactionRepository.findAll().forEach(t -> System.out.println("Transacción: " + t.getClient().getFirstName() + " - $" + t.getAmount()));

		};
	}

}
