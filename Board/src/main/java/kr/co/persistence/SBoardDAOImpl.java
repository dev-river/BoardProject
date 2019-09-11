package kr.co.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.domain.BoardVO;
import kr.co.domain.PageTO;
import kr.co.domain.SPageTO;

@Repository
public class SBoardDAOImpl implements SBoardDAO{
	
	@Autowired
	private SqlSession session;
	
	private final String NS = "kr.co.mapper.sboard";

	@Override
	public List<BoardVO> list(SPageTO sto) {
		
		System.out.println(sto.getSearchType());
		System.out.println(sto.getKeyword());
		
		return session.selectList(NS+".list", sto);
	}

	@Override
	public int amount(SPageTO sto) {

		return session.selectOne(NS+".amount", sto);
	}

	@Override
	public BoardVO read(int bno) {

		return session.selectOne(NS+".read", bno);
	}

	@Override
	public void increaseviewcnt(int bno) {

		session.update(NS+".increaseviewcnt", bno);
	}

	@Override
	public void del(int bno) {
		
		session.delete(NS+".del", bno);
	}

	@Override
	public BoardVO modifyUI(int bno) {

		return session.selectOne(NS+".modifyUI", bno);
	}

	@Override
	public void modify(BoardVO vo) {
		
		session.update(NS+".modify", vo);
	}

	
}
