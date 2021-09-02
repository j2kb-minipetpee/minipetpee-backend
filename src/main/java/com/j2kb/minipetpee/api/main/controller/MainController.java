package com.j2kb.minipetpee.api.main.controller;

import com.j2kb.minipetpee.api.board.domain.BoardPost;
import com.j2kb.minipetpee.api.main.controller.dto.response.*;
import com.j2kb.minipetpee.api.main.service.MainService;
import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.global.domain.Post;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "메인 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/apis")
public class MainController {

    private final MainService mainService;

    @Parameter(in = ParameterIn.QUERY
            , description = "요청할 페이지(0 이상)"
            , name = "page"
            , content = @Content(schema = @Schema(type = "integer", defaultValue = "0")))
    @Parameter(in = ParameterIn.QUERY
            , description = "요청할 데이터 수"
            , name = "size"
            , content = @Content(schema = @Schema(type = "integer", defaultValue = "5")))
    @Operation(summary = "인기 게시글 조회(board만 조회, album 조회x)")
    @GetMapping("/popular-posts")
    public ResponseEntity<PopularPostPaginationResponse> findPopularPosts(
            @ParameterObject @PageableDefault(size = 5) Pageable pageable
    ) {
        Page<Post> popularPosts = mainService.findPopularPosts(pageable);
        return ResponseEntity.ok(new PopularPostPaginationResponse(popularPosts));
    }

    @Parameter(in = ParameterIn.QUERY
            , description = "요청할 페이지(0 이상)"
            , name = "page"
            , content = @Content(schema = @Schema(type = "integer", defaultValue = "0")))
    @Parameter(in = ParameterIn.QUERY
            , description = "요청할 데이터 수"
            , name = "size"
            , content = @Content(schema = @Schema(type = "integer", defaultValue = "8")))
    @Operation(summary = "계정 검색")
    @GetMapping("/search-member")
    public ResponseEntity<SearchMemberPaginationResponse> searchMembers(
            @RequestParam("name") String name,
            @ParameterObject @PageableDefault(size = 8) Pageable pageable
    ) {
        Page<Member> searchMembers = mainService.searchMembers(name, pageable);
        return ResponseEntity.ok(new SearchMemberPaginationResponse(searchMembers));
    }

    @Parameter(in = ParameterIn.QUERY
            , description = "요청할 페이지(0 이상)"
            , name = "page"
            , content = @Content(schema = @Schema(type = "integer", defaultValue = "0")))
    @Parameter(in = ParameterIn.QUERY
            , description = "요청할 데이터 수"
            , name = "size"
            , content = @Content(schema = @Schema(type = "integer", defaultValue = "8")))
    @Operation(summary = "게시글 검색")
    @GetMapping("/search-post")
    public ResponseEntity<SearchPostPaginationResponse> searchPosts(
            @RequestParam("title") String title,
            @ParameterObject @PageableDefault(size = 8, sort = "title", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<Post> searchPosts = mainService.searchPosts(title, pageable);
        return ResponseEntity.ok(new SearchPostPaginationResponse(searchPosts));
    }
}
