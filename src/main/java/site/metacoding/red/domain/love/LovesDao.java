package site.metacoding.red.domain.love;

import java.util.List;

public interface LovesDao {
	public void insert(Loves loves);
	public List<Loves> findAll();
	public Loves findById(Integer id);
	public void update(Loves loves);
	public void deleteById(Integer id);
}
