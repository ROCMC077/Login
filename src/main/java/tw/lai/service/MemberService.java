package tw.lai.service;

import java.util.List;

import tw.lai.model.Member;

public interface MemberService {

	public void insert(Member member);

	public void delete(Integer id);

	public void update(Member member);
	
	public Member query(Integer id);
	
	public List<Member> queryALL();
}
