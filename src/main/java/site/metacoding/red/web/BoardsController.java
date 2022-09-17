package site.metacoding.red.web;



import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.security.PermissionCheck;
import org.springframework.lang.Nullable;
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
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.service.BoardsService;
import site.metacoding.red.utill.CheckPermission;
import site.metacoding.red.web.dto.request.boards.WriteDto;
import site.metacoding.red.web.dto.response.CMRespDto;
import site.metacoding.red.web.dto.response.boards.DetailDto;
import site.metacoding.red.web.dto.response.boards.PagingDto;
import site.metacoding.red.web.dto.request.boards.UpdateDto;

@RequiredArgsConstructor
@Controller
public class BoardsController {

	private final BoardsService boardsService;
	private final HttpSession session;
	static Users userInfo;
	
	
	@GetMapping({"/boards", "/"})
	public String mainPage(Integer page, String keyword, Model model) {
		userInfo = CheckPermission.CheckingPermission(session);
		PagingDto pagingDto = boardsService.게시글목록보기(page, keyword);
		pagingDto.makeBlockInfo(keyword);
		model.addAttribute("pagingDto", pagingDto);
		return "boards/main";
	}
	
	@GetMapping("/boards/{id}")
	public String board(@PathVariable Integer id, Model model){
		Integer usersid = CheckPermission.getUsersid();
		DetailDto detailDto = boardsService.게시글상세보기(id, usersid);
		model.addAttribute("boards", detailDto);
		return "boards/detail";
	}
	
	@GetMapping("/boards/update/{id}")
	public String updateForm(@PathVariable Integer id, Model model) {
		Users usersPS = (Users)session.getAttribute("principal");
		DetailDto detailDto = boardsService.게시글상세보기(id, null);
		model.addAttribute("boards", detailDto);
		model.addAttribute("users", usersPS);
		return "boards/updateForm"; 
	}
	
	@PutMapping("/boards/getLikeCount/{id}")
	public @ResponseBody CMRespDto<?> setLike(@PathVariable Integer id, @RequestBody DetailDto dtailDto){
		Integer likeCount = null;
		CMRespDto<?> cmRespDto;
		if(CheckPermission.getUsersid() == null) {
			cmRespDto = new CMRespDto<>(0, "로그인 필요", likeCount);
		}else {
			likeCount = boardsService.게시글좋아요수정하기(userInfo.getId(), id, dtailDto.isIslike());
			cmRespDto = new CMRespDto<>(1, "변경 완료", likeCount);
		}
		
		return cmRespDto;
	}
	
	@PutMapping("/boards/update/{id}")
	public @ResponseBody CMRespDto<?> update(@PathVariable Integer id, @RequestBody UpdateDto updateDto) {
		boardsService.게시글수정하기(id, updateDto);
		return new CMRespDto<>(1, "게시글 수정 완료", null); 
	}
	
	@DeleteMapping("/boards/delete/{id}")
	public @ResponseBody CMRespDto<?> delete(@PathVariable Integer id) {
		Integer usersId = CheckPermission.getUsersid();
		if(usersId == null)
			return new CMRespDto<>(-1, "로그인 필요", usersId);
		DetailDto boards = boardsService.게시글상세보기(id, usersId);
		if(boards.getUsersId() != CheckPermission.getUsersid())
			return new CMRespDto<>(-1, "작성자가 아님", usersId);
		
		boardsService.게시글삭제하기(id);
		return new CMRespDto<>(1, "삭제 완료", usersId);
	}
	
	@GetMapping("/boards/write")
	public String writeBoard() {
		return "boards/writeForm";
	}
	
	@PostMapping("/boards/write")
	public @ResponseBody CMRespDto<?> write(@RequestBody WriteDto writeDto) {
		Users users = (Users)session.getAttribute("principal");
		boardsService.게시글쓰기(writeDto, users);
		return new CMRespDto<>(1, "글쓰기 성공", null);
		
	}

}
