package com.kitri.cafe.board.dao;

import java.util.List;
import java.util.Map;

import com.kitri.cafe.board.model.BbsDto;

public interface BbsDao {

	int writeArticle(BbsDto bbsDto);
	List<BbsDto> listArticle(Map<String, String> parameter); //받아올 data 많기 때문에 map으로
	BbsDto viewArticle(int seq);
	int modifyArticle(BbsDto bbsDto);
	void deleteArticle(int seq);
}
