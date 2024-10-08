package tw.lai.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import tw.lai.dao.AdminDao;

@Repository
public class AdminDaoImpl implements AdminDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * login
	 */
	@Override
	public Boolean login(String  adminName,String adminPass) {
		//searching admin >0?
		String sql ="select count(*) from admin where admin_name=? and admin_password = ?";
		Integer sum = jdbcTemplate.queryForObject(sql, Integer.class,adminName,adminPass);
		System.err.println(sum);
		System.err.println((sum>0));
		return (sum>0);
	}

}
