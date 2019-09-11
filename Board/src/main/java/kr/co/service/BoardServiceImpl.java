package kr.co.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.domain.BoardVO;
import kr.co.domain.PageTO;
import kr.co.domain.SPageTO;
import kr.co.persistence.BoardDAO;

@Service
@Transactional
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDAO bDao;
	
	@Override
	public Integer create(BoardVO vo) throws Exception {

		return bDao.create(vo);
	}

	@Override
	public List<BoardVO> listall() {

		return bDao.listall();
	}

	@Override
	public BoardVO read(int bno) {
		bDao.increaseviewcnt(bno);
		return bDao.read(bno);
	}

	@Override
	public void del(int bno) {

		bDao.del(bno);
	}

	@Override
	public BoardVO modifyUI(int bno) {

		return bDao.modifyUI(bno);
	}

	@Override
	public void modify(BoardVO vo) {
		bDao.modify(vo);
	}

	@Override
	public PageTO list(PageTO to) {
		int amount = bDao.getAmount();
		to.setAmount(amount);
		
		List<BoardVO> list = bDao.list(to);
		to.setList(list);
		
		return to;
	}

	@Override
	public int amount() {

		return bDao.getAmount();
	}

	@Override
	public SPageTO searchList(SPageTO sto) {
		int amount = bDao.searchAmount();
		sto.setAmount(amount);
		
		List<BoardVO> list = bDao.searchList(sto);
		sto.setList(list);
				
		return sto;
	}

	@Override
	public int searchAmount() {

		return bDao.searchAmount();
	}

}
