package com.sparta.bbs.repository;


import com.sparta.bbs.entity.Bbs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BbsRepository extends JpaRepository<Bbs, Long> {
    List<Bbs> findAllByOrderByModifiedAtDesc();

    //게시글 수정, 삭제
    //Optional: null 을 반환하면 오류가 발생할 가능성이 매우 높은 경우에 '결과 없음'을 명확하게 드러내기 위해
    //--> 수정, 삭제에서 id 와 userid 를 비교해서 일치하지 않으면, 예외 처리를 하기때문에 여기선 적절한 사용법으로 보임
    //findByIdAndUserId(Long id, Long userId): Board 의 id 와 userId 가 일치하는 Board 를 가져온다
    //--> 그래야, 로그인한 해당 사용자가 작성한 게시글만 수정, 삭제되기 때문(본인 인증)
    Optional<Bbs> findByIdAndUserId(Long id, Long userId);
}