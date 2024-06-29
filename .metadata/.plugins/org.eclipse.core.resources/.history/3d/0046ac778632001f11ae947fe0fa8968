package net.datasa.homework240621.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import net.datasa.homework240621.model.board;
import net.datasa.homework240621.service.boardService;

@Controller
public class boardController {
	@Autowired
	boardService bService;
	
	@PostMapping("")
	@ResponseBody
	public board createBoard(@RequestBody board b) {
		board newB = bService.createBoard(b);
		return newB;
	}
	
	@GetMapping("/all")
	@ResponseBody
	public List<board> selectBoard() {
		List<board> boards = bService.selectAll();
		return boards;
	}
	

}
