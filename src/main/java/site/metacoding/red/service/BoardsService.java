package site.metacoding.red.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.web.dto.request.boards.WriteDto;
import site.metacoding.red.web.dto.response.boards.MainDto;
import site.metacoding.red.web.dto.response.boards.PagingDto;
import site.metacoding.red.web.dto.response.users.UpdateDto;

@Service
@RequiredArgsConstructor
public class BoardsService {
	
	private final BoardsDao boardsDao;
	
	
	public PagingDto 게시글목록보기(Integer page, String keyword) {
		if(page == null)
			page = 0;
		Integer startNum = page * 3;
		List<MainDto> pageList = boardsDao.findAll(startNum, keyword);
		PagingDto pagingDto = boardsDao.paging(page, keyword);
		pagingDto.setMainDtos(pageList);
		if (pagingDto.getTotalCount() == 0)
			pagingDto.setNotResult(true);
		return pagingDto;
		
	}
	public Boards 게시글상세보기(Integer id) {
		Boards boards = boardsDao.findById(id);
		return boards;
	}
	
	public void 게시글수정하기(Integer id, UpdateDto updateDto) {
		Boards boards = boardsDao.findById(id);
		boards.updateBoard(updateDto);
		boardsDao.update(boards);
	}
	
	public void 게시글삭제하기(Integer id) {
		boardsDao.deleteById(id);
	}
	public void 게시글쓰기(WriteDto writeDto) {
		boardsDao.insert(writeDto);
	}
}
