package dk.sdu.cbse.ScoringSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ScoringSystemApplication {
	private int score = 0;

	public static void main(String[] args) {
		SpringApplication.run(ScoringSystemApplication.class, args);
	}

	/**
	 * Endpoint receives the value of points just earned, and returns the accumulated score. <br>
	 * Available on http://localhost:8080
	 * */
	@PutMapping("/addScore") // go to http://localhost:8080/addScore?point=10 to add 10 points
	public void addPoints(@RequestParam(value = "points", defaultValue = "0") int points) {
		score += points;
	}

	@GetMapping("/getScore")
	public int getPoints() {
		return score;
	}
}
