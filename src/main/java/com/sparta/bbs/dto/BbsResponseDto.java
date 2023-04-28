package com.sparta.bbs.dto;

import com.sparta.bbs.entity.Bbs;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BbsResponseDto {

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long id;
    private String title;
    private String content;
    private String username;

    public BbsResponseDto(Bbs bbs) {
        this.id = bbs.getId();
        this.title = bbs.getTitle();
        this.content = bbs.getContent();
        this.username = bbs.getUsername();
        this.createdAt = bbs.getCreatedAt();
        this.modifiedAt = bbs.getModifiedAt();
    }
}
