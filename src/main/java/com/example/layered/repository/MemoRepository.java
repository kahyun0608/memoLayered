package com.example.layered.repository;

import com.example.layered.dto.MemoResponseDto;
import com.example.layered.entity.Memo;

import java.util.List;

public interface MemoRepository {

    //id가 없는 상태로 memo가 전달되어 매개변수에 담김
    Memo saveMemo(Memo memo);
    List<MemoResponseDto> findAllMemos();

    Memo findMemoById(Long id);

    void deleteMemo(Long id);
}
