package net.datasa.ex1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Ex1Controller {
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("register")
	public String register() {
		return "register";
	}
	
	@GetMapping("search")
	public String search() {
		return "search";
	}
	

}
