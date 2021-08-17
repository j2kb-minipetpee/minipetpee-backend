package com.j2kb.minipetpee.homepee.controller;

import com.j2kb.minipetpee.homepee.controller.dto.*;
import com.j2kb.minipetpee.member.domain.Gender;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/apis/{homepee-id}")
public class HomepeeController {

    // 홈 조회
    @GetMapping
    public ResponseEntity<FindHomepeeResponse> findHomepee(@PathVariable("homepee-id") Long homepeeId) {
        // 프로필 조회
        ProfileResponse profile = new ProfileResponse("보리", LocalDateTime.now(), "검은턱시도", "소심한 개냥이", Gender.MALE, "profileImg1");

        // 최근 게시글 조회
        RecentPostResponse post1 = new RecentPostResponse(1L, "안녕하새옹 보리애옹", LocalDateTime.now());
        RecentPostResponse post2 = new RecentPostResponse(2L, "오늘도 집사 개롭피기 성공", LocalDateTime.now());
        RecentPostResponse post3 = new RecentPostResponse(3L, "여름이었다", LocalDateTime.now());
        List<RecentPostResponse> recentPosts = new ArrayList<>();
        recentPosts.add(post1);
        recentPosts.add(post2);
        recentPosts.add(post3);

        // 공생평 조회
        FanCommentResponse fanComment1 = new FanCommentResponse(1L, 1011L, "콩이", "반갑개 맞팔 부탁하개~", LocalDateTime.now());
        FanCommentResponse fanComment2 = new FanCommentResponse(2L, 1013L, "춘식이", "울 집사 놀리는 것도 꿀잼이애옹", LocalDateTime.now());
        List<FanCommentResponse> fanComments = new ArrayList<>();
        fanComments.add(fanComment1);
        fanComments.add(fanComment2);

        // 홈피 리스폰스 dto 객체 생성
        FindHomepeeResponse homepeeResponse = new FindHomepeeResponse(profile, "gateImg1", recentPosts, "보리의 홈피애옹", 10, fanComments);
        return ResponseEntity.ok(homepeeResponse);
    }

    // 공생평 작성
    @PostMapping("/fan-comments")
    public ResponseEntity<FanCommentResponse> saveFanComment(
            @PathVariable("homepee-id") Long homepeeId,
            @RequestBody SaveFanCommentRequest fanCommentRequest
    ) {
        // 추가된 공생평 데이터의 id
        Long id = 10L;
        Long memberId = fanCommentRequest.getMemberId();
        // 멤버 아이디에 해당하는 memberName 조회
        String memberName = "춘식이";
        String content = fanCommentRequest.getContent();
        LocalDateTime createdAt = LocalDateTime.now();
        FanCommentResponse fanCommentResponse = new FanCommentResponse(id, memberId, memberName, content, createdAt);
        return ResponseEntity.ok(fanCommentResponse);
    }

    // 공생평 수정
    @PutMapping("/fan-comments")
    public ResponseEntity<Void> updateFanComment(
            @PathVariable("homepee-id") Long homepeeId,
            @RequestBody UpdateFanCommentRequest fanCommentRequest
    ){
        // 홈피 서비스: 공생평 수정 메서드 호출
        return ResponseEntity.noContent().build();
    }

    // 공생평 삭제
    @DeleteMapping("/fan-comments/{fan-comment-id}")
    public ResponseEntity<Void> deleteFanComment(
            @PathVariable("homepee-id") Long homepeeId,
            @PathVariable("fan-comment-id") Long fanCommentId
    ) {
        // 홈피 서비스: 공생평 삭제 메서드 호출
        return ResponseEntity.noContent().build();
    }
}
