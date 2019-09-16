package kr.co.ca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.domain.ReplyVO;
import kr.co.service.ReplyService;

@RestController
@RequestMapping("/replies")
public class ReplyController {
	
	@Autowired
	private ReplyService service;
	
	@RequestMapping(value="", method = RequestMethod.POST)
	public ResponseEntity<String> insert(@RequestBody ReplyVO vo) {
		ResponseEntity<String> entity = null;
		
		try {
			service.insert(vo);
			entity = new ResponseEntity<String>("INSERT_SUCCESS", HttpStatus.CREATED);
		} catch (Exception e) {
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			// ResponseEntity를 사용하면 결과를 조작할 수 있다
		}
		
		return entity;
	}
	
	
}
