package com.cxy.web.controller;

import com.cxy.frame.util.MessageStreamResult;
import com.cxy.frame.web.BaseController;
import com.cxy.web.annotation.SystemLogAfterController;
import com.cxy.web.annotation.SystemLogBeforeController;
import com.cxy.web.po.BsUser;
import com.cxy.web.service.BsUserService;
import com.cxy.web.util.MyMD5Util;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



@Controller
@RequestMapping("/bsLogin")
public class BsLoginController extends BaseController {

	@Autowired
	private BsUserService bsUserService;

	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	@SystemLogAfterController(description = "用户登录系统")
	public String doLogin(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String message_login = "1";
		String username = request.getParameter("username");
		String password = MyMD5Util.getEncryptedStr(request.getParameter("password"));
		UsernamePasswordToken token = new UsernamePasswordToken(username,password);
		token.setRememberMe(true);
		System.out.println("为了验证登录用户而封装的token为"+ ReflectionToStringBuilder.toString(token,ToStringStyle.MULTI_LINE_STYLE));
		// 获取当前的Subject
		Subject currentUser = SecurityUtils.getSubject();
		try {
			// 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
			// 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
			// 所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
			System.out.println("对用户[" + username + "]进行登录验证..验证开始");
			currentUser.login(token);
			System.out.println("对用户[" + username + "]进行登录验证..验证通过");
		} catch (UnknownAccountException uae) {
			System.out.println("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
			message_login= "未知账户";
		} catch (IncorrectCredentialsException ice) {
			System.out.println("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
			message_login= "密码不正确";
		} catch (LockedAccountException lae) {
			System.out.println("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
			message_login= "账户已锁定";
		} catch (ExcessiveAttemptsException eae) {
			System.out.println("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
			message_login= "用户名或密码错误次数过多";
		} catch (AuthenticationException ae) {
			// 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
			System.out.println("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
			ae.printStackTrace();
			message_login= "用户名或密码不正确";
		}
		// 验证是否登录成功
		if (currentUser.isAuthenticated()) {
			System.out.println("用户[" + username+ "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
		} else {
			token.clear();
		}
		try {
			MessageStreamResult.msgStreamResult(response,message_login);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/logout")
	@SystemLogBeforeController(description = "用户注销系统")
	public ModelAndView logout(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		request.getSession().setAttribute("bsUser", null);
		return new ModelAndView("redirect:/login.jsp");
	}

	/*@RequestMapping(value = "/index")
	public String index(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		return "main";
	}*/
	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		return new ModelAndView("index");
	}
	

	@RequestMapping(value = "/updatePwd")
	@SystemLogBeforeController(description = "用户更新登录密码")
	public ModelAndView updatePwd(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		// 从session中获取userid
		BsUser bsUser = null;
		if (request.getSession().getAttribute("bsUser") == null) {
			String contextPath = request.getContextPath();
			try {
				response.sendRedirect(contextPath + "/login.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			bsUser = (BsUser) request.getSession().getAttribute("bsUser");
		}
		Map paramMap = new HashMap();
		paramMap.put("newPassWord",MyMD5Util.getEncryptedStr(request.getParameter("newPassWord")));
		paramMap.put("oldPassWord",MyMD5Util.getEncryptedStr(request.getParameter("oldPassWord")));
		paramMap.put("id", bsUser.getId());
		try {
			bsUserService.updatePwd(paramMap);
			MessageStreamResult.msgStreamResult(response, "0");
			request.getSession().setAttribute("bsUser", null);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				MessageStreamResult.msgStreamResult(response, "1");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}
}
