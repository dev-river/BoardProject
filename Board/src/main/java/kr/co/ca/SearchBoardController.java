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
import kr.co.service.SBoardService;

@Controller
@RequestMapping("/sboard")
public class SearchBoardController {

	@Autowired
	private SBoardService sbService;
	
	@RequestMapping("/list")
	public void list(SPageTO<BoardVO> sto, Model model) {
		SPageTO<BoardVO> dbsto = sbService.list(sto);
		model.addAttribute("to", dbsto); //"to"를 써야 기존에 만들어뒀던걸 활용하기 쉬움
	}
	 
	
	@RequestMapping("/amount/{perPage}")
	@ResponseBody //ajax니까
	public int list(@PathVariable("perPage") int perPage, SPageTO<BoardVO> sto){ 
		int amount = sbService.amount(sto);
		int totalPage = (amount-1)/perPage+1; 
		return totalPage;
	}
	  
	@RequestMapping(value = "/read", method = RequestMethod.GET) 
	public void	read(Model model, int bno, SPageTO<BoardVO> sto) {
	  BoardVO svo = sbService.read(bno);
	  model.addAttribute("vo", svo);
	  model.addAttribute("to", sto);
	}
	  
	@RequestMapping(value="/del", method = RequestMethod.POST) 
	public String delete(int bno, SPageTO<BoardVO> sto) throws Exception{ 
		sbService.del(bno);
		
		StringBuffer sb = new StringBuffer();
		sb.append("redirect:/sboard/list?curPage=");
		sb.append(sto.getCurPage());
		sb.append("&perPage=");
		sb.append(sto.getPerPage());
		sb.append("&searchType=");
		sb.append(sto.getSearchType());
		sb.append("&keyword=");
		sb.append(sto.getKeyword());
				
		return sb.toString();
//		return "redirect:/sboard/list?curPage="+sto.getCurPage()+"&perPage="+sto.getPerPage()+"&searchType="+sto.getSearchType()+"&keyword="+sto.getKeyword(); 이걸로 해도되긴함 

	}
	  
	@RequestMapping(value="/modify", method = RequestMethod.GET) 
	public void	modifyUI(Model model, SPageTO<BoardVO> sto, int bno) {
		BoardVO vo = sbService.modifyUI(bno); 
		model.addAttribute("vo", vo);
		model.addAttribute("to", sto); 
	}
	  
	@RequestMapping(value="/modify", method = RequestMethod.POST)
	public String modify(BoardVO vo, SPageTO<BoardVO> sto) { 
		sbService.modify(vo);
		
		StringBuffer sb = new StringBuffer();
		sb.append("redirect:/sboard/read?bno=");
		sb.append(vo.getBno());
		sb.append("&CurPage=");
		sb.append(sto.getCurPage());
		sb.append("&perPage=");
		sb.append(sto.getPerPage());
		sb.append("&searchType=");
		sb.append(sto.getSearchType());
		sb.append("&keyword=");
		sb.append(sto.getKeyword());
		
		return sb.toString();
//		return "redirect:/sboard/read?bno="+vo.getBno()+"&curPage="+sto.getCurPage()+"&perPage"+sto.getPerPage()+"&searchType="+sto.getSearchType()+"&keyword="+sto.getKeyword();
	}
	 
}
