package kr.co.persistence;

import java.util.List;

import kr.co.domain.MemberVO;

public interface MemberDAO {
	
	void insert(MemberVO vo);
	
	MemberVO idcheck(String id);

	List<MemberVO> list();

	MemberVO read(String id);

	MemberVO updateUI(String id);

	void update(MemberVO vo);

	void delete(String id);

	MemberVO login(String id);
}
