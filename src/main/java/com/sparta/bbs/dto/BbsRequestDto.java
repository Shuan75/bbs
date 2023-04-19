package com.sparta.bbs.dto;

import com.sparta.bbs.entity.Bbs;
import lombok.Getter;

@Getter
public class BbsRequestDto {
    private String content;
    private String password;
    private String title;
    private String author;


    public Bbs toEntity() {
        // builder
        // 필요한 데이터만 설정할 수 있음
        // 유연성을 확보할 수 있음
        // 가독성을 높일 수 있음
        // 변경 가능성을 최소화할 수 있음
        return Bbs.builder()
                .content(content)
                .password(password)
                .title(title)
                .author(author).build();
    }
}