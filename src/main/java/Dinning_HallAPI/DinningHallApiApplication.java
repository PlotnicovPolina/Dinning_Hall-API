package Dinning_HallAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class DinningHallApiApplication {

	public static ArrayList<Table> tables = new ArrayList<>();
	public static ArrayList<Waiter> waiters = new ArrayList<>();
	private static TimeUnit unit = TimeUnit.SECONDS;

	public static void main(String[] args) {
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

	public static TimeUnit getUnit() {
		return unit;
	}
}


