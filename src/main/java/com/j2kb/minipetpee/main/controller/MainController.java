package com.j2kb.minipetpee.main.controller;

import com.j2kb.minipetpee.main.controller.dto.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/apis")
public class MainController {

    // 인기 컨텐츠 요청
    @GetMapping("/popular-posts")
    public ResponseEntity<FindPopularPostsResponse> findPopularPosts(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

        // popular post example
        PostResponse post1 = new PostResponse(101L, "루피는 연예인", LocalDateTime.now(), "postImage1", "화려한 조명이 루피를 감싸네");
        PostResponse post2 = new PostResponse(102L, "루피는 배고파", LocalDateTime.now(), "postImage2", "군침이 싸악 도노");
        PostResponse post3 = new PostResponse(103L, "루피 보호 위원회", LocalDateTime.now(), "postImage3", "끊임없이 고통 받는 루피를 쉬게 도와주세요!");
        PostResponse post4 = new PostResponse(104L, "프론트엔드 짱", LocalDateTime.now(), "postImage4", "열정!열정!열정!");

        // popular post author example
        PopularPostMemberResponse member1 = new PopularPostMemberResponse(11L, "루피루", "rupeeImg1");
        PopularPostMemberResponse member2 = new PopularPostMemberResponse(12L, "헝그리 루피", "rupeeImg2");
        PopularPostMemberResponse member3 = new PopularPostMemberResponse(13L, "루못미", "rupeeImg3");
        PopularPostMemberResponse member4 = new PopularPostMemberResponse(14L, "moon", "yeoljeongImg");

        // popular post response example
        FindPopularPostResponse popularPost1 = new FindPopularPostResponse(111L, post1, member1);
        FindPopularPostResponse popularPost2 = new FindPopularPostResponse(222L, post2, member2);
        FindPopularPostResponse popularPost3 = new FindPopularPostResponse(333L, post3, member3);
        FindPopularPostResponse popularPost4 = new FindPopularPostResponse(444L, post4, member4);

        // initializing popular post list example
        List<FindPopularPostResponse> popularList = new ArrayList<>();
        popularList.add(popularPost1);
        popularList.add(popularPost2);
        popularList.add(popularPost3);
        popularList.add(popularPost4);

        // popular post list response example
        FindPopularPostsResponse popularPostsResponse = new FindPopularPostsResponse(popularList);
        return ResponseEntity.ok(popularPostsResponse);
    }


    // 계정 검색
    @GetMapping("/search-member")
    public ResponseEntity<FindMembersResponse> findMembers(@RequestParam("name") String memberName, @PageableDefault(size = 8, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        FindMemberResponse member1 = new FindMemberResponse(1011L, memberName, "rupeeImg1", 1011);
        FindMemberResponse member2 = new FindMemberResponse(1022L, memberName, "rupeeImg2", 1032);
        FindMemberResponse member3 = new FindMemberResponse(1033L, memberName, "rupeeImg3", 1040);

        List<FindMemberResponse> memberList = new ArrayList<>();
        memberList.add(member1);
        memberList.add(member2);
        memberList.add(member3);

        FindMembersResponse membersResponse = new FindMembersResponse(memberList);
        return ResponseEntity.ok(membersResponse);
    }

    // 게시글 검색
    @GetMapping("/search-post")
    public ResponseEntity<FindPostsResponse> findPosts(@RequestParam("name") String postName, @PageableDefault(size = 8, sort = "title", direction = Sort.Direction.ASC) Pageable pageable) {
        PostResponse post1 = new PostResponse(11L, postName, LocalDateTime.now(), "hungryImg1", "초밥 먹고 싶다");
        PostResponse post2 = new PostResponse(22L, postName, LocalDateTime.now(), "hungryImg2", "배고파퍗파퍗파퍄퍗");
        PostResponse post3 = new PostResponse(33L, postName, LocalDateTime.now(), "hungryImg3", "점심은 미역국");

        List<FindPostResponse> postList = new ArrayList<>();
        postList.add(new FindPostResponse("프론트대장", post1));
        postList.add(new FindPostResponse("백엔드대장", post2));
        postList.add(new FindPostResponse("프론트응애", post3));

        FindPostsResponse postsResponse = new FindPostsResponse(postList);
        return ResponseEntity.ok(postsResponse);
    }
}
