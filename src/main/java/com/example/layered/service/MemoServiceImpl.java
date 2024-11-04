package com.example.layered.service;

import com.example.layered.dto.MemoRequestDto;
import com.example.layered.dto.MemoResponseDto;
import com.example.layered.entity.Memo;
import com.example.layered.repository.MemoRepository;
import org.springframework.stereotype.Service;

@Service
public class MemoServiceImpl implements MemoService {

    //repository 사용위해 필드와 생성자 지정
    private final MemoRepository memoRepository;

    public MemoServiceImpl(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    @Override
    public MemoResponseDto saveMemo(MemoRequestDto dto) {

        //요청받은 데이터로 Memo 객체 생성 -> Id 없음(MemoRequestDto는 id값이 없기 때문)
        Memo memo = new Memo(dto.getTitle(), dto.getContents());

        // DB저장 ->레파지토리의 영역이므로 넘김
        Memo savedMemo = memoRepository.saveMemo(memo);

        //controller로 전달될 때 dto 형태로 전달
        return new MemoResponseDto(savedMemo);
    }
}
