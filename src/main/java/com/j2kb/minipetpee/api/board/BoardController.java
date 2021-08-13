package com.j2kb.minipetpee.api.board;

import com.j2kb.minipetpee.api.board.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<SaveBoardPostResponse> saveBoardPost(@PathVariable(name = "homepee-id") int homepeeId,@RequestBody SaveBoardPostRequest boardDto) {
        log.info("ImageUrl = {}", boardDto.getImage().getUrl());

        SaveBoardPostResponse requestDto = new SaveBoardPostResponse(0);
        return ResponseEntity.ok().body(requestDto);
    }

    //게시글 목록 조회
    @GetMapping
    public ResponseEntity<SendBoardPostList> sendPosts(@PathVariable(name = "homepee-id") int hompeeId, @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {

        SendBoardPost boardPost1 = new SendBoardPost(1, "title1", LocalDateTime.now());
        SendBoardPost boardPost2 = new SendBoardPost(2, "title2", LocalDateTime.now());
        SendBoardPost boardPost3 = new SendBoardPost(3, "title3", LocalDateTime.now());

        List<SendBoardPost> boardPosts = new ArrayList<>();
        boardPosts.add(boardPost1);
        boardPosts.add(boardPost2);
        boardPosts.add(boardPost3);

        SendBoardPostList boardPostList = new SendBoardPostList(boardPosts);

        return ResponseEntity.ok(boardPostList);
    }

    //게시글 조회
    @GetMapping("/{post-id}")
    public ResponseEntity<FindBoardPostResponse> findBoardPost(@PathVariable(name = "homepee-id") int homepeeId, @PathVariable(name = "post-id") int postId) {
        log.info("homepeeId = {}", homepeeId);
        int id = 1;
        String title = "title";
        String content = "content";
        int viewCount = 100;
        String url = "imageURL!";

        BoardPostImage imageDto = new BoardPostImage(url);

        LocalDateTime createdAt = LocalDateTime.now();
        FindBoardPostResponse viewBoardDto = new FindBoardPostResponse(id,title,content,viewCount,imageDto, createdAt);
        return ResponseEntity.ok(viewBoardDto);
    }

    //게시글 수정
    @PutMapping("/{post-id}")
    public ResponseEntity<Void> updateBoardPost(@PathVariable(name = "homepee-id") int homepeeId, @PathVariable(name = "post-id") int postId) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    //게시글 댓글 추가
    @PostMapping("/{post-id}/comment")
    public ResponseEntity<SaveBoardPostCommentResponse> saveBoardPostComment(@PathVariable(name = "homepee-id") int homepeeId, @PathVariable(name = "post-id") int postId, @RequestBody SaveBoardPostCommentRequest addCommentDto) {
        int id = 0;
        int memberId = addCommentDto.getMemberId();
        String content = addCommentDto.getContent();
        LocalDateTime createdAt = LocalDateTime.now();
        SaveBoardPostCommentResponse savedComment = new SaveBoardPostCommentResponse(id, memberId, content, createdAt);
        return ResponseEntity.ok(savedComment);
    }

    //게시글 댓글 삭제
    @DeleteMapping("/{post-id}/comments/{comment-id}")
    public ResponseEntity<Void> deleteBoardPostComment(@PathVariable(name = "homepee-id") int homepeeId, @PathVariable(name = "post-id") int postId, @PathVariable(name = "comment-id") int commentId) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    //게시글 삭제
    @DeleteMapping("/{post-id}")
    public ResponseEntity<Void> deleteBoardPost(@PathVariable(name = "homepee-id") int homepeeId, @PathVariable(name = "post-id") int postId) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
