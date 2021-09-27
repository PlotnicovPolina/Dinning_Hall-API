package Dinning_HallAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class DinningHallApiApplication {

	@GetMapping("/message")
	public String getMessage() {
		return "Hello govno!!";
	}

	public static void main(String[] args) {
		SpringApplication.run(DinningHallApiApplication.class, args);
	}

}
