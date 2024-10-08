package tw.lai.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.lai.dao.MemberDao;
import tw.lai.model.Member;
import tw.lai.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDao memberDao;

	@Override
	public void insert(Member member) {
		memberDao.insert(member);
	}

	@Override
	public void delete(Integer id) {
		memberDao.delete(id);

	}

	@Override
	public void update(Member member) {
		memberDao.update(member);
	}

	@Override
	public Member query(Integer id) {
		return memberDao.query(id);
	}

	@Override
	public List<Member> queryALL() {
		return memberDao.queryALL();
	}

}
