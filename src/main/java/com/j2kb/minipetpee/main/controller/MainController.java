package com.j2kb.minipetpee.main.controller;

import com.j2kb.minipetpee.main.controller.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {

    // 인기 컨텐츠 요청
    @GetMapping("/apis/popular-posts")
    public ResponseEntity<FindAllPopularPostsResponse> findAllPopularPosts() {

        // popular post example
        PopularPost post1 = new PopularPost(101, "루피는 연예인", LocalDateTime.now(), "postImage1", "화려한 조명이 루피를 감싸네");
        PopularPost post2 = new PopularPost(102, "루피는 배고파", LocalDateTime.now(), "postImage2", "군침이 싸악 도노");
        PopularPost post3 = new PopularPost(103, "루피 보호 위원회", LocalDateTime.now(), "postImage3", "끊임없이 고통 받는 루피를 쉬게 도와주세요!");
        PopularPost post4 = new PopularPost(104, "프론트엔드 짱", LocalDateTime.now(), "postImage4", "열정!열정!열정!");

        // popular post author example
        PopularPostMember member1 = new PopularPostMember(11, "루피루", "rupeeImg1");
        PopularPostMember member2 = new PopularPostMember(12, "헝그리 루피", "rupeeImg2");
        PopularPostMember member3 = new PopularPostMember(13, "루못미", "rupeeImg3");
        PopularPostMember member4 = new PopularPostMember(14, "moon", "yeoljeongImg");

        // popular post response example
        FindPopularPostResponse popularPost1 = new FindPopularPostResponse(111, post1, member1);
        FindPopularPostResponse popularPost2 = new FindPopularPostResponse(222, post2, member2);
        FindPopularPostResponse popularPost3 = new FindPopularPostResponse(333, post3, member3);
        FindPopularPostResponse popularPost4 = new FindPopularPostResponse(444, post4, member4);

        // initializing popular post list example
        List<FindPopularPostResponse> popularList = new ArrayList<>();
        popularList.add(popularPost1);
        popularList.add(popularPost2);
        popularList.add(popularPost3);
        popularList.add(popularPost4);

        // popular post list response example
        FindAllPopularPostsResponse popularPostsResponse = new FindAllPopularPostsResponse(popularList);
        return ResponseEntity.ok(popularPostsResponse);
    }


    // 계정 검색
    @GetMapping("/apis/search-member")
    public ResponseEntity<FindAllMembersResponse> findMemberList(@RequestParam("name") String memberName) {
        FindMemberResponse member1 = new FindMemberResponse(1011, "루피", "rupeeImg1", 1011);
        FindMemberResponse member2 = new FindMemberResponse(1022, "루피룽룽", "rupeeImg2", 1032);
        FindMemberResponse member3 = new FindMemberResponse(1033, "피루피", "rupeeImg3", 1040);

        List<FindMemberResponse> memberList = new ArrayList<>();
        memberList.add(member1);
        memberList.add(member2);
        memberList.add(member3);

        FindAllMembersResponse membersResponse = new FindAllMembersResponse(memberList);
        return ResponseEntity.ok(membersResponse);
    }

    // 게시글 검색
    @GetMapping("/apis/search-post")
    public ResponseEntity<FindAllPostsResponse> findAllPosts(@RequestParam("name") String postName) {
        PostInfo post1 = new PostInfo(11, "배고파", LocalDateTime.now(), "hungryImg1", "초밥 먹고 싶다");
        PostInfo post2 = new PostInfo(22, "밥 먹어도 배고파", LocalDateTime.now(), "hungryImg2", "배고파퍗파퍗파퍄퍗");
        PostInfo post3 = new PostInfo(33, "배고파서 밥 먹음", LocalDateTime.now(), "hungryImg3", "점심은 미역국");

        List<FindPostResponse> postList = new ArrayList<>();
        postList.add(new FindPostResponse("프론트대장", post1));
        postList.add(new FindPostResponse("백엔드대장", post2));
        postList.add(new FindPostResponse("프론트응애", post3));

        FindAllPostsResponse postsResponse = new FindAllPostsResponse(postList);
        return ResponseEntity.ok(postsResponse);
    }
}
