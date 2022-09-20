package site.metacoding.red.web;



import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.love.Loves;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.service.BoardsService;
import site.metacoding.red.web.dto.request.boards.WriteDto;
import site.metacoding.red.web.dto.response.CMRespDto;
import site.metacoding.red.web.dto.response.boards.PagingDto;
import site.metacoding.red.web.dto.request.boards.UpdateDto;

@RequiredArgsConstructor
@Controller
public class BoardsController {

	private final BoardsService boardsService;
	private final HttpSession session;
	
	
	@GetMapping({"/boards", "/"})
	public String mainPage(Integer page, String keyword, Model model) {
		
		PagingDto pagingDto = boardsService.게시글목록보기(page, keyword);
		
		HashMap<String, Object> hashMap = new HashMap<>();
		
		pagingDto.makeBlockInfo(keyword);
		hashMap.put("page", pagingDto.getCurrentPage());
		hashMap.put("keyword", pagingDto.getKeyword());
		
		session.setAttribute("refferer", hashMap);
		model.addAttribute("pagingDto", pagingDto);
		return "boards/main";
	}
	
	@GetMapping("/boards/{id}")
	public String board(@PathVariable Integer id, Model model) {
		Users principal = (Users) session.getAttribute("principal");
		if(principal == null) {
			model.addAttribute("detailDto", boardsService.게시글상세보기(id, 0));
		}else {
			model.addAttribute("detailDto", boardsService.게시글상세보기(id, principal.getId()));
		}
		return "boards/detail";
	}
	
	@GetMapping("/boards/{id}/updateForm")
	public String updateForm(@PathVariable Integer id, Model model) {
		Boards boards = boardsService.게시글수정화면데이터가져오기(id);
		model.addAttribute("boards", boards);
		return "boards/updateForm"; 
	}
	
	@PutMapping("/boards/{id}")
	public @ResponseBody CMRespDto<?> update(@PathVariable Integer id, @RequestBody UpdateDto updateDto) {
		boardsService.게시글수정하기(id, updateDto);
		return new CMRespDto<>(1, "게시글 수정 완료", null); 
	}
	
	@DeleteMapping("/boards/{id}")
	public @ResponseBody CMRespDto<?> update(@PathVariable Integer id) {
		boardsService.게시글삭제하기(id);
		return new CMRespDto<>(1, "삭제완료", null); 
	}
	
	@GetMapping("/boards/write")
	public String writeBoard() {
		return "boards/writeForm";
	}
	
	@PostMapping("/boards/{id}/loves")
	public @ResponseBody CMRespDto<?> insertLoves(@PathVariable Integer id){
		Users principal = (Users)session.getAttribute("principal");
		Loves loves = new Loves(principal.getId(), id);
		boardsService.좋아요(loves);
		return new CMRespDto<>(1, "좋아요 성공", loves); // mybatis에서 return type을 명시하지 않아도 insert후 데이터를 리턴해준다.
	}
	
	@DeleteMapping("/boards/{id}/loves/{lovesId}")
	public @ResponseBody CMRespDto<?> deleteLoves(@PathVariable Integer id, @PathVariable Integer lovesId){
		boardsService.좋아요취소(lovesId);
		return new CMRespDto<>(1, "좋아요 취소 성공", null);
	}
	
	@PostMapping("/boards/write")
	public @ResponseBody CMRespDto<?> write(@RequestBody WriteDto writeDto) {
		Users users = (Users)session.getAttribute("principal");
		boardsService.게시글쓰기(writeDto, users);
		return new CMRespDto<>(1, "글쓰기 성공", null);
		
	}

}
