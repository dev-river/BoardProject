package kr.co.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.domain.PageTO;
import kr.co.domain.ReplyVO;
import kr.co.persistence.ReplyDAO;

@Service
@Transactional
public class ReplyServiceImpl implements ReplyService{

	@Inject
	private ReplyDAO dao;
	
	@Override
	public void insert(ReplyVO vo) {
		dao.insert(vo);
	}

	@Override
	public void update(ReplyVO vo) {

		dao.update(vo);
	}

	@Override
	public void delete(int rno) {

		dao.delete(rno);
	}

	@Override
	public void deleteAll(int bno) {

		dao.deleteAll(bno);
	}

	@Override
	public PageTO<ReplyVO> list(int bno, int replyPage) {
		int amount = dao.getAmount(bno);
		PageTO<ReplyVO> to = new PageTO<ReplyVO>(replyPage);
		to.setAmount(amount);
		to.setPerPage(5);
		List<ReplyVO> list = dao.list(bno, to);
		to.setList(list);
		
		return to;
	}

}
