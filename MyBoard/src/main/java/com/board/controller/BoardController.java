package com.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.domain.BoardDTO;
import com.board.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	/*
	 * @GetMapping(value = "/write.do") public String openBoardWrite(Model model) {
	 * 
	 * String title = "제목"; String content = "내용"; String writer = "홍길동";
	 * 
	 * model.addAttribute("t", title); model.addAttribute("c", content);
	 * model.addAttribute("w", writer); return "write"; }
	 */
	
	
	@GetMapping(value = "/write.do")
	public String openBoardWrite(@RequestParam(value = "idx", required = false) Long idx, Model model) {
		if (idx == null) {
			model.addAttribute("board", new BoardDTO());
		} else {
			BoardDTO board = boardService.getBoardDetail(idx);
			if (board == null) {
				return "redirect:/list.do";
			}
			model.addAttribute("board", board);
		}
		return "write";
	}
	
	
	@PostMapping(value = "/register.do")
	public String registerBoard(final BoardDTO params) {
		try {
			boolean isRegistered = boardService.registerBoard(params);
			if (isRegistered == false) {
				System.out.println("게시글 등록 실패");
			}
		} catch (DataAccessException e) {
			System.out.println("DB 처리 과정에 문제가 발생");
		} catch (Exception e) {
			System.out.println("시스템에 문제가 발생");
		}
		return "redirect:/list.do";
	}

	@GetMapping(value = "/list.do")
	public String openBoardList(Model model) {
		List<BoardDTO> boardList = boardService.getBoardList();
		model.addAttribute("boardList", boardList);

		return "list";
	}
	
	
	

}
