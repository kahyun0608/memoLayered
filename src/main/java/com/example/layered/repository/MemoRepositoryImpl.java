package com.example.layered.repository;


import com.example.layered.entity.Memo;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Repository
public class MemoRepositoryImpl implements MemoRepository{

    private final Map<Long, Memo> memoList = new HashMap<>();

    @Override
    public Memo saveMemo(Memo memo) {

        //id를 생성해서 달아줌
        Long memoId = memoList.isEmpty() ? 1 : Collections.max(memoList.keySet()) + 1;
        memo.setId(memoId);

        //DB에 id와 함께 저장
        memoList.put(memoId, memo);

        return memo;
    }
}
