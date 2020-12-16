package com.atguigu.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.bean.User;
import com.atguigu.service.UserService;
import com.google.code.kaptcha.Constants;

@RequestMapping("/user")
@Controller
public class UserController{

	@Autowired
	private UserService userService;
	
	/**
	 * 登录
	 */
	@RequestMapping("/login")
	public String login(User user, HttpServletRequest request, Model model) throws ServletException, IOException {
		// 封装参数
		user = userService.login(user);
		if (user == null) {
			// 登录失败
			model.addAttribute("msg", "用户名密码错误！");
			return "user/login";
		}else {
			// 登录成功
			// 将用户信息放入session中
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			return "redirect:/pages/user/login_success.jsp";
		}
	}
	
	/**
	 * 注册
	 */
	@RequestMapping("/regist")
	public String regist(User user, HttpServletRequest request, Model model) throws ServletException, IOException {
		// 校验验证码
		HttpSession session = request.getSession();
		// 获取放入session中的验证码
		String token = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if (user.getCode() != null && !user.getCode().equals(token)) {
			model.addAttribute("msg", "验证码不匹配！");
			return "user/regist";
		}
		
		if (!userService.regist(user)) {
			// 注册失败
			model.addAttribute("msg", "注册失败！");
			return "user/regist";
		}else {
			// 注册成功
			return "redirect:/pages/user/regist_success.jsp";
		}
	}
	
	/**
	 * 验证用户名是否已存在
	 * ajax调用
	 */
	@ResponseBody
	@RequestMapping("/checkUser")
	public String checkUser(User user) throws ServletException, IOException {
		// 响应给前端：1：用户名已存在；0：用户名不存在
		String result = userService.userIsExist(user) ? "1" : "0";
		return result;
	}

	/**
	 * 登出
	 */
	@RequestMapping("/logout")
	protected String logout(HttpServletRequest request) throws ServletException, IOException {
		// 强制session失效
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/index.jsp";
	}
	
}
