package com.j2kb.minipetpee.api.album.controller;

import com.j2kb.minipetpee.api.album.controller.dto.request.SaveAlbumPostCommentRequest;
import com.j2kb.minipetpee.api.album.controller.dto.request.SaveAlbumPostRequest;
import com.j2kb.minipetpee.api.album.controller.dto.request.UpdateAlbumPostRequest;
import com.j2kb.minipetpee.api.album.controller.dto.response.*;
import com.j2kb.minipetpee.api.album.domain.AlbumPost;
import com.j2kb.minipetpee.api.album.service.AlbumService;
import com.j2kb.minipetpee.global.domain.Comment;
import com.j2kb.minipetpee.global.domain.Image;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        return ResponseEntity.ok().body(new SaveAlbumPostResponse(saveAlbumPost));
    }

    @Operation(summary = "갤러리 게시글 조회", description = "전체 갤러리 내용 모두 조회")
    @GetMapping
    public ResponseEntity<AlbumPaginationResponse> findAlbumPosts(
            @PathVariable(name = "homepee-id") Long homepeeId,
            @PageableDefault(size = 4, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        //게시글 찾기
        Page<AlbumPost> albumPosts = albumService.findAlbumPosts(homepeeId, pageable);

        PageRequest pageRequest = PageRequest.of(0, 3, Sort.Direction.DESC, "createdAt");
        //게시글의 댓글 찾기
        Map<Long, Page<Comment>> albumComments = albumService.findAlbumComments(albumPosts, pageRequest);
        return ResponseEntity.ok().body(new AlbumPaginationResponse(albumPosts,albumComments));
    }

    @Operation(summary = "갤러리 게시글의 댓글 더보기", description = "댓글 더보기를 누르면 댓글 3개씩 조회")
    @GetMapping("/{post-id}/comments")
    public ResponseEntity<AlbumCommentPaginationResponse> findAlbumComments(
            @PathVariable(name = "homepee-id") Long homepeeId,
            @PathVariable(name = "post-id") Long postId,
            @PageableDefault(size = 3, page = 1, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<Comment> albumComments = albumService.findAlbumCommentsById(homepeeId, postId, pageable);
        return ResponseEntity.ok().body(new AlbumCommentPaginationResponse(albumComments));
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

        //전달된 이미지 Image 객체로 변경
        List<Image> sendFromImage = updateAlbumPost.getImages()
                .stream()
                .map(Image::new)
                .collect(Collectors.toList());

        List<Image> deleteImage = new ArrayList<>();  //삭제할 이미지
        List<Image> fromSendImage = new ArrayList<>();  //전달받은 이미지 중 서버에 있는 이미지
        List<Image> addFileList = new ArrayList<>(); //전달받은 이미지 중 서버에 없는 이미지

        //전달되어 온 사진에서 서버의 사진 포함하고 있지 않으면 삭제
        for (Image image : albumPost.getImages()) {
            if(!sendFromImage.contains(image)) {
                //서버의 이미지 삭제
                deleteImage.add(image);
            }else {
                //image에 저장되어 있으면서 전달되어 온 사진 정보
                fromSendImage.add(image);
            }
        }

        //image 에 저장되어 있으면서 전달되어 온 사진 정보에 전달되어 온 정보 비교 후 없는건 저장
        for (Image updateImage : sendFromImage) {
            if(!fromSendImage.contains(updateImage)) {
                addFileList.add(updateImage);
            }
        }

        albumService.updateAlbumPost(albumPost, updateAlbumPost, addFileList, deleteImage);
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
        return ResponseEntity.ok().body(new SaveAlbumPostCommentResponse(comment));
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
    public ResponseEntity<Void> deleteAlbumComment(
            @PathVariable(name = "homepee-id") Long homepeeId,
            @PathVariable(name = "post-id") Long postId,
            @PathVariable(name = "comment-id") Long commentId
    ) {
        //댓글 작성자만 댓글 삭제 가능(권한 체크)

        albumService.deleteAlbumComment(homepeeId, postId, commentId);
        return ResponseEntity.noContent().build();
    }
}
