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
	
	@GetMapping(value = "/view.do")
	public String openBoardDetail(@RequestParam(value = "idx", required = false) Long idx, Model model) {
		if (idx == null) {
			return "redirect:/list.do";
		}

		BoardDTO board = boardService.getBoardDetail(idx);
		if (board == null || "Y".equals(board.getDeleteYn())) {
			return "redirect:/list.do";
		}
		model.addAttribute("board", board);

		return "view";
	}
	
	@PostMapping(value = "/delete.do")
	public String deleteBoard(@RequestParam(value = "idx", required = false) Long idx) {
	    if (idx == null) {
	        System.out.println("올바르지 않은 접근: idx가 null입니다.");
	        return "redirect:/list.do"; // 리다이렉트는 문제가 없지만 로그를 좀 더 구체적으로 남기는 것이 좋습니다.
	    }

	    try {
	        boolean isDeleted = boardService.deleteBoard(idx);
	        if (!isDeleted) {
	            System.out.println("게시글 삭제 실패: idx=" + idx);
	        }
	    } catch (DataAccessException e) {
	        System.out.println("DB 처리과정 문제: " + e.getMessage());
	        // 로그를 남기거나 다른 처리 방안을 고려할 수 있습니다.
	    } catch (Exception e) {
	        System.out.println("시스템 문제 발생: " + e.getMessage());
	        // 시스템 문제에 대한 보다 구체적인 처리를 추가할 수 있습니다.
	    }
	    
	    return "redirect:/list.do"; 
	}

	
}
