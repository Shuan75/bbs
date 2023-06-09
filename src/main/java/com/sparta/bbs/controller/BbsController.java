package com.sparta.bbs.controller;


import com.sparta.bbs.dto.BbsRequestDto;
import com.sparta.bbs.dto.BbsResponseDto;
import com.sparta.bbs.dto.DeleteBbsDto;
import com.sparta.bbs.dto.ResponseDto;
import com.sparta.bbs.entity.Bbs;
import com.sparta.bbs.service.BbsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BbsController {

    private final BbsService bbsService;

    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index");
    }

    @GetMapping("/api/posts") // 전체 게시글 조회
    public List<BbsResponseDto> getPosts() {
        return bbsService.getAllPosts();
    }

    @GetMapping("/api/post/{id}") // id값과 일치하는 게시글 조회
    public BbsResponseDto getIdPost(@PathVariable Long id) {
        return bbsService.getPost(id);
    }

    @PostMapping("/api/post") // 게시글 작성
    public BbsResponseDto createBbs(@RequestBody BbsRequestDto requestDto, HttpServletRequest httpServletRequest) {
        return bbsService.create(requestDto, httpServletRequest);
    }

    @PutMapping("/api/post/{id}") // 게시글 수정
    public BbsResponseDto updatePosts(@PathVariable Long id, @RequestBody BbsRequestDto requestDto, HttpServletRequest httpServletRequest) {
        return bbsService.update(id, requestDto, httpServletRequest);
    }

    @DeleteMapping("/api/post/{id}") // 삭제
    public ResponseDto deletePost(@PathVariable Long id, HttpServletRequest httpServletRequest) {
        return bbsService.delete(id, httpServletRequest);
    }

}