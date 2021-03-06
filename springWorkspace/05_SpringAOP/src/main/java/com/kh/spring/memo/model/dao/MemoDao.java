package com.kh.spring.memo.model.dao;

import java.util.List;
import java.util.Map;

public interface MemoDao {
	
    List<Map<String, String>> selectMemoList();

    int insertMemo(Map<String, String> map);
    
    int deleteMemo(Map<String, String> map);
}
