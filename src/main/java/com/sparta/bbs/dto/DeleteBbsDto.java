package com.sparta.bbs.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeleteBbsDto {
    private String msg;
    private int value;
    public DeleteBbsDto(String msg, int value) {
        this.msg = msg;
        this.value = value;
    }

    //메서드
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
