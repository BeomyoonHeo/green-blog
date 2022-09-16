package site.metacoding.red.domain.boards;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.lang.Nullable;

import site.metacoding.red.web.dto.request.boards.WriteDto;
import site.metacoding.red.web.dto.response.boards.DetailDto;
import site.metacoding.red.web.dto.response.boards.MainDto;
import site.metacoding.red.web.dto.response.boards.PagingDto;

public interface BoardsDao {
	//처음에 Dao제작시 Dto생각 안해도 됨 - 나중에 수정하면 됨
	public void insert(Boards boards);
	public List<MainDto> findAll(@Param("page") Integer startNum, @Param("keyword") String keyword);
	public DetailDto findById(@Param("id") Integer id, @Param("usersid") Integer usersid);
	public void update(Boards boards);
	public void deleteById(Integer id);
	public void updateToboards(Integer usersid);
	public PagingDto paging(@Param("page") Integer page, @Param("keyword") String keyword);
	public void insertLike(@Param("usersId") Integer usersId,@Param("boardsId") Integer boardsId);
	public void deleteLike(@Param("usersId") Integer usersId,@Param("boardsId") Integer boardsId);
}
