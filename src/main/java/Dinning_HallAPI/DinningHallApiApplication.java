package Dinning_HallAPI;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@RestController
@SpringBootApplication
public class DinningHallApiApplication {


	@GetMapping("/message")
	public String getMessage() {
		return "Hello govno!!";
	}
	private static final  String POST_API_URL = "http://localhost:8081";

	public static void main(String[] args) throws Exception {
		SpringApplication.run(DinningHallApiApplication.class, args);
		ArrayList<Table> tables = Table.GenerateTables(5);
		int numOfWaiters = 3;
		for (Table table: tables) {
			System.out.println(table.getStatus());
			table.createOrder();
			table.getOrder();
		}
		for (int i = 0; i < numOfWaiters; i++) {
			new Waiter(tables);
		}

		ClientGenerator clientGenerator = new ClientGenerator(tables);
		new Thread(clientGenerator).start();

	}


}


