package net.datasa.ex1;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BookController {
	
	private final BookService bookService;
	
	@PostMapping("save")
	public String save(@ModelAttribute BookDTO dto) {
		
		bookService.save(dto);
		
		return "redirect:/";
	}
	
	
	@GetMapping("search")
	public String search(@RequestParam("ISBN") String ISBN,
			Model model) {
		
		BookDTO dto = bookService.search(ISBN);
		
		model.addAttribute("ISBN", ISBN);
		
		model.addAttribute("book", dto);
		
		return "result";
	}
	
	@GetMapping("selectAll")
	public String selectAll(Model model) {
		List<BookDTO> booklist = bookService.selectAll();
		model.addAttribute("booklist", booklist);
		return "book_selectAll";
	}
	
	@GetMapping("delete")
	public String delete(@RequestParam("ISBN") String ISBN
			, Model model) {

		bookService.delete(ISBN);
		
		return "redirect:/selectAll";
	}
	
	

}
