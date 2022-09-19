package site.metacoding.red.domain.love;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Loves {
	private Integer id;
	private Integer boardsId;
	private Integer usersId;
	private Timestamp createdAt;
	
	public Loves(Integer usersId, Integer boardsId) {
		this.usersId = usersId;
		this.boardsId = boardsId;  
	}

}
