package site.metacoding.red.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TestController {
	
	@CrossOrigin
	@GetMapping("/username")
	public String username() {
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "ssar";
	}
	
	@CrossOrigin
	@GetMapping("/password")
	public String password() {
		
		return "1234";
	}
}
