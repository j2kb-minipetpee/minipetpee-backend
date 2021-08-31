package com.j2kb.minipetpee.api.album.controller;

import com.j2kb.minipetpee.api.album.controller.dto.request.SaveAlbumPostCommentRequest;
import com.j2kb.minipetpee.api.album.controller.dto.request.SaveAlbumPostRequest;
import com.j2kb.minipetpee.api.album.controller.dto.request.UpdateAlbumPostRequest;
import com.j2kb.minipetpee.api.album.controller.dto.response.*;
import com.j2kb.minipetpee.api.album.domain.AlbumPost;
import com.j2kb.minipetpee.api.album.service.AlbumService;
import com.j2kb.minipetpee.global.domain.Comment;
import com.j2kb.minipetpee.global.dto.CommentPaginationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Tag(name = "갤러리 API")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/apis/{homepee-id}/album/posts")
public class AlbumController {

    private final AlbumService albumService;

    //게시글 등록
    @Operation(summary = "갤러리 게시글 등록")
    @PostMapping
    public ResponseEntity<SaveAlbumPostResponse> saveAlbumPost(
            @PathVariable(name = "homepee-id") Long homepeeId,
            @Valid @RequestBody SaveAlbumPostRequest saveAlbumPostRequest
    ) {
        //홈피 주인만 게시글 등록 가능(권한 체크)

        AlbumPost saveAlbumPost =
                albumService.saveAlbumPost(homepeeId, saveAlbumPostRequest);
        return ResponseEntity.ok(new SaveAlbumPostResponse(saveAlbumPost));
    }

    @Operation(summary = "갤러리 게시글 조회", description = "전체 갤러리 내용 모두 조회")
    @GetMapping
    public ResponseEntity<AlbumPaginationResponse> findAlbumPosts(
            @PathVariable(name = "homepee-id") Long homepeeId,
            @PageableDefault(size = 4, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        //게시글 찾기
        Page<AlbumPost> albumPosts = albumService.findAlbumPosts(homepeeId, pageable);

        PageRequest pageRequest = PageRequest.of(0, 3, Sort.Direction.DESC, "id");
        //게시글의 댓글 찾기
        Map<Long, Page<Comment>> albumComments = albumService.findAlbumPostComments(albumPosts, pageRequest);
        return ResponseEntity.ok(new AlbumPaginationResponse(albumPosts,albumComments));
    }

    @Operation(summary = "갤러리 게시글의 댓글 더보기", description = "댓글 더보기를 누르면 댓글 3개씩 조회")
    @GetMapping("/{post-id}/comments")
    public ResponseEntity<CommentPaginationResponse> findAlbumPostComments(
            @PathVariable(name = "homepee-id") Long homepeeId,
            @PathVariable(name = "post-id") Long postId,
            @PageableDefault(size = 3, page = 1, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<Comment> albumComments = albumService.findAlbumPostCommentsById(homepeeId, postId, pageable);
        return ResponseEntity.ok(new CommentPaginationResponse(albumComments));
    }


    @Operation(summary = "갤러리 게시글 수정")
    @PutMapping
    public ResponseEntity<Void> updateAlbumPost(
            @PathVariable(name = "homepee-id") Long homepeeId,
            @Valid @RequestBody UpdateAlbumPostRequest updateAlbumPost
    ) {
        //홈피 주인만 게시글 수정 가능(권한 체크)

        //hompeeId에 해당하는 album 찾기
        AlbumPost albumPost = albumService.findAlbumPost(homepeeId, updateAlbumPost);

        albumService.updateAlbumPost(albumPost, updateAlbumPost);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "갤러리 게시글 댓글 작성")
    @PostMapping("/{post-id}/comments")
    public ResponseEntity<SaveAlbumPostCommentResponse> saveAlbumPostComment(
            @PathVariable(name = "homepee-id") Long homepeeId,
            @PathVariable(name = "post-id") Long postId,
            @Valid @RequestBody SaveAlbumPostCommentRequest albumCommentRequest
    ) {
        Comment comment = albumService.saveAlbumPostComment(homepeeId, postId, albumCommentRequest);
        return ResponseEntity.ok(new SaveAlbumPostCommentResponse(comment));
    }


    @Operation(summary = "갤러리 게시글 삭제")
    @DeleteMapping("/{post-id}")
    public ResponseEntity<Void> deleteAlbumPost(
            @PathVariable(name = "homepee-id") Long homepeeId,
            @PathVariable(name = "post-id") Long postId
    ) {
        //홈피 주인만 게시글 삭제 가능(권한 체크)

        albumService.deleteAlbumPost(homepeeId, postId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "갤러리 게시글 댓글 삭제")
    @DeleteMapping("/{post-id}/comments/{comment-id}")
    public ResponseEntity<Void> deleteAlbumPostComment(
            @PathVariable(name = "homepee-id") Long homepeeId,
            @PathVariable(name = "post-id") Long postId,
            @PathVariable(name = "comment-id") Long commentId
    ) {
        //댓글 작성자만 댓글 삭제 가능(권한 체크)

        albumService.deleteAlbumPostComment(homepeeId, postId, commentId);
        return ResponseEntity.noContent().build();
    }
}
