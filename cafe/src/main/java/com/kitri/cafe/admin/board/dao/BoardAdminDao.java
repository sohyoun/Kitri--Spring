package com.kitri.cafe.admin.board.dao;

import java.util.List;

import com.kitri.cafe.admin.board.model.BoardListDto;
import com.kitri.cafe.admin.board.model.BoardTypeDto;
import com.kitri.cafe.admin.board.model.CategoryDto;

public interface BoardAdminDao { 
	List<BoardListDto> getBoardMenuList(int ccode); //카테고리종류 가져오기
	
	//카테고리 만들기
	List<CategoryDto> getCategoryList(); //카테고리 목록 보기
	void makeCategory(CategoryDto categoryDto); //카테고리 만들기
	
	//게시판 만들기
	List<BoardTypeDto> getBoardTypeList(); //게시판타입 가져오기
	void makeBoard(BoardListDto boardListDto);
}
