package tw.lai.controller;



import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.annotations.ApiOperation;
import tw.lai.model.Admin;
import tw.lai.model.Member;
import tw.lai.service.AdminService;

@Controller
public class AdminController {

	@Autowired
	AdminService adminService;
	
	@Autowired
	MemberController memberController;
	
	@Autowired
	private HttpSession session;
	/**
	 * 登入並得到token
	 * */
	@ApiOperation("登入並得到token")
	@GetMapping("/loginfunction")
	public String login(String  admin_name, String admin_password,Model model) {
			Boolean flag = adminService.login(admin_name,admin_password);
			if(flag) {
//				String token =Jwt.crateToken();
//				Jwt.parse(token);
//				session.setAttribute("token",token);
				session.setAttribute("adminName",admin_name);
				Member member = new Member();
				model.addAttribute("member",member);
				List<Member> queryAllMember = memberController.queryAllMember();
				model.addAttribute("queryAllMember",queryAllMember);
				return "loginSuccess";
			}
			Admin admin = new Admin();
			model.addAttribute("admin",admin);
			return "login";
	}
	
	@GetMapping("/login")
	public String loginPage(Model model) {
		Admin admin = new Admin();
		model.addAttribute("admin",admin);
		return "login";
	}
	

	
	

}
