package com.sparta.bbs.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.bbs.dto.BbsRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Bbs extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String title;

    @Builder
    public Bbs(String author, String password, String title, String content) {
        this.author = author;
        this.password = password;
        this.title = title;
        this.content = content;
    }

    public void update(BbsRequestDto bbsRequestDto) {
        this.author = bbsRequestDto.getAuthor();
        this.password = bbsRequestDto.getPassword();
        this.title = bbsRequestDto.getTitle();
        this.content = bbsRequestDto.getContent();
    }


}