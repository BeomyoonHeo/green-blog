package site.metacoding.red.web;



import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.service.BoardsService;
import site.metacoding.red.web.dto.request.boards.WriteDto;
import site.metacoding.red.web.dto.response.boards.PagingDto;
import site.metacoding.red.web.dto.response.users.UpdateDto;

@RequiredArgsConstructor
@Controller
public class BoardsController {

	private final BoardsService boardsService;
	private final HttpSession session;
	
	
	@GetMapping({"/boards", "/"})
	public String mainPage(Integer page, String keyword, Model model) {
		PagingDto pagingDto = boardsService.게시글목록보기(page, keyword);
		pagingDto.makeBlockInfo(keyword);
		model.addAttribute("pagingDto", pagingDto);
		return "boards/main";
	}
	
	@GetMapping("/boards/{id}")
	public String board(@PathVariable Integer id, Model model) {
		Boards boards = boardsService.게시글상세보기(id);
		model.addAttribute("boards", boards);
		return "boards/detail";
	}
	
	@GetMapping("/boards/update/{id}")
	public String updateForm(@PathVariable Integer id, Model model) {
		Boards boards = boardsService.게시글상세보기(id);
		model.addAttribute("boards", boards);
		return "boards/updateForm"; 
	}
	
	@PostMapping("/boards/update/{id}")
	public String update(@PathVariable Integer id, UpdateDto updateDto) {
		boardsService.게시글수정하기(id, updateDto);
		return "redirect:/boards"; 
	}
	
	@GetMapping("/boards/delete/{id}")
	public String update(@PathVariable Integer id) {
		boardsService.게시글삭제하기(id);
		return "redirect:/boards"; 
	}
	
	@GetMapping("/boards/write")
	public String writeBoard() {
		return "boards/writeForm";
	}
	
	@PostMapping("/boards/write")
	public String write(WriteDto writeDto) {
		Users users = (Users)session.getAttribute("principal");
		writeDto.setUsersId(users.getId());
		boardsService.게시글쓰기(writeDto);
		return "redirect:/boards";
		
	}

}