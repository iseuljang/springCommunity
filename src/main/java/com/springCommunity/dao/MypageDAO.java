package com.springCommunity.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springCommunity.vo.MypageVO;

@Repository
public class MypageDAO { 
	
	@Autowired
	SqlSession sqlSession;

	private final String namespace = "com.springCommunity.mapper.mypageMapper.";
	
	public int userUpdate (MypageVO mypageVO) {
		return sqlSession.update(namespace+"userUpdate",mypageVO);
	}
	
	public int userPwUpdate (MypageVO mypageVO) {
		return sqlSession.update(namespace+"userPwUpdate",mypageVO);
	}
	
	public MypageVO selectOne(String user_id) {
		return sqlSession.selectOne(namespace+"selectOne",user_id);
	}
	
	public int insertBenefit (MypageVO mypageVO) {
		return sqlSession.insert(namespace+"insertBenefit",mypageVO);
	}
	
	public int insertAttach (MypageVO mypageVO) {
		return sqlSession.insert(namespace+"insertAttach",mypageVO);
	}
	
	public int insertMedical (MypageVO mypageVO) {
		return sqlSession.insert(namespace+"insertMedical",mypageVO);
	}
	
	public MypageVO selectOneRequest (int request_no) {
		return sqlSession.selectOne(namespace+"selectOneRequest",request_no);
	}
}
