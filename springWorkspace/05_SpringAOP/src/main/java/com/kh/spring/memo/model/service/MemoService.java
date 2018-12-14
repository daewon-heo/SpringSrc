package com.kh.spring.memo.model.service;

import java.util.*;

public interface MemoService {

    List<Map<String, String>> selectMemoList();

    int insertMemo(Map<String, String> map);
    
    int deleteMemo(Map<String, String> map);

}
