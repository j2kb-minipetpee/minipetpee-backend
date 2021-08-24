package com.j2kb.minipetpee.api.main.controller;

import com.j2kb.minipetpee.api.main.controller.dto.*;
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
    public ResponseEntity<List<PopularPostResponse>> findPopularPosts(
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        // popular post example
        PostResponse post1 = new PostResponse(101L, "루피는 연예인", LocalDateTime.now(), "http://image.dongascience.com/Photo/2017/03/14900752352661.jpg", "화려한 조명이 루피를 감싸네");
        PostResponse post2 = new PostResponse(102L, "루피는 배고파", LocalDateTime.now(), "http://image.dongascience.com/Photo/2017/03/14900752352661.jpg", "군침이 싸악 도노");
        PostResponse post3 = new PostResponse(103L, "루피 보호 위원회", LocalDateTime.now(), "http://image.dongascience.com/Photo/2017/03/14900752352661.jpg", "끊임없이 고통 받는 루피를 쉬게 도와주세요!");
        PostResponse post4 = new PostResponse(104L, "프론트엔드 짱", LocalDateTime.now(), "http://image.dongascience.com/Photo/2017/03/14900752352661.jpg", "열정!열정!열정!");

        // popular post author example
        PopularPostMemberResponse member1 = new PopularPostMemberResponse(11L, "루피루", "http://image.dongascience.com/Photo/2017/03/14900752352661.jpg");
        PopularPostMemberResponse member2 = new PopularPostMemberResponse(12L, "헝그리 루피", "http://image.dongascience.com/Photo/2017/03/14900752352661.jpg");
        PopularPostMemberResponse member3 = new PopularPostMemberResponse(13L, "루못미", "http://image.dongascience.com/Photo/2017/03/14900752352661.jpg");
        PopularPostMemberResponse member4 = new PopularPostMemberResponse(14L, "moon", "http://image.dongascience.com/Photo/2017/03/14900752352661.jpg");

        // popular post response example
        PopularPostResponse popularPost1 = new PopularPostResponse(111L, post1, member1);
        PopularPostResponse popularPost2 = new PopularPostResponse(222L, post2, member2);
        PopularPostResponse popularPost3 = new PopularPostResponse(333L, post3, member3);
        PopularPostResponse popularPost4 = new PopularPostResponse(444L, post4, member4);

        // initializing popular post list example
        List<PopularPostResponse> popularPosts = new ArrayList<>();
        popularPosts.add(popularPost1);
        popularPosts.add(popularPost2);
        popularPosts.add(popularPost3);
        popularPosts.add(popularPost4);

        return ResponseEntity.ok(popularPosts);
    }


    // 계정 검색
    @GetMapping("/search-member")
    public ResponseEntity<List<SearchMemberResponse>> searchMembers(
            @RequestParam("name") String memberName,
            @PageableDefault(size = 8, sort = "name", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        SearchMemberResponse member1 = new SearchMemberResponse(1011L, memberName, "http://image.dongascience.com/Photo/2017/03/14900752352661.jpg", 1011);
        SearchMemberResponse member2 = new SearchMemberResponse(1022L, memberName, "http://image.dongascience.com/Photo/2017/03/14900752352661.jpg", 1032);
        SearchMemberResponse member3 = new SearchMemberResponse(1033L, memberName, "http://image.dongascience.com/Photo/2017/03/14900752352661.jpg", 1040);

        List<SearchMemberResponse> members = new ArrayList<>();
        members.add(member1);
        members.add(member2);
        members.add(member3);

        return ResponseEntity.ok(members);
    }

    // 게시글 검색
    @GetMapping("/search-post")
    public ResponseEntity<List<SearchPostResponse>> searchPosts(
            @RequestParam("name") String postName,
            @PageableDefault(size = 8, sort = "title", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        PostResponse post1 = new PostResponse(11L, postName, LocalDateTime.now(), "http://image.dongascience.com/Photo/2017/03/14900752352661.jpg", "초밥 먹고 싶다");
        PostResponse post2 = new PostResponse(22L, postName, LocalDateTime.now(), "http://image.dongascience.com/Photo/2017/03/14900752352661.jpg", "배고파퍗파퍗파퍄퍗");
        PostResponse post3 = new PostResponse(33L, postName, LocalDateTime.now(), "http://image.dongascience.com/Photo/2017/03/14900752352661.jpg", "점심은 미역국");

        List<SearchPostResponse> posts = new ArrayList<>();
        posts.add(new SearchPostResponse("프론트대장", post1));
        posts.add(new SearchPostResponse("백엔드대장", post2));
        posts.add(new SearchPostResponse("프론트응애", post3));

        return ResponseEntity.ok(posts);
    }
}
