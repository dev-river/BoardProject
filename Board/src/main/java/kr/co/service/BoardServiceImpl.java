package kr.co.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.domain.BoardVO;
import kr.co.domain.PageTO;
import kr.co.persistence.BoardDAO;

@Service
@Transactional
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDAO bDao;
	
	@Override
	public Integer create(BoardVO vo) throws Exception {

		bDao.create(vo);
		
		if(vo.getFiles() != null) {
			for(String filename : vo.getFiles()) {
				bDao.addAttch(filename, vo.getBno());
			}
		}
		
		return vo.getBno();
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

		bDao.clearAttach(bno);
		bDao.del(bno);
	}

	@Override
	public BoardVO modifyUI(int bno) {

		return bDao.modifyUI(bno);
	}

	@Override
	public void modify(BoardVO vo) {
		bDao.modify(vo);
		
		bDao.clearAttach(vo.getBno());
		
		if(vo.getFiles() !=null) {
			
			for(String filename : vo.getFiles()) {
				bDao.addAttch(filename, vo.getBno());
			}
		}
	}

	@Override
	public PageTO<BoardVO> list(PageTO<BoardVO> to) {
		int amount = bDao.getAmount();
		to.setAmount(amount);
		
		System.out.println(":::::::pointcuts::::::::");
		
		List<BoardVO> list = bDao.list(to);
		to.setList(list);
		
		return to;
	}

	@Override
	public int amount() {

		return bDao.getAmount();
	}

	@Override
	public List<String> getAttach(int bno) {

		return bDao.getAttach(bno);
	}

	@Override
	public void deleteAttach(String filename, int bno) {

		bDao.deleteAttach(filename, bno);
	}


}
