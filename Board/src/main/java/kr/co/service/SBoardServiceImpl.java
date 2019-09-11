package kr.co.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.domain.BoardVO;
import kr.co.domain.SPageTO;
import kr.co.persistence.SBoardDAO;

@Service
@Transactional
public class SBoardServiceImpl implements SBoardService{

	@Autowired
	private SBoardDAO sbDao;
	
	
	@Override
	public SPageTO list(SPageTO sto) {
		
		int amount = sbDao.amount(sto);
		sto.setAmount(amount);
		
		List<BoardVO> list = sbDao.list(sto);
		/* 작성일에 작성시간 없애기
		 * for(BoardVO x: list) { x.setUpdatedate(x.getUpdatedate().substring(0,10)); }
		 */ 
		sto.setList(list);
		
		return sto;
	}

	@Override
	public int amount(SPageTO sto) {

		return sbDao.amount(sto);
	}

	@Override
	public BoardVO read(int bno) {
		sbDao.increaseviewcnt(bno);
		return sbDao.read(bno);
	}

	@Override
	public void del(int bno) {
		sbDao.del(bno);
	}

	@Override
	public BoardVO modifyUI(int bno) {

		return sbDao.modifyUI(bno);
	}

	@Override
	public void modify(BoardVO vo) {
		
		sbDao.modify(vo);
	}

}
