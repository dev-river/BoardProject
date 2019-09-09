package kr.co.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import kr.co.domain.MemberVO;
import kr.co.service.MemberService;

@Controller
@RequestMapping("/member")
@SessionAttributes("login")
public class MemberController {
	
	@Autowired
	private MemberService mservice;
	
	@RequestMapping(value="/insert", method = RequestMethod.GET)
	public void insertUI() {
		
	}
	
	@RequestMapping(value="/insert", method = RequestMethod.POST)
	public String insert(MemberVO vo) {
		mservice.insert(vo);
		return "redirect:/member/list";
	}
	
	@ResponseBody
	@RequestMapping(value="/idcheck", method = RequestMethod.POST, produces = "application/text;charset=utf-8")
	public String idcheck(String id) {
		
		MemberVO what = mservice.idcheck(id);
		
		if(what == null) {
			return "사용가능한 아이디입니다";
		} else {
			return "사용중인 아이디입니다";
		}
	}
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public void list(Model model) {
		List<MemberVO> list = mservice.list();
		model.addAttribute("list", list);
	}
	
	@RequestMapping(value="/read", method = RequestMethod.GET)
	public void read(Model model, String id) {
		
		MemberVO vo = mservice.read(id);
		model.addAttribute("vo", vo);
	}
	
	@RequestMapping(value="/update", method = RequestMethod.GET)
	public void updateUI(Model model, String id) {
		MemberVO vo = mservice.updateUI(id);
		model.addAttribute("vo", vo);
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(MemberVO vo) {
		mservice.update(vo);
		return "redirect:/member/read?id="+vo.getId();
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	public String delete(String id) {
		mservice.delete(id);
		return "redirect:/member/list";
	}
	
	@RequestMapping(value="login", method = RequestMethod.GET)
	public void login() {
		
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String login(MemberVO vo, Model model) {
		MemberVO dbVO = mservice.login(vo.getId());
		
		if(dbVO !=null) {
			if(dbVO.getPw().equals(vo.getPw())) {
				model.addAttribute("login", vo);
				return "redirect:/member/list";
			}else {
				return "redirect:/member/login";
			}
		}else {
			return "redirect:/member/login";
		}
	}
	
	@RequestMapping(value = "/logout",method = RequestMethod.GET)
	public String logout(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "redirect:/member/login";
	}
}
