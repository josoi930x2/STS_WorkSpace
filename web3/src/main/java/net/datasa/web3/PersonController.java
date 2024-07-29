package net.datasa.web3;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
@RequiredArgsConstructor
// 반드시 생성해야하는 생성자를 자동으로 만들어주는 롬복의 기능 
public class PersonController {
	
	
	/*
	 * @Autowired PersonService personService;
	 */
	
	
	private final PersonService personService;
	
	@GetMapping("test")
	public String text() {
		return "redirect:/";
	}
	
	@GetMapping("save")
	public String save() {
		return "inputForm";
	}
	
	@PostMapping("save")
	public String save(@ModelAttribute PersonDTO dto) {
		// set method의 멤버변수와 일치하도록??
		// 만약 DTO에 없는 데이터가 전달되는 경우, @RequestParam으로 받으면 됨
		log.debug("전달된 값:{}", dto);
		
		// model.addAttribute("id", id);
		personService.save(dto);
		//Service method는 기능단위로 만들어짐
		
		return "redirect:/";
	}
	
	@GetMapping("select")
	public String select() {
		return "selectForm";
	}
	
	@PostMapping("select")
	public String select(@RequestParam("id") String id
			, Model model) {
		
		PersonDTO dto = personService.select(id);
		
		model.addAttribute("id", id);
		// HTML에서 사용자에게 입력받은 id
		model.addAttribute("person", dto);
		// DB에 저장되어있는 유저 정보
		
		return "select";
	}
	
	@GetMapping("delete")
	public String delete() {
		return "deleteForm";
	}
	
	
	/**삭제하고 결과 출력 페이지로 이동 
	 * @param id 삭제할 아이디
	 * @param model 콘트롤러에서 뷰로 데이터를 이동할 객체
	 * @return 출력할 HTML 파일 경로
	 */
	@PostMapping("delete")
	public String delete(@RequestParam("id") String id
			, Model model) {
		
		boolean result = personService.delete(id);
		// return값이 id가 있는 경우 true, 없는 경우 false
		
		//삭제 여부를 나타내는 result와 삭제 시도한 id를 모델에 저장
		//HTML 페이지로 포워딩해서 안내문구 출력
		
		model.addAttribute("id", id);
		model.addAttribute("result", result);
		
		return "delete";
	}
	
	@GetMapping("selectAll")
	public String selectAll(Model model) {
		
		List<PersonDTO> personlist = personService.selectAll();
		
		model.addAttribute("personlist", personlist);
		
		return "selectAll";
	}
	
	@GetMapping("view")
	public String view(@RequestParam("id") String id
			, Model model) {
		
		PersonDTO dto = personService.select(id);
		
		model.addAttribute("id", id);
		model.addAttribute("person", dto);
		
		return "select";
	}
	
	// http://localhost/8888/info/aaa
	@GetMapping("info" +"/{id}" )
	public String info (
			@PathVariable("id") String id,
			// 매핑의 URL에 { } 로 들어가는 패스 변수(path variable)를 받음
			Model model) {
		
		PersonDTO dto = personService.select(id);
		
		model.addAttribute("id", id);
		model.addAttribute("person", dto);		
		
		
		return "select";
	}
	
	@GetMapping("remove" +"/{id}" )
	public String remove (
			@PathVariable("id") String id,
			Model model) {
		
		personService.delete(id);
		
		return "redirect:/selectAll";
	}
	
	@GetMapping("deleteUser")
	public String deleteUser(@RequestParam("id") String id
			, Model model) {
		
		personService.delete(id);
		
		return "redirect:/selectAll";
	}
	
	// 수정 페이지로 이동	
	@GetMapping("update")
	public String update (@RequestParam("id") String id
			, Model model) {
		
		// 추가요소 : 로그인했는지 여부 체크, 아이디가 일치하는지
		
		PersonDTO dto = personService.select(id);	
		model.addAttribute("person", dto);	
		
		return "updateForm";
	}
	
	@PostMapping("update")
	public String update (@ModelAttribute PersonDTO dto) {
		
		log.debug("전달된 값:{}", dto);
		
		personService.update(dto);
		
		//"view?id=abc"
		return "redirect:/view?id=" + dto.getId();
	}
	
	// 사용자가 폼에 입력한 값을 DB에 저장
}
