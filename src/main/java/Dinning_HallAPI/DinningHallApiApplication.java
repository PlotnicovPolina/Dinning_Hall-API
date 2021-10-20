package Dinning_HallAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@SpringBootApplication
public class DinningHallApiApplication {


	@GetMapping("/message")
	public String getMessage() {
		return "Hello govno!!";
	}
	public static ArrayList<Table> tables = new ArrayList<>();
	public static ArrayList<Waiter> waiters = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		SpringApplication.run(DinningHallApiApplication.class, args);
		GenerateTables(5);
		GenerateWaiters(3);

		ClientGenerator clientGenerator = new ClientGenerator(tables);
		new Thread(clientGenerator).start();
		for (Waiter waiter: waiters ) {
			new Thread(waiter).start();
		}
	}

	public static void GenerateTables (int number){
		for (int i = 0; i <= number; i++) {
			tables.add(new Table(Status.FREE));
		}
	}

	public static void GenerateWaiters (int number){
		for (int i = 0; i <= number; i++) {
			waiters.add(new Waiter(tables));
		}
	}
}


