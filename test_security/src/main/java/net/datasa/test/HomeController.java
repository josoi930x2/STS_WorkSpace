package net.datasa.test;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	
	@GetMapping("")
	public String home() {	
		
		return "home";
	}
	
	@GetMapping("view1")
	public String view1() {
		return "view1";
	}
	
	@GetMapping("view2")
	public String view2(@AuthenticationPrincipal AuthenticatedUser user) {
		log.debug("로그인한 아이디 : {}", user.getId());
		log.debug("로그인한 객체정보 : {}", user);
		return "view2";
	}
	
	@GetMapping("loginForm")
	public String loginForm() {
		return "loginForm";
	}
	
	@GetMapping("thymeleaf")
	public String thymeleaf() {
		return "thymeleaf";
	}
	
	

}
