package net.datasa.web3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class Web3Controller {

		private final PersonService personService;
	
		@GetMapping({"","/"})
		public String Home() {
			personService.test();
			return "mainHome";			
		}
		
}		
