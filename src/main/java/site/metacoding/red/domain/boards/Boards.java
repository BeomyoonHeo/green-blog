package site.metacoding.red.domain.boards;

// java sql로 import
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

// entity는 setter를 만들지 않음
@Setter
@Getter
public class Boards {
	private Integer id;
	private String title;
	private String content;
	private Integer usersId;
	private Timestamp createdAt; // At시분초 다 표현할때, Dt 년월일

}
