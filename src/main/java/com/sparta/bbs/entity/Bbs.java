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
    private String title;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private Long userId;

    @Builder
    public Bbs(String password, String title, String content) {
        this.password = password;
        this.title = title;
        this.content = content;

    }

    public Bbs(BbsRequestDto bbsRequestDto, Long userId) {
        this.title = bbsRequestDto.getTitle();
        this.content = bbsRequestDto.getContent();
//        this.password = bbsRequestDto.getPassword();
        this.userId = userId;
    }

    public void update(BbsRequestDto bbsRequestDto) {
//        this.password = bbsRequestDto.getPassword();
        this.title = bbsRequestDto.getTitle();
        this.content = bbsRequestDto.getContent();
    }


}