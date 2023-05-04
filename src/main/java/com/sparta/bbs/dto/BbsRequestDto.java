package com.sparta.bbs.dto;

import com.sparta.bbs.entity.Bbs;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BbsRequestDto {

    private String title;
    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


//    public Bbs toEntity() {
//        // builder
//        // 필요한 데이터만 설정할 수 있음
//        // 유연성을 확보할 수 있음
//        // 가독성을 높일 수 있음
//        // 변경 가능성을 최소화할 수 있음
//        return Bbs.builder()
//                .content(content)
//                .password(password)
//                .title(title)
//                .build();
//    }


}