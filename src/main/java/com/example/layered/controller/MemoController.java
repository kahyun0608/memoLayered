package com.example.layered.controller;

import com.example.layered.dto.MemoRequestDto;
import com.example.layered.dto.MemoResponseDto;
import com.example.layered.entity.Memo;
import com.example.layered.service.MemoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController //@Controller + @ResponseBody
@RequestMapping("/memos") //Prefix
public class MemoController {

    //최초로 설정한 MemoService가 애플리케이션이 끝날 때까지 계속 유지
    private final MemoService memoService;

    //위의 생성자 주입
    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    //메모 생성
    @PostMapping
    public ResponseEntity<MemoResponseDto> createMemo(@RequestBody MemoRequestDto dto) {

        //Service Layer 호출, 응답
        return new ResponseEntity<>(memoService.saveMemo(dto), HttpStatus.CREATED);

    }

    //전체 조회
    @GetMapping
    public List<MemoResponseDto> findAllMemos() {

        return memoService.findAllMemos();
    }

    //단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<MemoResponseDto> findMemoById(@PathVariable Long id) {

        return new ResponseEntity<>(memoService.findMemoById(id), HttpStatus.OK);
    }

    //단건 수정 기능
    @PutMapping("/{id}")
    public ResponseEntity<MemoResponseDto> updateMemo(
            @PathVariable Long id,
            @RequestBody MemoRequestDto dto
    ){
        return new ResponseEntity<>(memoService.updateMemo(id, dto.getTitle(), dto.getContents()), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MemoResponseDto> updateTitle(
            @PathVariable Long id,
            @RequestBody MemoRequestDto dto
    ){
        return new ResponseEntity<>(memoService.updateTitle(id, dto.getTitle(), dto.getContents()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemo(@PathVariable Long id) {
        memoService.deleteMemo(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
