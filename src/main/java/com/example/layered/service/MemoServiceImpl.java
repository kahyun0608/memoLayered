package com.example.layered.service;

import com.example.layered.dto.MemoRequestDto;
import com.example.layered.dto.MemoResponseDto;
import com.example.layered.entity.Memo;
import com.example.layered.repository.MemoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    @Override
    public List<MemoResponseDto> findAllMemos() {

        return memoRepository.findAllMemos();

    }

    @Override
    public MemoResponseDto findMemoById(Long id) {

        Memo memo = memoRepository.findMemoById(id);

        //id가 없을 경우의 예외처리
        if (memo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        return new MemoResponseDto(memo);
    }

    @Override
    public MemoResponseDto updateMemo(Long id, String title, String contents) {

        Memo memo = memoRepository.findMemoById(id);

        //id가 없을 경우의 예외처리
        if (memo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        //title이나 contents 둘 중 하나라도 없으면 예외처리
        if (title == null || contents == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title and content are required values.");
        }

        //Memo 클래스 메서드
        memo.update(title, contents);

        return new MemoResponseDto(memo);
    }

    @Override
    public MemoResponseDto updateTitle(Long id, String title, String contents) {
        //memo 조회
        Memo memo = memoRepository.findMemoById(id);

        if (memo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        //title이나 contents 둘 중 하나라도 없으면 예외처리
        if (title == null || contents != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title is the only required value.");
        }

        memo.updateTitle(title);

        return new MemoResponseDto(memo);
    }

    @Override
    public void deleteMemo(Long id) {

        Memo memo = memoRepository.findMemoById(id);

        if (memo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        memoRepository.deleteMemo(id);

    }


}
