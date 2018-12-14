package com.kh.spring.board.model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;

@Repository
public class BoardDaoImpl implements BoardDao {

	@Autowired
	SqlSessionTemplate sqlSession;
	
	@Override
	public List<Map<String, String>> selectBoardList(int cPage, int numPerPage) {
		//org.apache.ibatis.session.RowBounds.RowBounds(int offset, int limit)
		RowBounds rowBounds = new RowBounds((cPage-1)*numPerPage, numPerPage);
		return sqlSession.selectList("board.selectBoardList",null,rowBounds);
	}

	@Override
	public int selectBoardTotalContents() {
		return sqlSession.selectOne("board.selectBoardTotalContents");
	}

	@Override
	public int insertBoard(Board board) {
		return sqlSession.insert("board.insertBoard",board);
	}

	@Override
	public int insertAttachment(Attachment a) {
		System.out.println("첨부파일 : " + a);
		return sqlSession.insert("board.insertAttachment",a);
	}

	@Override
	public Board selectOneBoard(int boardNo) {
		return sqlSession.selectOne("board.selectOneBoard",boardNo);
	}

	@Override
	public List<Attachment> selectAttachmentList(int boardNo) {
		return sqlSession.selectList("board.selectAttachmentList", boardNo);
	}

}
