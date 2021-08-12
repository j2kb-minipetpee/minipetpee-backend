package com.j2kb.minipetpee.api.board;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.j2kb.minipetpee.api.board.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/apis/{homepee-id}/board/posts")
public class BoardController {

    private final ObjectMapper objectMapper;

    //게시글 등록
    @PostMapping
    public ResponseEntity<RequestBoardDto> saveBoardPost(@PathVariable(name = "homepee-id") int hompeeId, @RequestBody NewBoardDto boardDto) {
        log.info("{}", boardDto);
        RequestBoardDto requestDto = new RequestBoardDto(0);
        return ResponseEntity.ok().body(requestDto);
    }

    //게시글 목록 조회
    @GetMapping
    public ResponseEntity<BoardListDto> sendPosts(@PathVariable(name = "homepee-id") int hompeeId, @RequestParam int size, @RequestParam int page) {

        int id = 0;
        String title = "title";
        LocalDateTime createdAt = LocalDateTime.now();
        BoardListDto boardList = new BoardListDto(id, title, createdAt);

        return ResponseEntity.ok(boardList);
    }

    //게시글 조회
    @GetMapping("/{post-id}")
    public ResponseEntity<ViewBoardDto> lookUpPost(@PathVariable(name = "homepee-id") int homepeeId, @PathVariable(name = "post-id") int postId) {
        log.info("homepeeId = {}", homepeeId);
        int id = 1;
        String title = "title";
        String content = "content";
        int viewCount = 100;
        String url = "imageURL!";

        ImageDto imageDto = new ImageDto(url);

        LocalDateTime createdAt = LocalDateTime.now();
        ViewBoardDto viewBoardDto = new ViewBoardDto(id,title,content,viewCount,imageDto, createdAt);
        return ResponseEntity.ok(viewBoardDto);
    }

    //게시글 수정
    @PutMapping("/{post-id}")
    public ResponseEntity updatePost(@PathVariable(name = "homepee-id") int homepeeId, @PathVariable(name = "post-id") int postId) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    //게시글 댓글 추가
    @PostMapping("/{post-id}/comment")
    public ResponseEntity<SavedCommentDto> addComment(@PathVariable(name = "homepee-id") int hompeeId, @PathVariable(name = "post-id") int postId, @RequestBody AddCommentDto addCommentDto) {
        int id = 0;
        int memberId = addCommentDto.getMemberId();
        String content = addCommentDto.getContent();
        LocalDateTime createdAt = LocalDateTime.now();
        SavedCommentDto savedComment = new SavedCommentDto(id, memberId, content, createdAt);
        return ResponseEntity.ok(savedComment);
    }

    //게시글 댓글 삭제
    @DeleteMapping("/{post-id}/comments/{comment-id}")
    public ResponseEntity delteComment(@PathVariable(name = "homepee-id") int hompeeId, @PathVariable(name = "post-id") int postId, @PathVariable(name = "comment-id") int commentId) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    //게시글 삭제
    @DeleteMapping("/{post-id}")
    public ResponseEntity deltePost(@PathVariable(name = "homepee-id") int hompeeId, @PathVariable(name = "post-id") int postId) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
