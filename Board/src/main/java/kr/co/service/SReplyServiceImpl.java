package kr.co.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.domain.ReplyVO;
import kr.co.persistence.SReplyDAO;

@Service
@Transactional
public class SReplyServiceImpl implements SReplyService{

	@Autowired
	private SReplyDAO sdao;
	
	@Override
	public void insert(ReplyVO vo) {

		sdao.insert(vo);
	}

}
