package kr.co.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.domain.BoardVO;
import kr.co.domain.PageTO;

@Repository
public class BoardDAOImpl implements BoardDAO{
	
	@Autowired
	private SqlSession session;
	
	private final String NS = "kr.co.mapper.board";

	@Override
	public Integer create(BoardVO vo) throws Exception {

		return session.insert(NS+".create", vo);
	}

	@Override
	public List<BoardVO> listall() {

		return session.selectList(NS+".listall");
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

	@Override
	public int getAmount() {
		
		return session.selectOne(NS+".getAmount");
	}

	@Override
	public List<BoardVO> list(PageTO<BoardVO> to) {

		return session.selectList(NS+".list", to);
	}


}
