package com.j2kb.minipetpee.api.comment.controller;

import com.j2kb.minipetpee.api.comment.controller.dto.request.SavePostCommentRequest;
import com.j2kb.minipetpee.api.comment.controller.dto.response.SavePostCommentResponse;
import com.j2kb.minipetpee.api.comment.domain.Comment;
import com.j2kb.minipetpee.api.comment.service.CommentService;
import com.j2kb.minipetpee.global.dto.CommentPaginationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "댓글 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/apis/{homepee-id}/posts/{post-id}/comments")
public class CommentController {

    private final CommentService commentService;

    @Parameter(in = ParameterIn.QUERY
            , description = "페이지 (1 부터 시작)"
            , name = "page"
            , content = @Content(schema = @Schema(type = "integer", defaultValue = "1")))
    @Parameter(in = ParameterIn.QUERY
            , description = "반환할 데이터 수"
            , name = "size"
            , content = @Content(schema = @Schema(type = "integer", defaultValue = "3")))
    @Operation(summary = "게시글의 댓글 더보기", description = "댓글 더보기를 누르면 댓글 3개씩 조회")
    @GetMapping
    public ResponseEntity<CommentPaginationResponse> findPostComments(
            @PathVariable(name = "homepee-id") Long homepeeId,
            @PathVariable(name = "post-id") Long postId,
            @PageableDefault(size = 3, page = 1, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<Comment> albumComments = commentService.findPostComments(postId, pageable);
        return ResponseEntity.ok(new CommentPaginationResponse(albumComments));
    }

    @Operation(summary = "게시글 댓글 작성")
    @PostMapping
    public ResponseEntity<SavePostCommentResponse> savePostComment(
            @PathVariable(name = "homepee-id") Long homepeeId,
            @PathVariable(name = "post-id") Long postId,
            @Valid @RequestBody SavePostCommentRequest commentRequest
    ) {
        Comment comment = commentService.savePostComment(postId, commentRequest);
        return ResponseEntity.ok(new SavePostCommentResponse(comment));
    }

    @Operation(summary = "게시글 댓글 삭제")
    @DeleteMapping("/{comment-id}")
    public ResponseEntity<Void> deletePostComment(
            @PathVariable(name = "homepee-id") Long homepeeId,
            @PathVariable(name = "post-id") Long postId,
            @PathVariable(name = "comment-id") Long commentId
    ) {
        //댓글 작성자만 댓글 삭제 가능(권한 체크)

        commentService.deletePostComment(postId, commentId);
        return ResponseEntity.noContent().build();
    }
}
