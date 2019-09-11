package kr.co.service;

import kr.co.domain.BoardVO;
import kr.co.domain.SPageTO;

public interface SBoardService {

	SPageTO list(SPageTO sto);

	int amount(SPageTO sto);

	BoardVO read(int bno);

	void del(int bno);

	BoardVO modifyUI(int bno);

	void modify(BoardVO vo);
	
}
