package site.metacoding.red.domain.boards;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import site.metacoding.red.web.dto.request.boards.WriteDto;
import site.metacoding.red.web.dto.response.boards.MainDto;
import site.metacoding.red.web.dto.response.boards.PagingDto;

public interface BoardsDao {
	//처음에 Dao제작시 Dto생각 안해도 됨 - 나중에 수정하면 됨
	public void insert(WriteDto writeDto);
	public List<MainDto> findAll(@Param("startNum") Integer startNum, @Param("keyword") String keyword);
	public Boards findById(Integer id);
	public void update(Boards boards);
	public void deleteById(Integer id);
	public void updateToboards(Integer usersid);
	public PagingDto paging(@Param("page") Integer page, @Param("keyword") String keyword);
}
