package com.kitri.cafe.board.service;

import java.util.List;
import java.util.Map;

import com.kitri.cafe.board.model.AlbumDto;

public interface AlbumService {
	
	int writeArticle(AlbumDto albumDto);
	List<AlbumDto> listArticle(Map<String, String> parameter); //받아올 data 많기 때문에 map으로
	AlbumDto viewArticle(int seq);
	int modifyArticle(AlbumDto albumDto);
	void deleteArticle(int seq);
}
