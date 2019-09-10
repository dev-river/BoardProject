package kr.co.ca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.service.BoardService;

@Controller
@RequestMapping("/sboard")
public class SearchBoardController {

	@Autowired
	private BoardService bService;
	
	@RequestMapping("/list")
	public void list() {
		
	}
	
}
