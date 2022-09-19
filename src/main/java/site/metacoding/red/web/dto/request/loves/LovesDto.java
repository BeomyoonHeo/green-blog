package site.metacoding.red.web.dto.request.loves;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LovesDto {
	private boolean isLoved;
	private Integer count;
}
