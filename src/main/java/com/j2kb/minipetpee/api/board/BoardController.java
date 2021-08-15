package com.j2kb.minipetpee.api.board;

import com.j2kb.minipetpee.api.board.dto.*;
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
    public ResponseEntity<SaveBoardPostResponse> saveBoardPost(@PathVariable(name = "homepee-id") Long homepeeId, @RequestBody SaveBoardPostRequest boardPostRequest) {
        log.info("ImageUrl = {}", boardPostRequest.getImage().getUrl());

        SaveBoardPostResponse boardPostResponse = new SaveBoardPostResponse(1L);
        return ResponseEntity.ok(boardPostResponse);
    }

    //게시글 목록 조회
    @GetMapping
    public ResponseEntity<FindBoardPostsResponse> findBoardPosts(@PathVariable(name = "homepee-id") Long hompeeId, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        FindBoardPostImageResponse boardImg1 = new FindBoardPostImageResponse(1L, "image1");
        FindBoardPostImageResponse boardImg2 = new FindBoardPostImageResponse(2L, "image2");
        FindBoardPostImageResponse boardImg3 = new FindBoardPostImageResponse(3L, "image3");

        FindBoardPost boardPost1 = new FindBoardPost(1L, "title1", boardImg1, LocalDateTime.now());
        FindBoardPost boardPost2 = new FindBoardPost(2L, "title2", boardImg2, LocalDateTime.now());
        FindBoardPost boardPost3 = new FindBoardPost(3L, "title3", boardImg3, LocalDateTime.now());

        List<FindBoardPost> boardPosts = new ArrayList<>();
        boardPosts.add(boardPost1);
        boardPosts.add(boardPost2);
        boardPosts.add(boardPost3);

        FindBoardPostsResponse boardPostsResponse = new FindBoardPostsResponse(boardPosts);

        return ResponseEntity.ok(boardPostsResponse);
    }

    //게시글 조회
    @GetMapping("/{post-id}")
    public ResponseEntity<FindBoardPostResponse> findBoardPost(@PathVariable(name = "homepee-id") Long homepeeId, @PathVariable(name = "post-id") Long postId) {
        String title = "title";
        String content = "content";
        int viewCount = 100;
        String url = "imageURL!";

        BoardPostImageResponse postImageResponse = new BoardPostImageResponse(url);

        LocalDateTime createdAt = LocalDateTime.now();
        FindBoardPostResponse boardPostResponse = new FindBoardPostResponse(postId,title,content,viewCount,postImageResponse, createdAt);
        return ResponseEntity.ok(boardPostResponse);
    }

    //게시글 수정
    @PutMapping("/{post-id}")
    public ResponseEntity<Void> updateBoardPost(@PathVariable(name = "homepee-id") Long homepeeId, @PathVariable(name = "post-id") Long postId, @RequestBody UpdateBoardRequest updateBoardRequest) {
        return ResponseEntity.noContent().build();
    }

    //게시글 댓글 추가
    @PostMapping("/{post-id}/comment")
    public ResponseEntity<SaveBoardPostCommentResponse> saveBoardPostComment(@PathVariable(name = "homepee-id") Long homepeeId, @PathVariable(name = "post-id") Long postId, @RequestBody SaveBoardPostCommentRequest boardCommentRequest) {
        String content = boardCommentRequest.getContent();
        BoardPostCommentMemberResponse boardPostMember = new BoardPostCommentMemberResponse(boardCommentRequest.getMemberId(), "memberName");
        LocalDateTime createdAt = LocalDateTime.now();
        SaveBoardPostCommentResponse boardPostComment = new SaveBoardPostCommentResponse(1L,content, boardPostMember,createdAt);
        return ResponseEntity.ok(boardPostComment);
    }

    //게시글 삭제
    @DeleteMapping("/{post-id}")
    public ResponseEntity<Void> deleteBoardPost(@PathVariable(name = "homepee-id") Long homepeeId, @PathVariable(name = "post-id") Long postId) {
        return ResponseEntity.noContent().build();
    }

    //게시글 댓글 삭제
    @DeleteMapping("/{post-id}/comments/{comment-id}")
    public ResponseEntity<Void> deleteBoardPostComment(@PathVariable(name = "homepee-id") Long homepeeId, @PathVariable(name = "post-id") Long postId, @PathVariable(name = "comment-id") Long commentId) {
        return ResponseEntity.noContent().build();
    }

}
