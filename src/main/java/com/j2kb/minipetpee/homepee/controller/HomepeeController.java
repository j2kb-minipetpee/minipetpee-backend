package com.j2kb.minipetpee.homepee.controller;

import com.j2kb.minipetpee.homepee.controller.dto.*;
import com.j2kb.minipetpee.member.domain.Gender;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/apis/{homepee-id}")
@RestController
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
        List<RecentPostResponse> recentPostList = new ArrayList<>();
        recentPostList.add(post1);
        recentPostList.add(post2);
        recentPostList.add(post3);
        RecentPostsResponse recentPosts = new RecentPostsResponse(recentPostList);

        // 공생평 조회
        FanCommentResponse fanComment1 = new FanCommentResponse(1L, 1011L, "콩이", "반갑개 맞팔 부탁하개~", LocalDateTime.now());
        FanCommentResponse fanComment2 = new FanCommentResponse(2L, 1013L, "춘식이", "울 집사 놀리는 것도 꿀잼이애옹", LocalDateTime.now());
        List<FanCommentResponse> fanCommentsList = new ArrayList<>();
        fanCommentsList.add(fanComment1);
        fanCommentsList.add(fanComment2);
        FanCommentsResponse fanComments = new FanCommentsResponse(fanCommentsList);

        // 홈피 리스폰스 dto 객체 생성
        FindHomepeeResponse homepeeResponse = new FindHomepeeResponse(profile, "gateImg1", recentPosts, "보리의 홈피애옹", 10, fanComments);
        return ResponseEntity.ok(homepeeResponse);
    }

    // 공생평 작성
    @PostMapping("/fan-comments")
    public ResponseEntity<SaveFanCommentResponse> saveFanComment(@PathVariable("homepee-id") Long homepeeId, @RequestBody SaveFanCommentRequest fanCommentRequest) {
        Long id = 10L;
        Long memberId = fanCommentRequest.getMemberId();
        String content = fanCommentRequest.getContent();
        LocalDateTime createdAt = LocalDateTime.now();
        SaveFanCommentResponse fanCommentResponse = new SaveFanCommentResponse(id, memberId, content, createdAt);
        return ResponseEntity.ok(fanCommentResponse);
    }

    // 공생평 수정
    @PutMapping("/fan-comments")
    public ResponseEntity<Void> updateFanComment(@PathVariable("homepee-id") Long homepeeId, @RequestBody UpdateFanCommentRequest fanCommentRequest){
        // 홈피 서비스: 공생평 수정 메서드 호출
        return ResponseEntity.noContent().build();
    }

    // 공생평 삭제
    @DeleteMapping("/fan-comments/{fan-comment-id}")
    public ResponseEntity<Void> deleteFanComment(@PathVariable("homepee-id") Long homepeeId, @PathVariable("fan-comment-id") Long fanCommentId) {
        // 홈피 서비스: 공생평 삭제 메서드 호출
        return ResponseEntity.noContent().build();
    }
}
