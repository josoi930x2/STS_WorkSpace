package net.datasa.web5.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.web5.domain.dto.MemberDTO;
import net.datasa.web5.security.AuthenticatedUser;
import net.datasa.web5.service.MemberService;

/**
 * 회원정보 관련 컨트롤러
 */
@Controller
@RequestMapping("member")
@Slf4j
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
	@GetMapping("joinForm")
	public String joinForm() {
		return "memberView/joinForm";
		
	}
	
	@PostMapping("join")
	public String join( @ModelAttribute MemberDTO member) {
		log.debug("전달된 회원정보 : {}", member);
		
		memberService.join(member);
		
		return "memberView/joinForm";	
	}
	
	/**
	 * 회원가입 페이지에서 "ID중복확인"버튼을 클릭하면 새창으로 보여줄 검색 페이지로 이동
	 * @return ID검색 HTML파일 경로
	 */
	@GetMapping("idCheck")
	public String idCheck (Model model) {
		return "memberView/idCheck";
		
	}
	
	/**
	 * ID중복확인 페이지에서 검색 요청했을 때 처리
	 * @param searchId 검색할 아이디
	 * @return ID검색 HTML파일 경로
	 */
	@PostMapping("idCheck")
	public String idCheck2 (@RequestParam("searchId") String searchId,
			Model model){
		
		//검색할 아이디를 서비스로 보내서 사용 가능한지 조회(true면 사용 가능)
		boolean result = memberService.findId(searchId);
		
		//검색한 아이디와 결과를 모델에 저장
		model.addAttribute("searchId", searchId);
		model.addAttribute("result", result);
		
		//ID검색 페이지로 포워딩
		return "memberView/idCheck";
		
	}
	
	@GetMapping("memberList") 
	public String list(Model model) { 
	//전체 글을 조회해서 모델에 저장
	  List<MemberDTO> list = memberService.list();
	  model.addAttribute("list" , list);
	 
	  	return "memberView/memberList"; 
	}
	
	@GetMapping("loginForm")
	public String loginForm() {
		return "memberView/loginForm";
	}
	
	/**
	 * 개인정보 수정 폼으로 이동
	 * @return 수정폼 HTML 경로
	 */
	@GetMapping("info")
	public String info(@AuthenticationPrincipal AuthenticatedUser user, 
			//유저 객체 안 : 아이디, 비밀번호, 권한
			Model model) {
		
		//서비스로 아이디를 전달하여 사용자 정보 조회(MemberDTO 타입으로 리턴)
		//조회한 회원 정보를 모델에 저장하고 HTML로 포워딩
		MemberDTO memberDTO = memberService.getMember(user.getUsername());
		model.addAttribute("member", memberDTO);
		
		return "memberView/updateForm";
	}
	
	
	@PostMapping("info")
	public String update(@AuthenticationPrincipal AuthenticatedUser user,
			@ModelAttribute MemberDTO memberDTO) {
		log.debug("수정된 폼에서 전달된 값 : {}" , memberDTO);
		//서비스로 전달하여 DB수정
		memberDTO.setMemberId(user.getUsername());
		
		memberService.update(memberDTO);
		return "redirect:/";
	}
	
	

}
