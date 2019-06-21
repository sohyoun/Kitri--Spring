package com.kitri.member.model.service;

import java.util.*;

import com.kitri.member.model.*;
import com.kitri.member.model.dao.MemberDaoImpl;

public class MemberServiceImpl implements MemberService{
	//모든 class를 통틀어서 Service는 하나밖에 못만든다. 
	//>>private로 만들고 이 class안에서 생성자 만들었는데 그 생성자를 static으로 함 
	//: single 톤 패턴 (객체를 딱 하나만 만든다.)
	
	private static MemberService memberService;
	
	static {
		memberService = new MemberServiceImpl();
	}
	
	private MemberServiceImpl() {}//private임으로 외부에서 객체 생성못함. 하지만 이 메소드를 못쓰는 것은 아님. 전부 다 static으로 method를 만든다.
	
	public static MemberService getMemberService() {
		return memberService;
	}


	@Override
	public String idCheck(String id) {
		int cnt = MemberDaoImpl.getMemberDao().idCheck(id);
//		System.out.println("cnt : " + cnt);
		String result ="";
		result += "<idcount>\n";
		result += "<cnt>"+cnt+"</cnt>\n";
		result += "</idcount>";
		return result;
	}

	@Override
	public String zipSearch(String doro) {
		String result = "";
		List<ZipcodeDto> list = MemberDaoImpl.getMemberDao().zipSearch(doro);
		result += "<ziplist>\n";
		
		for(ZipcodeDto zipDto : list) {
		result += "	<zip>\n";
		result += "		<zipcode>"+zipDto.getZipcode()+"</zipcode>\n";
		result += "		<address><![CDATA["+zipDto.getSido()+" "+zipDto.getGugun()+" "+zipDto.getUpmyon()+" "+zipDto.getDoro()+" "+zipDto.getBuildingNumber()+" "+zipDto.getSigugunBuildingName()+"]]></address>\n";
		result += "	</zip>\n";
		}
		result += "</ziplist>";
		return result;
	}

	@Override
	public int registerMember(MemberDetailDto memberDetailDto) {
		return MemberDaoImpl.getMemberDao().registerMember(memberDetailDto);
	}

	@Override
	public MemberDto loginMember(String id, String pass) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", id);
		map.put("userpwd", pass);
		return MemberDaoImpl.getMemberDao().loginMember(map);
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
		MemberDaoImpl.getMemberDao().deleteMember(id);
		return 0;
	}

}
