package site.metacoding.red.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.handler.ex.MyApiException;
import site.metacoding.red.service.UsersService;
import site.metacoding.red.web.dto.request.users.JoinDto;
import site.metacoding.red.web.dto.request.users.LoginDto;
import site.metacoding.red.web.dto.request.users.UpdateDto;
import site.metacoding.red.web.dto.response.CMRespDto;

@RequiredArgsConstructor
@Controller
public class UsersController {

	private final UsersService usersService;
	private final HttpSession session;

	// http://localhost:8000/users/usernameSameCheck?username=ssar
	@GetMapping("users/usernameSameCheck")
	public @ResponseBody CMRespDto<Boolean> usernameSameCheck(String username) {
		boolean isSame = usersService.유저네임중복확인(username);
		// return은 JSON으로
		// HttpMessageConverter발동 - 자동으로 json type으로 변경해준다.
		// HttpMessageConverter는 Restcontroller나 @ResponseBody의 어노테이션에의해 발동 된다.
		return new CMRespDto<>(1, "성공", isSame);
	}

	@GetMapping("/joinForm")
	public String joinForm() {
		return "users/joinForm";
	}

	@GetMapping("/loginForm")
	public String loginForm(HttpServletRequest request, Model model) { // 쿠키 배워보기
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("username"))
				model.addAttribute(cookie.getName(), cookie.getValue());
		}
		return "users/loginForm";
	}

	@PostMapping("/api/join")
	public @ResponseBody CMRespDto<?> join(@RequestBody @Valid JoinDto joinDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			System.out.println("에러가 있습니다.");
			FieldError felist = bindingResult.getFieldError();
			throw new MyApiException(felist.getDefaultMessage());
		} else {
			System.out.println("에러가 없습니다.");
		}
		usersService.회원가입(joinDto); // service에게 (책임)위임한다.
		return new CMRespDto<>(1, "회원가입성공", null);
	}

	@PostMapping("/api/login")
	// viewResolver 발동 안함 - @ResponseBody를 붙여줘서 -> data를 return한다.
	public @ResponseBody CMRespDto<?> login(@RequestBody LoginDto loginDto, HttpServletResponse response) {

		Users principal = usersService.로그인(loginDto); // service에게 (책임)위임한다.

		// historyback을 해서 정보를 남겨놔야함 - UX(편의성)
		if (principal == null) {
			return new CMRespDto<>(-1, "로그인 실패", null);
		}
		if (loginDto.isRemember()) {
			Cookie cookie = new Cookie("username", loginDto.getUsername());
			cookie.setMaxAge(60 * 60 * 24); // 60*60*24
			response.addCookie(cookie);
			// response.setHeader("Set-Cookie", "username="+loginDto.getUsername() + ";
			// HttpOnly");
		}
		session.setAttribute("principal", principal);
		return new CMRespDto<>(1, "로그인 성공", null);
	}

	// 인증 필요
	@GetMapping("/s/users/{id}")
	public String updateForm(@PathVariable Integer id, Model model) {
		Users usersPS = (Users) session.getAttribute("principal");
		model.addAttribute("users", usersPS);
		return "users/updateForm";
	}

	// 인증 필요
	@PutMapping("/s/api/users/{id}")
	public @ResponseBody CMRespDto<?> update(@PathVariable Integer id, @RequestBody UpdateDto updateDto) {
		Users usersPS = usersService.회원수정(id, updateDto);
		session.setAttribute("principal", usersPS);
		return new CMRespDto<>(1, "회원수정 성공", null);
	}

	// 인증 필요
	@DeleteMapping("/s/api/users/{id}")
	public @ResponseBody CMRespDto<?> delete(@PathVariable Integer id, HttpServletResponse response) {
		usersService.회원탈퇴(id);

		Cookie cookie = new Cookie("username", null);
		cookie.setMaxAge(0);
		// cookie.setPath("/"); // cookie가 생성된 path의 같은 path로 바꿔줘야 cookie가 바뀐다.
		response.addCookie(cookie);
		session.invalidate();
		return new CMRespDto<>(1, "회원탈퇴성공", null);
	}

	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/loginForm";
	}
}
