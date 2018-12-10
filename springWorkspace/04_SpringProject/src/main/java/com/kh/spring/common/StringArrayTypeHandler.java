package com.kh.spring.common;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

public class StringArrayTypeHandler implements TypeHandler<String[]> {
/*
 * typeHandler를 통해 원하는 자료형을 구현하기 위한
 * 클래스를 작성할 경우 3개의 getter메소드와 1개의 setter메소드를
 * 작성해야 한다.
 * 
 *  <setter>
 *  	String[] -> String
 *  
 *  <getter>
 *  1. ResultSet에서 컬럼 이름으로 가져오기
 *  2. ResultSet에서 컬럼의 인덱스를 기준으로 가져오기
 *  3. CallableStatement(프로시저 호출 메소드)
 *  
 */	@Override
	public void setParameter(PreparedStatement ps, int i, String[] parameter, JdbcType jdbcType) throws SQLException {
		if(parameter != null) {
			ps.setString(i, String.join(",", parameter));
		}else {
			ps.setString(i, "");
		}
		
	}

	@Override
	public String[] getResult(ResultSet rs, String columnName) throws SQLException {
		
		String columnValue = rs.getString(columnName);
		String[] result = columnValue.split(",");
		return result;
	}

	@Override
	public String[] getResult(ResultSet rs, int columnIndex) throws SQLException {
		String columnValue = rs.getString(columnIndex);
		String[] result = columnValue.split(",");
		return result;
	}

	@Override
	public String[] getResult(CallableStatement cs, int columnIndex) throws SQLException {
		String columnValue = cs.getString(columnIndex);
		String[] result = columnValue.split(",");
		return result;
	}

}
