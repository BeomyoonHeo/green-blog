package site.metacoding.red.domain.boards;

// java sql로 import
import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.red.web.dto.request.boards.UpdateDto;
import site.metacoding.red.web.dto.response.boards.DetailDto;

// entity는 setter를 만들지 않음
@NoArgsConstructor
@Setter
@Getter
public class Boards {
	private Integer id;
	private String title;
	private String content;
	private Integer usersId;
	private Timestamp createdAt; // At시분초 다 표현할때, Dt 년월일
	
	public Boards(String title, String content, Integer usersId) {
		this.title = title;
		this.content = content;
		this.usersId = usersId;
	}
	
	public Boards(DetailDto detailDto) {
		this.title = detailDto.getTitle();
		this.content = detailDto.getContent();
	}
	
	public void updateBoard(UpdateDto updateDto) {
		this.title = updateDto.getTitle();
		this.content = updateDto.getContent();
	}

}
