package site.metacoding.red.web.dto.response.users;


import lombok.Getter;
import lombok.Setter;
import site.metacoding.red.domain.boards.Boards;

@Setter
@Getter
public class UpdateDto {
	private String title;
	private String content;
	
	public void setDto(Boards boards) {
		this.title = boards.getTitle();
		this.content = boards.getContent();
	}

}
