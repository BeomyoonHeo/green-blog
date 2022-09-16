package site.metacoding.red.web.dto.response.boards;

import java.security.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class LikeDto {
	private Integer id;
	private Integer usersId;
	private Integer boardsId;
	private Timestamp createdAt;
	
}
