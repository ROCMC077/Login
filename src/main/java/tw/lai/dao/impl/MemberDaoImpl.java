package tw.lai.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import tw.lai.dao.MemberDao;
import tw.lai.model.Member;
import tw.lai.util.GetTime;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void insert(Member member) {
		String sql = "insert into member(member_username ,member_gender,date)" + " values(?,?,?)";
		jdbcTemplate.update(sql, member.getMember_username(), member.getMember_gender(), GetTime.getTime());
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		String sql = "UPDATE member SET member_status=1 where member_id = ?";
		jdbcTemplate.update(sql, id);
	}

	@Override
	public void update(Member member) {
		String sql = " UPDATE member SET member_username=? ,member_gender=? , date = ? WHERE member_id=?";
		jdbcTemplate.update(sql, member.getMember_username(), member.getMember_gender(), GetTime.getTime(), member.getMember_id());

	}

	@Override
	public Member query(Integer id) {
		String sql = "select * from member where member_id=? ";
		RowMapper<Member> rowMapper = new BeanPropertyRowMapper<Member>(Member.class);
		Member memberRow = jdbcTemplate.queryForObject(sql, rowMapper, id);
		System.out.println(memberRow);
		return memberRow;
	}

	@Override
	public List<Member> queryALL() { 
		String sql = "select * from member where member_status=0";
		RowMapper<Member> rowMapper = new BeanPropertyRowMapper<Member>(Member.class);
		List<Member> list = jdbcTemplate.query(sql, rowMapper);
		for (Member member : list) {
			System.out.println(member);
		}
		return list;
	}

}
