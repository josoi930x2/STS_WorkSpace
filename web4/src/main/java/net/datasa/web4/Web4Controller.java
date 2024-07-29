package net.datasa.web4;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Web4Controller {
	
	@GetMapping({"","/"})
	public String Home() {
	
		return "web4mainHome";			
	}
	
	

}
