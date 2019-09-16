package kr.co.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.domain.ReplyVO;

@Repository
public class SReplyDAOImpl implements SReplyDAO{

	@Autowired
	private SqlSession session;
	
	private final String NS = "kr.co.mapper.sreply";
	
	@Override
	public void insert(ReplyVO vo) {
		
		session.insert(NS+".insert", vo);
	}

}
