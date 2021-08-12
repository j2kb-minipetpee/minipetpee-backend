package com.j2kb.minipetpee.api.album;

import com.fasterxml.jackson.core.JsonParser;
import com.j2kb.minipetpee.api.album.dto.*;
import com.j2kb.minipetpee.api.board.dto.ImageDto;
import com.j2kb.minipetpee.domain.Image;
import com.j2kb.minipetpee.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
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
    public ResponseEntity<NewResponseAlbumDto> saveAlbumPosts(@PathVariable(name = "homepee-id") int hompeeId, @RequestBody String albumDto) {
        log.info("{}", albumDto);
        List<ImageDto> imageList = new ArrayList<>();
        JSONObject obj = new JSONObject(albumDto);
        JSONArray images = obj.getJSONArray("images");
        for (int i = 0; i < images.length(); i++)
        {
            imageList.add(new ImageDto(images.getJSONObject(i).getString("url")));
        }

        NewRequestAlbumDto album = new NewRequestAlbumDto(obj.getString("title"),imageList, obj.getBoolean("visible"));
        log.info("{}",album);

        NewResponseAlbumDto responseAlbumDto = new NewResponseAlbumDto(0);
        return ResponseEntity.ok(responseAlbumDto);
    }

    //게시글 수정
    @PutMapping
    public ResponseEntity updateAlbumPost(@PathVariable(name = "homepee-id") int hompeeId, @RequestBody UpdateAlbumDto albumDto) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    //게시글 조회 - 전체 사진첩 내용 모두 조회
    @GetMapping
    public ResponseEntity<List<AlbumListDto>> lookupAlbumPost(@PathVariable(name = "homepee-id") int hompeeId) {
        List<AlbumImageDto> albumImage1 = new ArrayList<>();
        albumImage1.add(new AlbumImageDto(0, "image1URL"));
        albumImage1.add(new AlbumImageDto(1, "image2URL"));
        albumImage1.add(new AlbumImageDto(2, "image3URL"));

        AlbumListDto album1 = new AlbumListDto(0,"title1", albumImage1, 100, true);

        List<AlbumImageDto> albumImage2 = new ArrayList<>();
        albumImage2.add(new AlbumImageDto(3, "image3URL"));
        albumImage2.add(new AlbumImageDto(4, "image4URL"));

        AlbumListDto album2 = new AlbumListDto(1,"title2", albumImage2, 1000, true);

        List<AlbumListDto> albumLists = new ArrayList<>();
        albumLists.add(album1);
        albumLists.add(album2);

        return ResponseEntity.ok(albumLists);
    }

    //게시글에 댓글 작성
    @PostMapping("/{post-id}/comments")
    public ResponseEntity<AlbumResponseCommentDto> addAlbumComment(@PathVariable(name = "homepee-id") int hompeeId, @PathVariable(name = "post-id") int postId,
                                                                   @RequestBody AlbumRequestCommentDto albumRequest) {
        MemberInfoDto memberInfo = new MemberInfoDto(2,"minipet", "imageurllll");
        AlbumResponseCommentDto albumResponse = new AlbumResponseCommentDto(0, albumRequest.getContent(), memberInfo,LocalDateTime.now());
        return ResponseEntity.ok(albumResponse);
    }

    @DeleteMapping("/{post-id}")
    public ResponseEntity deleteAlbumPost(@PathVariable(name = "homepee-id") int hompeeId, @PathVariable(name = "post-id") int postId) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{post-id}/comments/{comment-id}")
    public ResponseEntity deleteAlbumComment(@PathVariable(name = "homepee-id") int hompeeId, @PathVariable(name = "post-id") int postId, @PathVariable(name = "comment-id") int commentId) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
