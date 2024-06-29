package net.datasa.test2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// import 단축키 : Ctrl+Shift+O
@Controller
public class HomeController {
	
	//외부로부터 들어오는 요청과 연결
	@GetMapping({"","/","home"})
	public String home() {
		// 필요한 자바 코드
		System.out.println("home메소드 지나감");
		// 템플릿으로 이동(templates폴더의 home.html :오른쪽 마우스 new-other)
		return "home";
		
	}
	// sub 앞에 http://localhost:8888이 생략되어있음
	@GetMapping("sub")
	public String subMethod() {
		System.out.println("sub메소드");
		return "subView";
	}

}
