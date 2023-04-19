package com.sparta.bbs.service;


import com.sparta.bbs.dto.BbsResponseDto;
import com.sparta.bbs.repository.BbsRepository;
import com.sparta.bbs.dto.BbsRequestDto;
import com.sparta.bbs.entity.Bbs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BbsService {
    // PostRepository 연결
    private final BbsRepository bbsRepository;

    // 목록 조회
    @Transactional(readOnly = true)
    public List<BbsResponseDto> getAllPosts() {
        List<Bbs> posts = bbsRepository.findAllByOrderByModifiedAtDesc();
        List<BbsResponseDto> responseDto = new ArrayList<>();
        for (Bbs bbs : posts) {
            responseDto.add(new BbsResponseDto(bbs));
        }
        return responseDto;
    }

    // 상세 조회
    @Transactional(readOnly = true)
    public BbsResponseDto getPost(Long id) {
        Bbs bbs = bbsRepository.findById(id).orElseThrow();
        BbsResponseDto responseDto = new BbsResponseDto(bbs);
        responseDto.getClass();
        return responseDto;
    }

    // 추가
    @Transactional
    public BbsResponseDto create(BbsRequestDto bbsRequestDto) {
        Bbs bbs = bbsRepository.save(bbsRequestDto.toEntity());
        return new BbsResponseDto(bbs);
    }

    // 수정
    @Transactional
    public BbsResponseDto update(Long id, BbsRequestDto postRequestDto) {
        Bbs bbs = bbsRepository.findById(id).orElseThrow();
        BbsResponseDto responseDto = new BbsResponseDto(bbs);
        if (!bbs.getPassword().equals(postRequestDto.getPassword())) {
            return responseDto;
        }
        bbs.update(postRequestDto);
        return new BbsResponseDto(bbs);
    }

    // 삭제
    @Transactional
    public String delete(Long id, String password) {
        Bbs bbs = bbsRepository.findById(id).orElseThrow();
        if (!bbs.getPassword().equals(password)) {
            return "failed : 비번틀림";
        }
        bbsRepository.deleteById(id);
        return "success: 성공적 삭제";
    }

}