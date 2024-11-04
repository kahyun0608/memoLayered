package com.example.layered.entity;

import com.example.layered.dto.MemoRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@AllArgsConstructor
public class Memo {

    @Setter
    private Long id;
    private String title;
    private String contents;

    //생성자
    public Memo(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public void updateTitle(String title, String contents) {
        this.title = title;
    }
}

