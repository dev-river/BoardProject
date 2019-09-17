package kr.co.service;

import kr.co.domain.PageTO;
import kr.co.domain.ReplyVO;

public interface ReplyService {

	void insert(ReplyVO vo);

	void update(ReplyVO vo);

	void delete(int rno);

	void deleteAll(int bno);

	PageTO<ReplyVO> list(int bno, int replyPage);
}
