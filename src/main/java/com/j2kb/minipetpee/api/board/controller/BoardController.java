package com.j2kb.minipetpee.api.board.controller;

import com.j2kb.minipetpee.api.board.controller.dto.request.SaveBoardPostRequest;
import com.j2kb.minipetpee.api.board.controller.dto.request.UpdateBoardPostRequest;
import com.j2kb.minipetpee.api.board.controller.dto.response.*;
import com.j2kb.minipetpee.api.board.service.BoardService;
import com.j2kb.minipetpee.api.comment.domain.Comment;
import com.j2kb.minipetpee.global.domain.Post;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "게시판 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/apis/{homepee-id}/board/posts")
public class BoardController {

    private final BoardService boardService;

    @Operation(summary = "게시판 게시글 등록")
    @PostMapping
    public ResponseEntity<SaveBoardPostResponse> saveBoardPost(
            @PathVariable(name = "homepee-id") Long homepeeId,
            @Valid @RequestBody SaveBoardPostRequest request
    ) {
        Long boardPostId = boardService.saveBoardPost(homepeeId, request);
        return ResponseEntity.ok(new SaveBoardPostResponse(boardPostId));
    }

    @Parameter(in = ParameterIn.QUERY
            , description = "페이지 (0 부터 시작)"
            , name = "page"
            , content = @Content(schema = @Schema(type = "integer", defaultValue = "0")))
    @Parameter(in = ParameterIn.QUERY
            , description = "반환할 데이터 수"
            , name = "size"
            , content = @Content(schema = @Schema(type = "integer", defaultValue = "10")))
    @Operation(summary = "게시판 게시글 목록 조회")
    @GetMapping
    public ResponseEntity<BoardPaginationResponse> findBoardPosts(
            @PathVariable(name = "homepee-id") Long homepeeId,
            @ParameterObject  @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {

        Page<Post> boardPosts = boardService.findBoardPosts(homepeeId, pageable);
        return ResponseEntity.ok(new BoardPaginationResponse(boardPosts));
    }

    @Operation(summary = "게시판 게시글 조회")
    @GetMapping("/{post-id}")
    public ResponseEntity<BoardPostResponse> findBoardPost(
            @PathVariable(name = "homepee-id") Long homepeeId,
            @PathVariable(name = "post-id") Long postId
    ) {
        Post boardPost = boardService.findBoardPost(homepeeId, postId);

        PageRequest pageRequest = PageRequest.of(0, 3, Sort.Direction.DESC, "id");
        Page<Comment> comments = boardService.findBoardPostComments(boardPost.getId(), pageRequest);
        return ResponseEntity.ok(new BoardPostResponse(boardPost, comments));
    }

    @Operation(summary = "게시판 게시글 수정")
    @PutMapping("/{post-id}")
    public ResponseEntity<Void> updateBoardPost(
            @PathVariable(name = "homepee-id") Long homepeeId,
            @PathVariable(name = "post-id") Long postId,
            @Valid @RequestBody UpdateBoardPostRequest request
    ) {
        boardService.updateBoardPost(homepeeId, postId, request);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "게시판 게시글 삭제")
    @DeleteMapping("/{post-id}")
    public ResponseEntity<Void> deleteBoardPost(
            @PathVariable(name = "homepee-id") Long homepeeId,
            @PathVariable(name = "post-id") Long postId
    ) {
        boardService.deleteBoardPost(homepeeId, postId);
        return ResponseEntity.noContent().build();
    }
}
