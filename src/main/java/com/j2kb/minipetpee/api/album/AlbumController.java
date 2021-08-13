package com.j2kb.minipetpee.api.album;

import com.j2kb.minipetpee.api.album.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/apis/{homepee-id}/album/posts")
public class AlbumController {

    //게시글 등록
    @PostMapping
    public ResponseEntity<SaveAlbumPostResponse> saveAlbumPost(@PathVariable(name = "homepee-id") int homepeeId, @RequestBody SaveAlbumPostRequest albumDto) {
        for(SaveAlbumPostImage albumPostImage : albumDto.getImages())
            log.info("ImageUrl = {}", albumPostImage.getUrl());

        SaveAlbumPostResponse saveAlbumPost = new SaveAlbumPostResponse(1);
        return ResponseEntity.ok(saveAlbumPost);
    }

    //게시글 수정
    @PutMapping
    public ResponseEntity<Void> updateAlbumPost(@PathVariable(name = "homepee-id") int homepeeId, @RequestBody UpdateAlbumPostRequest albumDto) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    //게시글 조회 - 전체 사진첩 내용 모두 조회
    @GetMapping
    public ResponseEntity<FindAlbumPostResponse> findAlbumPost(@PathVariable(name = "homepee-id") int homepeeId) {
        List<FindAlbumPostImage> albumImage1 = new ArrayList<>();
        albumImage1.add(new FindAlbumPostImage(0, "image1URL"));
        albumImage1.add(new FindAlbumPostImage(1, "image2URL"));
        albumImage1.add(new FindAlbumPostImage(2, "image3URL"));

        FindAlbumPostListResponse album1 = new FindAlbumPostListResponse(0,"title1", albumImage1, 100, true);

        List<FindAlbumPostImage> albumImage2 = new ArrayList<>();
        albumImage2.add(new FindAlbumPostImage(3, "image3URL"));
        albumImage2.add(new FindAlbumPostImage(4, "image4URL"));

        FindAlbumPostListResponse album2 = new FindAlbumPostListResponse(1,"title2", albumImage2, 1000, true);

        List<FindAlbumPostListResponse> albumLists = new ArrayList<>();
        albumLists.add(album1);
        albumLists.add(album2);

        FindAlbumPostResponse albumResponse = new FindAlbumPostResponse(albumLists);

        return ResponseEntity.ok(albumResponse);
    }

    //게시글에 댓글 작성
    @PostMapping("/{post-id}/comments")
    public ResponseEntity<SaveAlbumPostCommentResponse> saveAlbumPostComment(@PathVariable(name = "homepee-id") int homepeeId, @PathVariable(name = "post-id") int postId, @RequestBody SaveAlbumPostCommentRequest albumRequest) {
        AlbumPostCommentMember memberInfo = new AlbumPostCommentMember(2,"minipet", "imageurllll");
        SaveAlbumPostCommentResponse albumResponse = new SaveAlbumPostCommentResponse(0, albumRequest.getContent(), memberInfo,LocalDateTime.now());
        return ResponseEntity.ok(albumResponse);
    }

    @DeleteMapping("/{post-id}")
    public ResponseEntity<Void> deleteAlbumPost(@PathVariable(name = "homepee-id") int homepeeId, @PathVariable(name = "post-id") int postId) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{post-id}/comments/{comment-id}")
    public ResponseEntity<Void> deleteAlbumComment(@PathVariable(name = "homepee-id") int homepeeId, @PathVariable(name = "post-id") int postId, @PathVariable(name = "comment-id") int commentId) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
