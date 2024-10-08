package tw.lai.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.lai.dao.AdminDao;
import tw.lai.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDao adminDao;
	
	@Override
	public Boolean login(String  adminName,String adminPass) {
		return adminDao.login(adminName,adminPass);
	}
	
	
}
