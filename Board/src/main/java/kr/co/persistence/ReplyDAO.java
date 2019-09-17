package kr.co.persistence;

import java.util.List;

import kr.co.domain.PageTO;
import kr.co.domain.ReplyVO;

public interface ReplyDAO {

	void insert(ReplyVO vo);

	void update(ReplyVO vo);

	void delete(int rno);

	void deleteAll(int bno);

	int getAmount(int bno);

	List<ReplyVO> list(int bno, PageTO<ReplyVO> to);
}
