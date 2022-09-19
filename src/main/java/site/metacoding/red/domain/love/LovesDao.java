package site.metacoding.red.domain.love;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import site.metacoding.red.web.dto.request.loves.LovesDto;

public interface LovesDao {
	public void insert(Loves loves);
	public List<Loves> findAll();
	public Loves findById(Integer id);
	public void update(Loves loves);
	public void deleteById(Integer id);
	public LovesDto findByBoardsId(@Param("boardsId") Integer boardsId, @Param("principalId") Integer principalId);
}
