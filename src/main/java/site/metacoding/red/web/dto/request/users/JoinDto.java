package site.metacoding.red.web.dto.request.users;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JoinDto {
	@Size(min = 2, max = 20, message = "username 길이가 부적합합니다.")
	@NotBlank(message = "username이 null이거나 공백일 수 없습니다.") // null과 공백 검사
	@Pattern(regexp = "[a-zA-Z0-9]+", message = "한글 입력 불가")
	private String username;
	@Size(min = 2, max = 20, message = "password 길이가 부적합합니다.")
	@NotBlank(message = "password가 null이거나 공백일 수 없습니다.")
	@Pattern(regexp = "[a-zA-Z0-9]+", message = "한글 입력 불가")
	private String password;
	@Size(min = 4, max = 50, message = "email 길이가 부적합합니다.")
	@NotBlank(message = "email이 null이거나 공백일 수 없습니다.")
	@Pattern(regexp = "[a-zA-z0-9]+@[a-zA-z]+[.]+[a-zA-z.]+", message = "이메일 형식이 올바르지 않습니다.")
	private String email;

}
