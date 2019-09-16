package kr.co.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.domain.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO{

	@Autowired
	private SqlSession session;
	
	private final String NS = "kr.co.mapper.reply";
	
	@Override
	public void insert(ReplyVO vo) {

		session.insert(NS+".insert", vo);
	}

}
