package dk.sdu.cbse.ScoringSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
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
	 * Available on http://localhost:8080/score
	 * */
	@GetMapping("/addScore")
	public void addPoints(@RequestParam(value = "point", defaultValue = "0") int point) {
		score += point;
	}
	@GetMapping("/getScore")
	public int getPoints() {
		return score;
	}
}
