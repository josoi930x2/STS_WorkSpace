package net.datasa.web9;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Web9Controller {
	
	@GetMapping("/")
	public String home() {
		
		return "web9Home";
	}
	
	@GetMapping("input")
	public String input() {
		
		return "inputForm";
	}

}
