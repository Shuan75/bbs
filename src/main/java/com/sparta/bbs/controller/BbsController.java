package com.sparta.bbs.controller;


import com.sparta.bbs.dto.BbsRequestDto;
import com.sparta.bbs.dto.BbsResponseDto;
import com.sparta.bbs.entity.Bbs;
import com.sparta.bbs.service.BbsService;
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
    public BbsResponseDto createBbs(@RequestBody BbsRequestDto requestDto) {
        return bbsService.create(requestDto);
    }

    @PutMapping("/api/post/{id}") // 게시글 수정
    public BbsResponseDto updatePosts(@PathVariable Long id, @RequestBody BbsRequestDto requestDto) {
        return bbsService.update(id, requestDto);
    }

    @DeleteMapping("/api/post/{id}") // 삭제
    public String deletePost(@PathVariable Long id, @RequestParam(value = "password" , required = false) String password) {
        return bbsService.delete(id, password);
    }

}