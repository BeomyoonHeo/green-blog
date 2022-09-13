package site.metacoding.red.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.domain.users.UsersDao;


//Transection 관리할때 사용하는 annotation
@Service
@RequiredArgsConstructor
public class UsersService {
//DB연결 안되면 service가 필요가 없다.
	
	private final UsersDao usersDao;
	private final BoardsDao boardsDao;
	
	public void 회원가입(/*dto*/) { // username, password, email (id, createdAt)
		// 1. dto를 entity로 변경하는 코드
		
		// 2. entity로 DB 수행
		usersDao.insert(null);
	}
	public Users 로그인(/*dto*/) { // username, password
		Users UsersPs = usersDao.findUsername(/*dto.getUsername()*/);
		// if로 usersPS의 password와 dto password 비교
	}
	
	public void 회원수정(Integer id /*, dto*/) { // id, dto(password, email)
		// 1. 영속화
		Users usersPS = usersDao.findById(id);
		// 2. 영속화된 객체 변경
		usersPS.update(/*dto*/);
		// 3. DB 수행
		usersDao.update(usersPS);
	}
	
	//@Transactional - 하나의 Transaction으로 묶음
	@Transactional(rollbackFor = RuntimeException.class)
	public void 회원탈퇴(Integer id) {
		usersDao.deleteById(id);
		//boardsDao.해당회원이 적은글을 모두 찾아서 usersId를 null로 업데이트();
	} // users - delete, boards - update
	public boolean 아이디중복확인(String username) {
		Users usersPS = usersDao.findByUsername(dto.getusername());
		
		// 있으면 true, 없으면 false
	} 
	
	public Users 회원정보보기(Integer id) {
		Users usersPS = usersDao.findById(id);
		return usersPS;
	}
}
