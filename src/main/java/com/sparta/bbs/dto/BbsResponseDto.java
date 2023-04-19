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
    private String author;
    private String title;
    private String content;

    public BbsResponseDto(Bbs bbs) {
        this.createdAt = bbs.getCreatedAt();
        this.modifiedAt = bbs.getModifiedAt();
        this.id = bbs.getId();
        this.author = bbs.getAuthor();
        this.title = bbs.getTitle();
        this.content = bbs.getContent();
    }
}
