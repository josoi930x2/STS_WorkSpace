package net.datasa.web5.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.web5.domain.dto.BoardDTO;
import net.datasa.web5.security.AuthenticatedUser;
import net.datasa.web5.service.BoardService;

@Controller
@RequestMapping("board")
@Slf4j
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	
	@GetMapping("list")
	public String list() {
		return "boardView/list";
	}
	
	//@GetMapping("write")
	//public String write() {
		//return "boardView/writeForm";
	//}
	
	//@PostMapping("save")
	//public String save(@ModelAttribute BoardDTO dto, 
			//Model model,
			//@AuthenticationPrincipal AuthenticatedUser user)  {
		
		//boardService.save(dto);
		
		//return "redirect:list";
	//}
	
}
