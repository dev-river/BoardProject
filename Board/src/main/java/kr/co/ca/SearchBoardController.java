package kr.co.ca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.domain.BoardVO;
import kr.co.domain.PageTO;
import kr.co.domain.SPageTO;
import kr.co.service.BoardService;

@Controller
@RequestMapping("/sboard")
public class SearchBoardController {

	@Autowired
	private BoardService bService;
	
	@RequestMapping("/list")
	public void list(SPageTO sto, Model model) {
		SPageTO sdbTO = bService.searchList(sto);
		model.addAttribute("to", sdbTO);
		System.out.println(sto.getAmount());
	}
	 
	@RequestMapping("/amount/{perPage}")
	@ResponseBody //ajax니까
	public int list(@PathVariable("perPage") int perPage) {
		int amount = bService.searchAmount();
		return (amount-1)/perPage+1;
	} 
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(Model model, int bno, SPageTO sto) {

		BoardVO vo = bService.read(bno);
		model.addAttribute("vo", vo);
		model.addAttribute("to", sto);
	}
	
	@RequestMapping(value="/del", method = RequestMethod.POST)
	public String delete(int bno, SPageTO sto) throws Exception{
		bService.del(bno);
		return "redirect:/sboard/list?curPage="+sto.getCurPage()+"&perPage="+sto.getPerPage()+"&searchType="+sto.getSearchType()+"&keyword="+sto.getKeyword();
	}
	
	@RequestMapping(value="/modify", method = RequestMethod.GET)
	public void modifyUI(Model model, SPageTO sto, int bno) {
		BoardVO	vo = bService.modifyUI(bno);
		model.addAttribute("vo", vo);
		model.addAttribute("to", sto);
	}
	
	@RequestMapping(value="/modify", method = RequestMethod.POST)
	public String modify(BoardVO vo, SPageTO sto) {
		bService.modify(vo);
		return "redirect:/sboard/read?bno="+vo.getBno()+"&curPage="+sto.getCurPage()+"&perPage"+sto.getPerPage()+"&searchType="+sto.getSearchType()+"&keyword="+sto.getKeyword();
	}
}
