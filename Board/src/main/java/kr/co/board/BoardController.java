package kr.co.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.domain.BoardVO;
import kr.co.domain.PageTO;
import kr.co.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService bService;
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public void createUI() {
		
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(BoardVO vo) throws Exception {
		System.out.println("vo.getBno : "+vo.getBno());
		int reNum = bService.create(vo);
		System.out.println("reNum : "+reNum);
		System.out.println("vo.getBno2 : "+vo.getBno());
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/listall", method = RequestMethod.GET)
	public void listall(Model model) throws Exception{
		List<BoardVO> list = bService.listall();
		model.addAttribute("list", list);
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(Model model, int bno, PageTO to) {

		
		BoardVO vo = bService.read(bno);
		model.addAttribute("vo", vo);
		model.addAttribute("to", to);
	}
	
	@RequestMapping(value="/del", method = RequestMethod.POST)
	public String delete(int bno, PageTO to) throws Exception{
		bService.del(bno);
		return "redirect:/board/list?curPage="+to.getCurPage()+"&perPage="+to.getPerPage();
	}
	
	@RequestMapping(value="/modify", method = RequestMethod.GET)
	public void modifyUI(Model model, PageTO to, int bno) {
		BoardVO	vo = bService.modifyUI(bno);
		model.addAttribute("vo", vo);
		model.addAttribute("to", to);
	}
	
	@RequestMapping(value="/modify", method = RequestMethod.POST)
	public String modify(BoardVO vo, PageTO to) {
		bService.modify(vo);
		return "redirect:/board/read?bno="+vo.getBno()+"&curPage="+to.getCurPage()+"&perPage"+to.getPerPage();
	}
	
	@RequestMapping("/list")
	public void list(PageTO to, Model model) {
		PageTO dbTO = bService.list(to);
		model.addAttribute("to", dbTO);
	}
}
