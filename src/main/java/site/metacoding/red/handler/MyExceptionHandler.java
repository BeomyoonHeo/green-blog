package site.metacoding.red.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import site.metacoding.red.handler.ex.MyApiException;
import site.metacoding.red.handler.ex.MyException;
import site.metacoding.red.utill.Script;
import site.metacoding.red.web.dto.response.CMRespDto;

//에러가 발생되면 데이터를 반환해준다.
@ControllerAdvice
public class MyExceptionHandler {
	
	@ExceptionHandler(MyApiException.class)
	public @ResponseBody CMRespDto<?> apiError(Exception e){
		return new CMRespDto<>(-1, e.getMessage(), null);
	}
	@ExceptionHandler(MyException.class)
	public @ResponseBody String Error(Exception e){
		
		return Script.back(e.getMessage());
	}
}
