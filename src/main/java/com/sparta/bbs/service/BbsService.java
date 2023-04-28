package com.sparta.bbs.service;


import com.sparta.bbs.dto.BbsResponseDto;
import com.sparta.bbs.dto.DeleteBbsDto;
import com.sparta.bbs.entity.Member;
import com.sparta.bbs.jwt.JwtUtil;
import com.sparta.bbs.repository.BbsRepository;
import com.sparta.bbs.dto.BbsRequestDto;
import com.sparta.bbs.entity.Bbs;
import com.sparta.bbs.repository.MemberRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BbsService {
    // PostRepository 연결
    private final BbsRepository bbsRepository;
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

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
    public BbsResponseDto create(BbsRequestDto bbsRequestDto, HttpServletRequest httpServletRequest) {
        String token = jwtUtil.resolveToken(httpServletRequest);
        //JWT 안에 있는 정보를 담는 Claims 객체
        Claims claims;

        //토큰이 있는 경우에만 추가 가능
        if (token != null) {
            //validateToken()를 사용해서, 들어온 토큰이 위조/변조, 만료가 되지 않았는지 검증
            if (jwtUtil.validateToken(token)) {
                // true => 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
                // false
            } else {
                //매개변수가 의도치 않는 상황 유발시, 해당 메시지 반환
                throw new IllegalArgumentException("Token Error");
            }

            //토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            //claims.getSubject(): 넣어두었던 username 가져오기
            //findByUsername()를 사용해서, UserRepository 에서 user 정보를 가져오기
            Member member = memberRepository.findByUsername(claims.getSubject()).orElseThrow(
                    //매개변수가 의도치 않는 상황 유발시
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            //요청받은 DTO 로 DB에 저장할 객체 Board board 만들기
            Bbs bbs = bbsRepository.saveAndFlush(new Bbs(bbsRequestDto, member.getId()));
            System.out.println(bbs);
            System.out.println(bbs.getId());
            System.out.println(bbsRequestDto);
            //entity(board) 를 DTO(BoardResponseDto) 로 변환시켜서 리턴(반환)
            return new BbsResponseDto(bbs);
            //토큰이 null 이라면(Client 에게서 Token 이 넘어오지 않은 경우),
        } else {

            return null;
        }
    }

//    Bbs bbs = bbsRepository.save(bbsRequestDto.toEntity());
//        return new BbsResponseDto(bbs);
//    }

    // 수정
    @Transactional
    public BbsResponseDto update(Long id, BbsRequestDto bbsRequestDto, HttpServletRequest httpServletRequest) {
        String token = jwtUtil.resolveToken(httpServletRequest);

        Claims claims;


        if (token != null) {
            // validateToken => 토큰 검증
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            //claims.getSubject(): 우리가 넣어두었던 username 가져오기
            //findByUsername()를 사용해서, UserRepository 에서 user 정보를 가져오기
            Member member = memberRepository.findByUsername(claims.getSubject()).orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));

            //즉, 현재 로그인한 user 가 선택한 board 가 맞는지 확인
            Bbs bbs = bbsRepository.findByIdAndUserId(id, member.getId()).orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지않습니다."));
            bbs.update(bbsRequestDto);
            return new BbsResponseDto(bbs);
        } else {
            return null;
        }
    }

    // 삭제
    @Transactional
    public DeleteBbsDto delete(Long id, BbsRequestDto bbsRequestDto, HttpServletRequest httpServletRequest) {

        String token = jwtUtil.resolveToken(httpServletRequest);
        //JWT 안에 있는 정보를 담는 Claims 객체
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            Member member = memberRepository.findByUsername(claims.getSubject()).orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지않습니다."));

            Bbs bbs = bbsRepository.findByIdAndUserId(id, member.getId()).orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지않습니다."));
            bbsRepository.delete(bbs);

            return new DeleteBbsDto("게시글 삭제성공", 200);

        } else {
            return new DeleteBbsDto("게시글 작성자만 삭제 가능", HttpStatus.OK.value());
        }

//        DeleteBbsDto deleteBbsDto = new DeleteBbsDto();
//        if (bbsRequestDto.getPassword().equals(bbs.getPassword())) {
//            bbsRepository.deleteById(id);
//            deleteBbsDto.setMsg("success");
//        } else {
//            deleteBbsDto.setMsg("failed");
//        }
//        return deleteBbsDto;
//    }
    }
}