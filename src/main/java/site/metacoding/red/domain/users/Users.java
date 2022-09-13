package site.metacoding.red.domain.users;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.red.service.dto.users.JoinDto;
import site.metacoding.red.service.dto.users.UpdateDto;

@Setter
@Getter
public class Users {
	private Integer id;
	private String username;
	private String password;
	private String email;
	private Timestamp createdAt;
	
	public void setJoin(JoinDto joinDto) {
		this.username = joinDto.getUsername();
		this.password = joinDto.getPassword();
		this.email = joinDto.getEmail();
	}
	
	public void update(UpdateDto updateDto) {
		this.password = updateDto.getPassword();
		this.email = updateDto.getEmail();
	}
}
