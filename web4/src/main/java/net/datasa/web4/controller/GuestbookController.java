package net.datasa.web4.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.web4.domain.dto.GuestbookDTO;
import net.datasa.web4.service.GuestbookService;


@Controller
@Slf4j
@RequiredArgsConstructor
public class GuestbookController {
	
	private final GuestbookService guestbookService;

	//home.html에서 "test" 클릭하면 실행될 메소드
	//GuestbookService의 test()메소드를  호출하여
	//GuestbookEntity 객체를 생성해서 DB에 저장
	@GetMapping("test")
	public String test () {
		
		guestbookService.test();
		
		return "redirect:/";
	}
	
	@GetMapping("write")
	public String write() {
		
		return "writeForm";
	}
	
	@PostMapping("save")
	public String save(@ModelAttribute GuestbookDTO dto )  {
		
		guestbookService.save(dto);
		
		return "redirect:list";
	}
	
	@GetMapping("list")
	public String list(Model model) {
		//전체 글을 조회해서 모델에 저장
		
		List<GuestbookDTO> list = guestbookService.list();
		
		model.addAttribute("list" , list);
		
		return "list";
	}
	
	//@,ModelAttribute GuestbookDTO dto 
	 @PostMapping("delete") public String delete(
			 @RequestParam("num") Integer num,
			 @RequestParam("password") String password,
			 RedirectAttributes redirectAttributes) {

			  //예외가 총 2개 : 글이 없을때, 비밀번호가 틀렸을 때 
		 try { 
			 guestbookService.delete(num, password); 
			 } 
		 catch (Exception e) { 
				  //예외가 발생하면 어떻게 처리할지 
				  //Exception e가 상위(상속)이므로 앞에 있는 경우 뒤의 예외처리가 의미x 
			 e.printStackTrace();
			 //어디를 거쳐서 문제가 발생했는지 체크
			 // model.addAttribute("msg", "삭제 실패했습니다."); 리다이렉트(모든 정보 초기화)할때 없어짐
			 redirectAttributes.addFlashAttribute("msg", "삭제 실패했습니다.");
			 //한단계 이동할 동안 딱 한번살아있음
			 }
		 return "redirect:list";
		 }
	 
	 
	  @PostMapping("updateForm") public String update(
	  			@RequestParam("num") Integer num,
	  			@RequestParam("password") String password,
	  			RedirectAttributes redirectAttributes,
	  			Model model ){
		  try { 
	  		GuestbookDTO dto = guestbookService.select(num, password);
	  		model.addAttribute("updategb" , dto);
		  }
		  catch (Exception e) { 
			  e.printStackTrace();
			  redirectAttributes.addFlashAttribute("msg", "수정 실패했습니다.");
			  return "redirect:list";
		  }
	  		
	  	  return "updateForm"; 
	  }
	  
	  
	  @PostMapping("update") public String update(
			  @ModelAttribute GuestbookDTO dto){
		  
		  dto.setInputdate(LocalDateTime.now()); 
		  // inputdate를 현재 타임스탬프로 업데이트
		  guestbookService.update(dto);
		  return "redirect:list";
		  }
	  }

