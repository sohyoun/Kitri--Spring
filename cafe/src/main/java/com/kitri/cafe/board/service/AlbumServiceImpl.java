package com.kitri.cafe.board.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kitri.cafe.board.dao.AlbumDao;
import com.kitri.cafe.board.model.AlbumDto;

@Service
public class AlbumServiceImpl implements AlbumService {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int writeArticle(AlbumDto albumDto) {
		
		return sqlSession.getMapper(AlbumDao.class).writeArticle(albumDto);
//		return 0;
	}

	@Override
	public List<AlbumDto> listArticle(Map<String, String> parameter) {
		
		return null;
	}

	@Override
	public AlbumDto viewArticle(int seq) {
		
		return null;
	}

	@Override
	public int modifyArticle(AlbumDto albumDto) {
		
		return 0;
	}

	@Override
	public void deleteArticle(int seq) {
		

	}

}
