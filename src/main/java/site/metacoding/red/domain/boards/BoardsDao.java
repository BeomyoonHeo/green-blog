package site.metacoding.red.domain.boards;

import java.util.List;

public interface BoardsDao {
	//처음에 Dao제작시 Dto생각 안해도 됨 - 나중에 수정하면 됨
	public void insert(Boards boards);
	public List<Boards> findAll();
	public Boards findById(Integer id);
	public void update(Boards boards);
	public void deleteById(Integer id);
}
