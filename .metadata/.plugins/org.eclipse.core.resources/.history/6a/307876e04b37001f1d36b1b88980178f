package net.datasa.web2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
import net.datasa.web2.domain.CalcDTO;
import net.datasa.web2.service.CalcService;

/**
 * 연습문제
 */
@Slf4j
@RequestMapping("/ex")
@Controller
public class ExController {
	
	@Autowired
	CalcService calcService;
	// 필요한 객체를 타입으로 찾아서 주입
	
	/**
	[연습문제 1]
	1. ex/calc1 경로로 요청
	2. 입력 폼을 출력 (입력란 2개, Select 1개, submit버튼 1개 포함)
	3. 숫자 2개를 입력하고 연산자를 선택 후 submit 버튼 클릭
	4. 숫자가 아닌 값을 입력하면 JavaScript로 확인하고 오류메시지 출력
	5. 숫자 2개를 정상적으로 입력하면 서버로 전송
	6. 콘트롤러에서 값을 전달받아 계산
	7. 계산한 결과를 Model에 저장하고 View로 이동
	8. 화면에 계산한 결과 출력
	(새로운 Controller 정의, HTML 파일 2개 필요)
	 */
	
	/**
	 * 계산기 폼 화면으로 이동
	 */
	@GetMapping("calc1")
	public String calc1() {
		return "exView/calcForm1";
	}


	@PostMapping("calc1")
	public String calcOutput1(
			@ModelAttribute CalcDTO dto,
			//@RequestParam("num1") int n1,
			//@RequestParam (name="op") String op
			//요청의 쿼리 파라미터나 폼 데이터에서 데이터를 추출해 
			//해당 값을 바로 메소드 파라미터에 할당
			Model model) {
		
		int res = 0, n1, n2;
		try {
			switch (dto.getOp()) {
				case "+" : res = dto.getNum1() + dto.getNum2(); break;
				case "-" : res = dto.getNum1() - dto.getNum2(); break;
				case "*" : res = dto.getNum1() * dto.getNum2(); break;
				case "/" : res = dto.getNum1() / dto.getNum2(); break;
				default: throw new Exception("연산자 오류");
			}
			model.addAttribute("calc", dto);
			model.addAttribute("res", res);
		}
		catch (Exception e) {
			e.printStackTrace();
			return "/exView/calcForm1";	//예외 발생시 계산폼으로 다시 이동
		}
		return "exView/calcOutput1";
	}
	
	/**
	 * 계산기2 폼 화면으로 이동
	 */
	@GetMapping("calc2")
	public String calc2() {
		return "exView/calcForm2";
	}


	@PostMapping("calc2")
	public String calcOutput2(
			@ModelAttribute CalcDTO dto,
			Model model) {
		
		//서비스의 메소드 호출
		try {
			int res = calcService.calc(dto);
			model.addAttribute("calc", dto);
			model.addAttribute("res", res);
			return "exView/calcOutput2";
			
		} catch (Exception e) {
			e.printStackTrace();
			return "/exView/calcForm2";	//예외 발생시 계산폼으로 다시 이동
		}
	}
}