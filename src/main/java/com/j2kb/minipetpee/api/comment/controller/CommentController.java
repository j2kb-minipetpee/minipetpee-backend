package com.j2kb.minipetpee.api.comment.controller;

import com.j2kb.minipetpee.api.comment.controller.dto.request.SavePostCommentRequest;
import com.j2kb.minipetpee.api.comment.controller.dto.response.SavePostCommentResponse;
import com.j2kb.minipetpee.api.comment.domain.Comment;
import com.j2kb.minipetpee.api.comment.service.CommentService;
import com.j2kb.minipetpee.global.dto.CommentPaginationResponse;
import com.j2kb.minipetpee.security.jwt.JwtAuthenticationPrincipal;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
            @ParameterObject @PageableDefault(size = 3, page = 1, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<Comment> albumComments = commentService.findPostComments(postId, pageable);
        return ResponseEntity.ok(new CommentPaginationResponse(albumComments));
    }

    @Operation(summary = "게시글 댓글 작성")
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<SavePostCommentResponse> savePostComment(
            @Parameter(hidden = true) @AuthenticationPrincipal JwtAuthenticationPrincipal principal,
            @PathVariable(name = "homepee-id") Long homepeeId,
            @PathVariable(name = "post-id") Long postId,
            @Valid @RequestBody SavePostCommentRequest commentRequest
    ) {
        Comment comment = commentService.savePostComment(postId, principal.getId(), commentRequest);
        return ResponseEntity.ok(new SavePostCommentResponse(comment));
    }

    @Operation(summary = "게시글 댓글 삭제")
    @DeleteMapping("/{comment-id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> deletePostComment(
            @Parameter(hidden = true) @AuthenticationPrincipal JwtAuthenticationPrincipal principal,
            @PathVariable(name = "homepee-id") Long homepeeId,
            @PathVariable(name = "post-id") Long postId,
            @PathVariable(name = "comment-id") Long commentId
    ) {
        commentService.deletePostComment(postId, principal.getId(), commentId);
        return ResponseEntity.noContent().build();
    }
}
