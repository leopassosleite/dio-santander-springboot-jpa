package com.example.store.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.store.entities.Category;
import com.example.store.entities.Order;
import com.example.store.entities.OrderItem;
import com.example.store.entities.Payment;
import com.example.store.entities.Product;
import com.example.store.entities.User;
import com.example.store.entities.enums.OrderStatus;
import com.example.store.repositories.CategoryRepository;
import com.example.store.repositories.OrderItemRepository;
import com.example.store.repositories.OrderRepository;
import com.example.store.repositories.ProductRepository;
import com.example.store.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public void run(String... args) throws Exception {

		Product p1 = new Product(null, "Halo Infinite", "Jogo pica grossa do console pica grossa.", 300.0, "");
		Product p2 = new Product(null, "PC Gamer X", "Mais pica grossa.", 50000.0, "");
		Product p3 = new Product(null, "Xbox Series X", "Pica grossa.", 5500.0, "");
		Product p4 = new Product(null, "Controle Xbox Series X", "Pica grossa.", 500.0, "");
		Product p5 = new Product(null, "Playstation 5", "Console fraco.", 5500.0, "");
		Product p6 = new Product(null, "God of War 4", "Joguinho de gay.", 800.99, "");

		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6));

		Category cat1 = new Category(null, "Acessórios");
		Category cat2 = new Category(null, "Jogos");
		Category cat3 = new Category(null, "Computadores");
		Category cat4 = new Category(null, "Consoles");

		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4));

		p1.getCategories().add(cat2);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat4);
		p4.getCategories().add(cat1);
		p5.getCategories().add(cat3);
		p6.getCategories().add(cat2);

		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6));

		User u1 = new User(null, "Leonardo dos Passos", "leopassos.leite@gmail.com", "(51) 99154-6743", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "(51) 98112-4522", "123456");

		userRepository.saveAll(Arrays.asList(u1, u2));

		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAGO, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.PAGAMENTO_PENDENTE, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.PAGAMENTO_PENDENTE, u1);

		orderRepository.saveAll(Arrays.asList(o1, o2, o3));

		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o2, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());

		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
		
		Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1);
		o1.setPayment(pay1);
		
		orderRepository.save(o1);
	}
}
