package com.sparta.bbs.controller;

import com.sparta.bbs.dto.ResponseDto;
import com.sparta.bbs.dto.SignupRequestDto;
import com.sparta.bbs.service.MemberService;
import com.sparta.bbs.dto.LoginRequestDto;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/api/auth/signup")
    public ResponseEntity<ResponseDto> signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        memberService.signup(signupRequestDto);
        return ResponseEntity.ok(new ResponseDto("회원가입 완료", 200));
    }

    @ResponseBody
    @PostMapping("/api/auth/login")
    public ResponseEntity<ResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        memberService.login(loginRequestDto, response);
        return ResponseEntity.ok(new ResponseDto("로그인 완료", 200));
    }







//    @PostMapping("/signup")
//    //ResponseEntity: 결과값, 상태코드, 헤더값을 모두 프론트에 넘겨줄 수 있고, 에러코드 또한 섬세하게 설정해서 보내줄 수 있음 --> 구글링 필요, MsgResponseDto 의 데이터를 반환할 것임, signup 메소드 명
//    //@RequestBody: HTTP Method 안의 body 값을 Mapping(key:value 로 짝지어줌), SignupRequestDto: 넘어오는 데이터를 받아주는 객체
//    //@Valid: Controller 에서 유효성 검사를 할 곳에 붙임
//    public ResponseEntity<MsgResponseDto> signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
//        //signupRequestDto 에 데이터를 담아서, userService 로 응답을 보냄
//        memberService.signup(signupRequestDto);
//        //MsgResponseDto 에서 선언한 타입(여기서는 String message, int statusCode)으로 반환하는데,
//        //ResponseEntity.ok(): 상태코드를 반환
//        return ResponseEntity.ok(new MsgResponseDto("회원가입 완료", HttpStatus.OK.value()));
//    }
//
//    //로그인 구현
//    @ResponseBody
//    @PostMapping("/login")
//    //ResponseEntity: 결과값, 상태코드, 헤더값을 모두 프론트에 넘겨줄 수 있고, 에러코드 또한 섬세하게 설정해서 보내줄 수 있음 --> 구글링 필요, MsgResponseDto 의 데이터를 반환할 것임, login 메소드 명
//    //@RequestBody: HTTP Method 안의 body 값을 Mapping(key:value 로 짝지어줌), LoginRequestDto: 넘어오는 데이터를 받아주는 객체
//    //HttpServletRequest request 객체: 누가 로그인 했는지 알기위한 토큰을 담고 있음
//    public ResponseEntity<MsgResponseDto> login(@RequestBody com.sparta.springboard.dto.LoginRequestDto loginRequestDto, HttpServletResponse response) {
//        //loginRequestDto, response 에 데이터를 담아서, userService 로 응답을 보냄
//        memberService.login(loginRequestDto, response);
//        //MsgResponseDto 에서 선언한 타입(여기서는 String message, int statusCode)으로 반환하는데,
//        //ResponseEntity.ok(): 상태코드를 반환
//        return ResponseEntity.ok(new MsgResponseDto("로그인 완료",HttpStatus.OK.value()));
//    }
}
