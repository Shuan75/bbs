package com.sparta.bbs.repository;


import com.sparta.bbs.entity.Bbs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BbsRepository extends JpaRepository<Bbs, Long> {
    List<Bbs> findAllByOrderByModifiedAtDesc();
}