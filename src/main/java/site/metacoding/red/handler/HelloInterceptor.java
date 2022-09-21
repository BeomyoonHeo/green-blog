package site.metacoding.red.handler;

import java.io.BufferedReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;

import site.metacoding.red.web.dto.response.CMRespDto;

public class HelloInterceptor implements HandlerInterceptor{
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		BufferedReader br = request.getReader();
		StringBuilder builder = new StringBuilder();
		String st = "";
		while(true) {
			st = br.readLine();
			if(st == null)break;
			builder.append(st);
		}
		System.out.println(builder.toString());
		if(builder.toString().contains("바보"))
		{
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			CMRespDto<?> cmrDto = new CMRespDto<>(-1, "바보 발견", null);
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(cmrDto);
			writer.println(json);
			return false;
		}
		return true;
	}

}
