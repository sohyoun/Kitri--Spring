package com.kitri.cafe.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kitri.cafe.board.model.MemoDto;
import com.kitri.cafe.board.service.MemoService;
import com.kitri.cafe.member.model.MemberDto;

@RestController
@RequestMapping("/memo")
public class MemoController {
	
	@Autowired
	private MemoService memoService;
	
	@RequestMapping(method = RequestMethod.POST)
	public String write(@RequestBody MemoDto memoDto, HttpSession session) {
		//Json으로 받아온거는 @RequestBody로 받는다.
		//consumes="application/json"
		//headers={Content-type=application/jacson}
//		System.out.println(memoDto.getMcontent());
		MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
		if(memberDto != null) {
			memoDto.setId(memberDto.getId());
			memoDto.setName(memberDto.getName());
			memoService.writeMemo(memoDto);
			String json = memoService.listMemo(memoDto.getSeq());
			return json;
		}
		
		return "";
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(int seq) {
		//, consumes="application/json", headers = "{Content-type=application/jacson}"
		String json = memoService.listMemo(seq);
		return json;
	}
	

	//memo/글번호~~
	@RequestMapping(value="/{seq}/{mseq}", method = RequestMethod.DELETE)
	public String delete(@PathVariable(name="seq") int seq, @PathVariable(name="mseq") int mseq) {
		//, consumes="application/json", headers = "{Content-type=application/jacson}"
		String json = memoService.deleteMemo(seq, mseq);
//		String json = memoService.listMemo(seq);
		return json;
	}
}
