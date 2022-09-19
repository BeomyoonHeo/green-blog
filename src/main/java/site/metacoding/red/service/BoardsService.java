package site.metacoding.red.service;

import java.util.List;


import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.domain.love.Loves;
import site.metacoding.red.domain.love.LovesDao;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.web.dto.request.boards.WriteDto;
import site.metacoding.red.web.dto.request.loves.LovesDto;
import site.metacoding.red.web.dto.response.boards.DetailDto;
import site.metacoding.red.web.dto.response.boards.MainDto;
import site.metacoding.red.web.dto.response.boards.PagingDto;
import site.metacoding.red.web.dto.request.boards.UpdateDto;

@Service
@RequiredArgsConstructor
public class BoardsService {
	
	private final BoardsDao boardsDao;
	private final LovesDao lovesDao;
	
	public void 좋아요(Loves loves) {
		lovesDao.insert(loves);
	}
	
	
	public PagingDto 게시글목록보기(Integer page, String keyword) {
		if(page == null)
			page = 0;
		Integer startNum = page * PagingDto.ROW;
		List<MainDto> pageList = boardsDao.findAll(startNum, keyword, PagingDto.ROW);
		PagingDto pagingDto = boardsDao.paging(page, keyword, PagingDto.ROW);
		pagingDto.setMainDtos(pageList);
		if (pagingDto.getTotalCount() == 0)
			pagingDto.setNotResult(true);
		return pagingDto;
		
	}
	public DetailDto 게시글상세보기(Integer id, Integer principalId) {
		Boards boards = boardsDao.findById(id);
		LovesDto lovesDto = lovesDao.findByBoardsId(id, principalId);
		if(lovesDto == null) {
			lovesDto = new LovesDto();
			lovesDto.setCount(0);
			lovesDto.setLoved(false);
		}
		return new DetailDto(boards, lovesDto);
	}
	
	public Boards 게시글수정화면데이터가져오기(Integer id) {
		return boardsDao.findById(id);
	}
	
	public void 게시글수정하기(Integer id, UpdateDto updateDto) {
		Boards boards = boardsDao.findById(id);
		boards.updateBoard(updateDto);
		boardsDao.update(boards);
	}
	
	public void 게시글삭제하기(Integer id) {
		boardsDao.deleteById(id);
	}
	public void 게시글쓰기(WriteDto writeDto, Users principal) {
		boardsDao.insert(writeDto.toEntity(principal.getId()));
	}
}
