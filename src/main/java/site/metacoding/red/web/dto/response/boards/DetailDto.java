package site.metacoding.red.web.dto.response.boards;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailDto {
	private Integer id;
	private String title;
	private String content;
	private Integer likeCount;
	private boolean islike;
	
}
