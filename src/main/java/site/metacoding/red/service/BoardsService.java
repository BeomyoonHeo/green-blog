package site.metacoding.red.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.web.dto.request.boards.WriteDto;
import site.metacoding.red.web.dto.response.boards.DetailDto;
import site.metacoding.red.web.dto.response.boards.MainDto;
import site.metacoding.red.web.dto.response.boards.PagingDto;
import site.metacoding.red.web.dto.request.boards.UpdateDto;

@Service
@RequiredArgsConstructor
public class BoardsService {
	
	private final BoardsDao boardsDao;
	
	
	public PagingDto 게시글목록보기(Integer page, String keyword) {
		if(page == null || page < 0)
			page = 0;
		Integer startNum = page * 5;
		List<MainDto> pageList = boardsDao.findAll(startNum, keyword);
		PagingDto pagingDto = boardsDao.paging(page, keyword);
		pagingDto.setMainDtos(pageList);
		if (pageList.isEmpty())
			pagingDto.setNotResult(true);
		if(page > pagingDto.getTotalPage()) {
			return null;
		}
		return pagingDto;
		
	}
	public DetailDto 게시글상세보기(Integer id, Integer usersid) {
		DetailDto detailDto = boardsDao.findById(id, usersid);
		return detailDto;
	}
	public Integer 게시글좋아요수정하기(Integer usersId, Integer boardsId, boolean islike) {
		if(islike) {
			boardsDao.insertLike(usersId, boardsId);
		}else {
			boardsDao.deleteLike(usersId, boardsId);
		}
		DetailDto detailDto = boardsDao.findById(boardsId, usersId);
		Integer likeCount = detailDto.getLikeCount();
		return likeCount;
	}
	
	public void 게시글수정하기(Integer id, UpdateDto updateDto) {
		Boards boards = new Boards(boardsDao.findById(id, null));
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
