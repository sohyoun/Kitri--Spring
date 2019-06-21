package com.kitri.admin.model.dao;

import java.sql.*;
import java.util.*;

import org.apache.ibatis.session.SqlSession;

import com.kitri.admin.model.service.AdminService;
import com.kitri.admin.model.service.AdminServiceImpl;
import com.kitri.member.model.MemberDetailDto;
import com.kitri.member.model.MemberDto;
import com.kitri.sqlmap.MyBatisConfiguration;

public class AdminDaoImpl implements AdminDao{
	
	private final String NAME_SPACE = "com.kitri.admin.model.dao.AdminDao";
	
	private static AdminDao adminDao;
	
	static {
		adminDao = new AdminDaoImpl();
	}
	
	private  AdminDaoImpl() {}
	


	public static AdminDao getAdminDao() {
		return adminDao;
	}



	@Override
	public List<MemberDetailDto> getMemberList(Map<String, String> map) {
		SqlSession session = MyBatisConfiguration.getSqlSessionFactory().openSession();
		try {
			return session.selectList(NAME_SPACE + ".getMemberList",map);			
		} finally {
			session.close();
		}
	}


	

}
