package com.j2kb.minipetpee.api.album.controller;

import com.j2kb.minipetpee.api.album.controller.dto.request.SaveAlbumPostRequest;
import com.j2kb.minipetpee.api.album.controller.dto.request.UpdateAlbumPostRequest;
import com.j2kb.minipetpee.api.album.controller.dto.response.*;
import com.j2kb.minipetpee.api.album.domain.AlbumPost;
import com.j2kb.minipetpee.api.album.service.AlbumService;
import com.j2kb.minipetpee.global.domain.Post;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
            @Valid @RequestBody SaveAlbumPostRequest albumPostRequest
    ) {
        //홈피 주인만 게시글 등록 가능(권한 체크)

        AlbumPost saveAlbumPost =
                albumService.saveAlbumPost(homepeeId, albumPostRequest);
        return ResponseEntity.ok(new SaveAlbumPostResponse(saveAlbumPost));
    }

    @Operation(summary = "갤러리 게시글 조회", description = "전체 갤러리 내용 모두 조회")
    @GetMapping
    public ResponseEntity<AlbumPaginationResponse> findAlbumPosts(
            @PathVariable(name = "homepee-id") Long homepeeId,
            @PageableDefault(size = 4, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.Direction.DESC, "id");
        //게시글 찾기
        AlbumPageResult albumPosts = albumService.findAlbumPosts(homepeeId, pageable, pageRequest);

        return ResponseEntity.ok(new AlbumPaginationResponse(albumPosts));
    }

    @Operation(summary = "갤러리 게시글 수정")
    @PutMapping
    public ResponseEntity<Void> updateAlbumPost(
            @PathVariable(name = "homepee-id") Long homepeeId,
            @Valid @RequestBody UpdateAlbumPostRequest albumPostRequest
    ) {
        //홈피 주인만 게시글 수정 가능(권한 체크)

        //hompeeId에 해당하는 album 찾기
        Post albumPost = albumService.findAlbumPost(homepeeId, albumPostRequest);

        albumService.updateAlbumPost(albumPost, albumPostRequest);
        return ResponseEntity.noContent().build();
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
}
