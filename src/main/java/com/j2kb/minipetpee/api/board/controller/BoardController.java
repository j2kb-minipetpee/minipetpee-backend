package com.j2kb.minipetpee.api.board.controller;

import com.j2kb.minipetpee.api.board.controller.dto.request.SaveBoardPostCommentRequest;
import com.j2kb.minipetpee.api.board.controller.dto.request.SaveBoardPostRequest;
import com.j2kb.minipetpee.api.board.controller.dto.request.UpdateBoardPostRequest;
import com.j2kb.minipetpee.api.board.controller.dto.response.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/apis/{homepee-id}/board/posts")
public class BoardController {

    //게시글 등록
    @PostMapping
    public ResponseEntity<SaveBoardPostResponse> saveBoardPost(
            @PathVariable(name = "homepee-id") Long homepeeId,
            @RequestBody SaveBoardPostRequest boardPostRequest
    ) {
        log.info("ImageUrl = {}", boardPostRequest.getImage());

        SaveBoardPostResponse boardPostResponse = new SaveBoardPostResponse(1L);
        return ResponseEntity.ok(boardPostResponse);
    }

    //게시글 목록 조회
    @GetMapping
    public ResponseEntity<List<BoardPostSummaryResponse>> findBoardPosts(
            @PathVariable(name = "homepee-id") Long hompeeId,
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        BoardPostImageResponse boardImg1 = new BoardPostImageResponse(1L, "http://image.dongascience.com/Photo/2017/03/14900752352661.jpg");
        BoardPostImageResponse boardImg2 = new BoardPostImageResponse(2L, "http://image.dongascience.com/Photo/2017/03/14900752352661.jpg");
        BoardPostImageResponse boardImg3 = new BoardPostImageResponse(3L, "http://image.dongascience.com/Photo/2017/03/14900752352661.jpg");

        BoardPostSummaryResponse boardPost1 = new BoardPostSummaryResponse(1L, "title1", boardImg1, LocalDateTime.now());
        BoardPostSummaryResponse boardPost2 = new BoardPostSummaryResponse(2L, "title2", boardImg2, LocalDateTime.now());
        BoardPostSummaryResponse boardPost3 = new BoardPostSummaryResponse(3L, "title3", boardImg3, LocalDateTime.now());

        List<BoardPostSummaryResponse> boardPosts = new ArrayList<>();
        boardPosts.add(boardPost1);
        boardPosts.add(boardPost2);
        boardPosts.add(boardPost3);

        return ResponseEntity.ok(boardPosts);
    }

    //게시글 조회
    @GetMapping("/{post-id}")
    public ResponseEntity<BoardPostResponse> findBoardPost(
            @PathVariable(name = "homepee-id") Long homepeeId,
            @PathVariable(name = "post-id") Long postId
    ) {
        String title = "title";
        String content = "content";
        int viewCount = 100;
        BoardPostImageResponse postImageResponse = new BoardPostImageResponse(1L, "http://image.dongascience.com/Photo/2017/03/14900752352661.jpg");
        LocalDateTime createdAt = LocalDateTime.now();

        BoardPostResponse boardPostResponse = new BoardPostResponse(postId,title,content,viewCount,postImageResponse, createdAt);

        return ResponseEntity.ok(boardPostResponse);
    }

    //게시글 수정
    @PutMapping("/{post-id}")
    public ResponseEntity<Void> updateBoardPost(
            @PathVariable(name = "homepee-id") Long homepeeId,
            @PathVariable(name = "post-id") Long postId,
            @RequestBody UpdateBoardPostRequest updateBoardPostRequest
    ) {
        return ResponseEntity.noContent().build();
    }

    //게시글 댓글 추가
    @PostMapping("/{post-id}/comment")
    public ResponseEntity<SaveBoardPostCommentResponse> saveBoardPostComment(
            @PathVariable(name = "homepee-id") Long homepeeId,
            @PathVariable(name = "post-id") Long postId,
            @RequestBody SaveBoardPostCommentRequest boardCommentRequest
    ) {
        String content = boardCommentRequest.getContent();
        BoardPostCommentMemberResponse boardPostMember = new BoardPostCommentMemberResponse(boardCommentRequest.getMemberId(), "memberName");
        LocalDateTime createdAt = LocalDateTime.now();

        SaveBoardPostCommentResponse boardPostComment = new SaveBoardPostCommentResponse(1L,content, boardPostMember,createdAt);

        return ResponseEntity.ok(boardPostComment);
    }

    //게시글 삭제
    @DeleteMapping("/{post-id}")
    public ResponseEntity<Void> deleteBoardPost(
            @PathVariable(name = "homepee-id") Long homepeeId,
            @PathVariable(name = "post-id") Long postId
    ) {
        return ResponseEntity.noContent().build();
    }

    //게시글 댓글 삭제
    @DeleteMapping("/{post-id}/comments/{comment-id}")
    public ResponseEntity<Void> deleteBoardPostComment(
            @PathVariable(name = "homepee-id") Long homepeeId,
            @PathVariable(name = "post-id") Long postId,
            @PathVariable(name = "comment-id") Long commentId
    ) {
        return ResponseEntity.noContent().build();
    }

}
