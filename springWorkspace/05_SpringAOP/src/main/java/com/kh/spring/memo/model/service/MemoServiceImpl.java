package com.kh.spring.memo.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.memo.model.dao.MemoDao;

@Service
public class MemoServiceImpl implements MemoService {

    @Autowired
    MemoDao memoDao;
    
    @Override
    public List<Map<String, String>> selectMemoList() {
        return memoDao.selectMemoList();
    }

    @Override
    public int insertMemo(Map<String, String> map) {
        return memoDao.insertMemo(map);
    }
    
    @Override
	public int deleteMemo(Map<String, String> map) {
		return memoDao.deleteMemo(map);
	}

}