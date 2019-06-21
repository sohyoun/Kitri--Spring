package com.kitri.member.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kitri.member.model.MemberDetailDto;
import com.kitri.member.model.MemberDto;
import com.kitri.member.model.ZipcodeDto;
import com.kitri.member.model.dao.MemberDao;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDao memberDao;
	
	@Override
	public String idCheck(String id) {
		int cnt = memberDao.idCheck(id);
//		System.out.println("cnt : " + cnt);
		JSONObject json = new JSONObject();
		json.put("idcount", cnt);
		return json.toString(); //{"idcount" : 0}
		//dispatcher는 WEB-INF/views/{"idcount" : 0}.jsp 로 인식할 것이다.
		
	}

	@Override
	public String zipSearch(String doro) {
		List<ZipcodeDto> list = memberDao.zipSearch(doro);
		JSONObject json = new JSONObject();
		//1)바로 list 담기 : 다 따로 받아짐. 순서 뒤죽박죽 하지만 상관X (Map의 특성)>>이름으로 불러올것이어서
		JSONArray jarray = new JSONArray(list);
		//2)하나씩 이어서 담기
//		JSONArray jarray = new JSONArray();
//		for(ZipcodeDto zipcodeDto : list) {
//			JSONObject zipcode = new JSONObject();
//			zipcode.put("zipcode", zipcodeDto.getZipcode());
//			zipcode.put("address", zipcodeDto.getSido() + " " + 
//							zipcodeDto.getGugun() + " " + 
//							zipcodeDto.getUpmyon() + " " + 
//							zipcodeDto.getDoro() + " " + 
//							zipcodeDto.getBuildingNumber() + " " + 
//							zipcodeDto.getSigugunBuildingName());
//			jarray.put(zipcode);
//		}
		
		json.put("ziplist", jarray);
//		System.out.println(json.toString());
		return json.toString();
	}
//	{"ziplist",[{"zipcode" : "12345", "address" : "서울시..."},
//	            {"zipcode" : "12345", "address" : "서울시..."},
//	            {"zipcode" : "12345", "address" : "서울시..."}
//			]
//	}

	@Override
	public int registerMember(MemberDetailDto memberDetailDto) {
		return memberDao.registerMember(memberDetailDto);
	}

	@Override
	public MemberDto loginMember(Map<String, String> map) {
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("userid", id);
//		map.put("userpwd", pass);
		return memberDao.loginMember(map);
	}

	@Override
	public MemberDetailDto getMember(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int modifyMember(MemberDetailDto memberDetailDto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(String id) {
		memberDao.deleteMember(id);
		return 0;
	}

}
