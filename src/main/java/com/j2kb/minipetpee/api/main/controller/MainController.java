package com.j2kb.minipetpee.api.main.controller;

import com.j2kb.minipetpee.api.main.controller.dto.response.*;
import com.j2kb.minipetpee.api.main.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/apis")
public class MainController {

    private final MainService mainService;

    // 인기 컨텐츠 요청
    @GetMapping("/popular-posts")
    public ResponseEntity<List<PopularPostResponse>> findPopularPosts(
            @PageableDefault(size = 5) Pageable pageable
    ) {
        List<PopularPostResponse> postsResponse = mainService.findPopularPosts(pageable)
                .stream()
                .map(PopularPostResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(postsResponse);
    }


    // 계정 검색
    @GetMapping("/search-member")
    public ResponseEntity<List<SearchMemberResponse>> searchMembers(
            @RequestParam("name") String name,
            @PageableDefault(size = 8) Pageable pageable
    ) {
        List<SearchMemberResponse> membersResponse = mainService.searchMembers(name, pageable)
                .stream()
                .map(SearchMemberResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(membersResponse);
    }

    // 게시글 검색
    @GetMapping("/search-post")
    public ResponseEntity<List<SearchPostResponse>> searchPosts(
            // api 명세에 title로 나와 있어서 그대로 작성하였는데, 본문 검색도 허용하게 될 경우 Keyword 등의 파라미터명이 더 적합할 거 같네요 :)
            @RequestParam("title") String title,
            @PageableDefault(size = 8, sort = "title", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        List<SearchPostResponse> postsResponse = mainService.searchPosts(title, pageable)
                .stream()
                .map(SearchPostResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(postsResponse);
    }
}
