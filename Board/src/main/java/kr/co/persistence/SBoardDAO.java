package kr.co.persistence;

import java.util.List;

import kr.co.domain.BoardVO;
import kr.co.domain.SPageTO;

public interface SBoardDAO {

	int amount(SPageTO sto);

	List<BoardVO> list(SPageTO sto);

	BoardVO read(int bno);

	void increaseviewcnt(int bno);

	void del(int bno);

	BoardVO modifyUI(int bno);

	void modify(BoardVO vo);
	
}
