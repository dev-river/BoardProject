package kr.co.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.domain.PageTO;
import kr.co.domain.ReplyVO;
import kr.co.persistence.SReplyDAO;

@Service
@Transactional
public class SReplyServiceImpl implements SReplyService{

	@Autowired
	private SReplyDAO sdao;
	
	@Override
	public void insert(ReplyVO vo) {

		sdao.insert(vo);
	}

	@Override
	public List<ReplyVO> list(int bno) {

		return sdao.list(bno);
	}

	@Override
	public void update(ReplyVO vo) {

		sdao.update(vo);
	}

	@Override
	public void delete(int rno) {

		sdao.delete(rno);
	}

	@Override
	public void deleteAll(int bno) {
		
		sdao.deleteAll(bno);
	}

	@Override
	public PageTO<ReplyVO> list(int bno, int replyPage) {
		int amount = sdao.getAmount(bno);
		PageTO<ReplyVO> to = new PageTO<ReplyVO>(replyPage);
		to.setAmount(amount);
		to.setPerPage(5);
		List<ReplyVO> list = sdao.list(bno, to);
		to.setList(list);
		
		return to;
	}

}
