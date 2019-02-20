package cn.juni.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.juni.pojo.User;
import cn.juni.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping("/loginRequest")
	public String userLogin(User user,Model model,HttpSession session) {
		User queryUser = userService.login(user);
		System.out.println("-->"+queryUser);
		if(queryUser == null) {
			model.addAttribute("loginErr", "用户名或密码错误");
			return "login";
		}
		session.setAttribute("user", queryUser);
		model.addAttribute("user",queryUser);
		return "redirect:toIndex";
	}
	@RequestMapping("/toIndex")
	public String toIndex() {
		return "index";
	}
	
}
