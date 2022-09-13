package site.metacoding.red.domain.users;

import java.util.List;

public interface UsersDao {
	public void insert(Users users);
	public Users findById(Integer id);
	public List<Users> findAll();
	public void update(Users users);
	public void deleteById(Integer id);
	public Users findUsername(String username);
	
}
