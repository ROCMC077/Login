package tw.lai.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import tw.lai.model.Admin;
import tw.lai.model.Member;
import tw.lai.service.MemberService;
import tw.lai.util.Jwt;

@Controller
public class MemberController {

	@Autowired
	MemberService memberService;
	@Autowired
	HttpServletRequest request;
	@Autowired
	private HttpSession session;

	@ApiOperation("insert Member")
	@PostMapping("insertMember")
	public String insertMember(String member_username, String member_gender) {
//		if (checkToken()) {
//			Member m = new Member();
//			m.setMember_username(member_username);
//			m.setMember_gender(member_gender);
//			memberService.insert(m);
//			return "success";
//		} else {
//			return "fail";
//		}
		Member m = new Member();
		m.setMember_username(member_username);
		m.setMember_gender(member_gender);
		memberService.insert(m);
		return "redirect:/queryAll";
	}

	@ApiOperation("delete Member")
	@PostMapping("deleteMember")
	public String deleteMember(Integer member_id) {
//		if (checkToken()) {
//			memberService.delete(member_id);
//			return "success";
//		} else {
//			return "fail";
//		}
		memberService.delete(member_id);
		return "redirect:/queryAll";
	}

	@ApiOperation("update Member")
	@PostMapping("updateMember")
	public String updateMember(String member_username, String member_gender, Integer member_id) {
//		if (checkToken()) {
//			Member m = new Member();
//			m.setMember_username(member_username);
//			m.setMember_gender(member_gender);
//			m.setMember_id(member_id);
//			memberService.update(m);
//			return "redirect:/queryAll";
//		} else {
//			return "redirect:/queryAll";
//		}
		
		Member m = new Member();
		m.setMember_username(member_username);
		m.setMember_gender(member_gender);
		m.setMember_id(member_id);
		memberService.update(m);
		return "redirect:/queryAll";

	}

	@ApiOperation("query one Member")
	@GetMapping("queryOneMember")
	public Member queryOneMember(Integer member_id) {
		session.setAttribute("member1",memberService.query(member_id));
		return memberService.query(member_id);

	}

	@ApiOperation("query All Member")
	@GetMapping("queryAllMember")
	public List<Member> queryAllMember() {
		return memberService.queryALL();

	}

	/**
	 * Swagger檢查當前有無token
	 */
	@ApiOperation("檢查當前有無token")
	@GetMapping("/checkToken")
	public Boolean checkToken() {
		
		boolean flag = Jwt.checkToken(request);
		if (flag) {
			return flag;
		}
		return flag;
	}
	


	@GetMapping("/queryAll")
	public String login( Model model) {
		Member member = new Member();
		model.addAttribute("member", member);
		List<Member> queryAllMember =queryAllMember();
		model.addAttribute("queryAllMember", queryAllMember);
		return "loginSuccess";

	}

}
